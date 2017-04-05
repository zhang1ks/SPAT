package com.example.zhang1ks.testbottombar;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import java.lang.reflect.Field;

/**
 * Created by yuge on 11/14/2016.
 */

public class TabFg1 extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fg1_tab, container, false);
        final VideoView view = (VideoView)rootView.findViewById(R.id.myVideo);
        String path = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.a;
        view.setVideoURI(Uri.parse(path));
//        MediaController vidControl = new MediaController(getActivity());
//        vidControl.setAnchorView(view);
//        view.setMediaController(vidControl);

//        final DBHelper mydb = new DBHelper(getActivity());

        final FloatingActionButton mPlayButton = (FloatingActionButton) rootView.findViewById(R.id.play_button);

        view.setOnPreparedListener (new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mPlayButton.setVisibility(View.VISIBLE);
            }
        });

        view.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent)
            {
                if (view.isPlaying())
                {
                    view.pause();
                    mPlayButton.setVisibility(View.VISIBLE);
                    return false;
                }
                else
                {
                    view.start();
                    mPlayButton.setVisibility(View.GONE);
                    return false;
                }
            }
        });

        view.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mPlayButton.setVisibility(View.VISIBLE);
            }
        });

        FloatingActionButton bt_1 =(FloatingActionButton)rootView.findViewById(R.id.fab1);
        bt_1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton bt_2 =(FloatingActionButton)rootView.findViewById(R.id.fab5);
        bt_2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton bt_3 =(FloatingActionButton)rootView.findViewById(R.id.fab4);
        bt_3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton bt_4 =(FloatingActionButton)rootView.findViewById(R.id.fab3);
        bt_4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        return rootView;
    }


}