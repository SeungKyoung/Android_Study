package com.example.safingproject.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.safingproject.activity.MainActivity;
import com.example.safingproject.R;


public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    TextView tv1, tv2;
    ImageView logo_bg;
    Animation move_bottom_up, fade_in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo_bg = findViewById(R.id.logo_bg);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        move_bottom_up = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.move_bottom_down);
        fade_in = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fade_in);

        //logo_bg.startAnimation(move_bottom_up);
        tv1.startAnimation(fade_in);
        tv2.startAnimation(fade_in);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}