/*
 * Copyright 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.zhang1ks.testbottombar;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.IOException;

import static android.R.attr.angle;
import static android.R.attr.orientation;

public class CameraActivity extends Activity implements Camera2BasicFragment.ImageSaver.OnImageDecodedListener{

    private static final int READ_REQUEST_CODE = 1;
    private ImageView button_close;
    private ImageView btn_switchvideo;
    private ImageView btn_preview;
    private Button btn_post;
    String savedPhoto;
    ImageView image;
    FrameLayout frameLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posting_page_plus);

        final DBHelper mydb=new DBHelper(this);




        initBackToPreview();
        initClose();
        image = (ImageView) findViewById(R.id.imageontexture);

        btn_post=(Button) findViewById(R.id.btn_post);
        btn_post.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mydb.insertSavedResource(savedPhoto);
            }
        });

        frameLayout = (FrameLayout) findViewById(R.id.imageviewframe);
        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.myFramelayout, Camera2BasicFragment.newInstance())
                    .commit();
        }




    }
    private void initClose(){
        button_close = (ImageView) findViewById(R.id.plus_btn_close);
        button_close.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }


    private void initBackToPreview(){
        btn_preview = (ImageView) findViewById(R.id.btn_preview);
        btn_preview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    }


    public void performFileSearch(View view) {

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/* video/*");

        startActivityForResult(intent, READ_REQUEST_CODE);

    }

    @Override
    public void onImageDecoded(Bitmap b) {

        image.setImageBitmap(b);
        savedPhoto = b.toString();
        frameLayout.setBackgroundColor(Color.rgb(0,0,0));
        btn_post.setVisibility(View.VISIBLE);

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == READ_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap myBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));


                FrameLayout frameLayout=(FrameLayout) findViewById(R.id.imageviewframe);
                ImageView imageView = (ImageView) findViewById(R.id.imageontexture);
                imageView.setImageBitmap(myBitmap);


                frameLayout.setBackgroundColor(Color.rgb(0,0,0));



                Button btn_post = (Button) findViewById(R.id.btn_post);
                btn_post.setVisibility(View.VISIBLE);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
