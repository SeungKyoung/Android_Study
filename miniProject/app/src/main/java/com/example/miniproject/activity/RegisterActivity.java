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
import com.example.miniproject.MainActivity;
import com.example.miniproject.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RegisterActivity  extends AppCompatActivity {
    EditText register_edt1, register_edt2, register_edt3, register_edt4;
    Button register_btn1, register_btn2;
    Intent intent;
    List<UserDTO> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register_btn1 = findViewById(R.id.register_btn1);
        register_btn2 = findViewById(R.id.register_btn2);
        register_edt1 = findViewById(R.id.register_edt1);
        register_edt2 = findViewById(R.id.register_edt2);
        register_edt3 = findViewById(R.id.register_edt3);
        register_edt4 = findViewById(R.id.register_edt4);

        AskTask conn = new AskTask("idCheck.test");
        try {
            InputStream is = conn.execute().get();
            Gson gson = new Gson();
            list = gson.fromJson(new InputStreamReader(is) ,
                    new TypeToken<List<UserDTO>>(){}.getType());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        register_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = register_edt1.getText()+"";
                String pw = register_edt2.getText()+"";
                String name = register_edt3.getText()+"";
                int birth = Integer.parseInt(register_edt4.getText()+"");

                for (UserDTO dtos: list) {
                    int length = (int)(Math.log10(birth)+1);
                    if(!dtos.getId().equals(id) && (length == 6)){
                            int succ = 0;
                            UserDTO dto = new UserDTO(id, pw, name, birth);
                            AskTask conn = new AskTask("join.test", dto);
                            try {
                                InputStream is = conn.execute().get();
                                Gson gson = new Gson();
                                succ = gson.fromJson(new InputStreamReader(is), Integer.class);
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            if(succ >0){
                                Toast.makeText(RegisterActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                                intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterActivity.this, "회원가입 실패", Toast.LENGTH_SHORT).show();

                            }

                        } else if(dtos.getId().equals(id)) {
                            Toast.makeText(RegisterActivity.this, "중복된 아이디입니다.", Toast.LENGTH_SHORT).show();
                            register_edt1.requestFocus();
                        }else if((length != 6)){
                            Toast.makeText(RegisterActivity.this, "생년월인은 6자리로 일력해주세요.", Toast.LENGTH_SHORT).show();
                            register_edt4.requestFocus();
                    }
                }
            }
        });
        register_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}