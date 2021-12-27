package com.example.miniproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miniproject.Atask.AskTask;
import com.example.miniproject.DTO.UserDTO;
import com.example.miniproject.R;
import com.example.miniproject.common.Common;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {
    Button login_btn1, login_btn2;
    EditText login_edt1, login_edt2;
    UserDTO dto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_btn1 = findViewById(R.id.login_btn1);
        login_btn2 = findViewById(R.id.login_btn2);
        login_edt1 = findViewById(R.id.login_edt1);
        login_edt2 = findViewById(R.id.login_edt2);

        login_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = login_edt1.getText()+"";
                String pw = login_edt2.getText()+"";
                AskTask conn = new AskTask("login.test", id);
                try {
                    InputStream is = conn.execute().get();
                    Gson gson = new Gson();
                    dto = gson.fromJson(new InputStreamReader(is) , UserDTO.class);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(dto != null){
                    if(id.equals(dto.getId()) && pw.equals(dto.getPw())){
                        Common.dto = dto;
                        Intent intent = new Intent(LoginActivity.this, UserMainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "비밀번호를 확인하세요.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "아이디를 확인하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        login_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Register", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}