package com.example.test02_intentresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Member;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn3 = findViewById(R.id.btn3);
        Intent intent = getIntent();
        userDTO dto = (userDTO) intent.getSerializableExtra("dto");
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText log_id = findViewById(R.id.log_id);
                EditText log_pw = findViewById(R.id.log_pw);
                String id = log_id.getText() + "";
                String pw = log_pw.getText() + "";
                if (id.equals(dto.getId()) && pw.equals(dto.getPw())) {
                    Toast.makeText(LoginActivity.this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "아이디 비밀번호를 확인하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}