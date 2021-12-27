package com.example.ex10_intentresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        //디자인 로딩이 완료가 되고나서 위젯을 찾는다.
        TextView text2 = findViewById(R.id.text2);
        //MainActivity Intent -> SubActivity
        Intent intent = getIntent(); //모델
        String test = intent.getStringExtra("test");
        int textInt = intent.getIntExtra("testInt", -1);
        testDTO dto = (testDTO) intent.getSerializableExtra("dto");

        text2.setText("test + testInt\r\n");
        text2.append(dto.field1 + dto.field2 + dto.fiedl3);//기존 내용 + 새로운 글자
    }
}