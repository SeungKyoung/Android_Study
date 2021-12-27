package com.example.ex10_intentresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //암시적(묵시), 명시적
                //명시적 인텐트, 정확한 위치를 알고 있을때 사용. ***
                //ex) 액티비티 (회원가입) 회원의 정보들 (intent) -> 액티비티(로그인) -> intent
                //묵시 ex) 액티비티 -> 외부서비스(전화걸기, naverWeb...)
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                //Intent를 지금위치와 이동할 위치를 지정을 하고 초기화
                intent.putExtra("test", "내용");
                intent.putExtra("testInt", 1);
                //intent에 key, value를 통해서 보내줄 데이터를 넣어둠.

                testDTO dto = new testDTO("test", 1, 3.14);
                //객체를 생성해서 intent에 담음
                intent.putExtra("dto", dto);
                startActivity(intent);
            }
        });
    }
}