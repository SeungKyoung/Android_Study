package com.example.safingproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.safingproject.Atask.AskTask;
import com.example.safingproject.DTO.UserDTO;
import com.example.safingproject.R;
import com.example.safingproject.common.Common;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

                //================= DB와 데이터 주고 받는 객체 ==============

                //데이터 보내는 부분
                ArrayList<String> list = new ArrayList<>();
                Gson gson = new Gson();     //gson 초기화
                list.add(gson.toJson(id)); //보내는 데이터 변환
                AskTask conn = new AskTask("login.saf", list); //DB와 연결

                //데이터 받는 부분
                try {
                    InputStream is = conn.execute().get(); //DB에 데이터를 InputStream에 담기
                    dto = gson.fromJson(new InputStreamReader(is) , UserDTO.class);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //==============================================================

                if(dto != null){
                    if(id.equals(dto.getId()) && pw.equals(dto.getPw())){
                        Common.dto = dto;
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
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