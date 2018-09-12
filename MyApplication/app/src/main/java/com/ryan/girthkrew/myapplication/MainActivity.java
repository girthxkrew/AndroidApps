package com.ryan.girthkrew.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void fade (View view)
    {
        ImageView sasuke = (ImageView) findViewById(R.id.sasuke);

        //sasuke.animate().alpha(0f).setDuration(2000);

        //sasuke.animate().translationXBy(1000f).setDuration(2000);

        sasuke.animate().rotation(360f).setDuration(4000);

        //ImageView itachi = (ImageView) findViewById(R.id.itachi);

        //itachi.animate().alpha(1f).setDuration(2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ImageView sasuke = (ImageView) findViewById(R.id.sasuke);

        //sasuke.setTranslationX(-1000f);
    }
}
