package com.example.test01_layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imgv1, imgv2, imgv3, imgv4;
    Button btn1, btn2;
    int index = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.bnt1);
        btn2 = findViewById(R.id.bnt2);
        imgv1 = findViewById(R.id.imgv1);
        imgv2 = findViewById(R.id.imgv2);
        imgv3 = findViewById(R.id.imgv3);
        imgv4 = findViewById(R.id.imgv4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index ==3){
                    index = 1;
                } else {
                    index++;
                }
                changeImg();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeScroll();
            }
        });
    }
    public void changeImg(){
        imgv1.setVisibility(View.GONE);
        imgv2.setVisibility(View.GONE);
        imgv3.setVisibility(View.GONE);
        if(index == 1){
            imgv1.setVisibility(View.VISIBLE);
        } else if(index == 2){
            imgv2.setVisibility(View.VISIBLE);
        } else if(index == 3){
            imgv3.setVisibility(View.VISIBLE);
        }
    }
    public void changeScroll() {
        if (index == 1) {
            imgv4.setImageResource(R.drawable.image1);
        } else if (index == 2) {
            imgv4.setImageResource(R.drawable.image2);
        } else if (index == 3) {
            imgv4.setImageResource(R.drawable.image3);
        }
    }
}