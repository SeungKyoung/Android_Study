package com.example.ex04_framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imgv1, imgv2, imgv3;
    Button btn1, btn2;
    int index = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        imgv1 = findViewById(R.id.imgv1);
        imgv2 = findViewById(R.id.imgv2);
        imgv3 = findViewById(R.id.imgv3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼을 클릭할 때마다 다음 이미지가 보여짐
                //현 imgv3 보여진다면 버튼클릭시 imgv1 -> imgv2
                //메소드를 구현해서 버튼을 클릭할 때마다 다음 이미지가 보이는
                //철, 자바코드 어떤식으로 짜든 자유롭게
                if(index==1){
                    index = 3;
                } else {
                    index--;
                }
                changeImg();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index==3){
                    index = 1;
                } else {
                    index++;
                }
                changeImg();
            }
        });
    }
    public void changeImg(){
        imgv1.setVisibility(View.GONE);
        imgv2.setVisibility(View.GONE);
        imgv3.setVisibility(View.GONE);

        if(index == 3 ){
            imgv3.setVisibility(View.VISIBLE);
        } else if(index ==2) {
            imgv2.setVisibility(View.VISIBLE);
        }else if(index ==1) {
            imgv1.setVisibility(View.VISIBLE);
        }
    }
}