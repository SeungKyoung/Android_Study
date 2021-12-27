package com.example.test02_intentresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = findViewById(R.id.btn1);
        EditText edt_id = findViewById(R.id.edt_id);
        EditText edt_pw = findViewById(R.id.edt_pw);
        EditText edt_name = findViewById(R.id.edt_name);
        EditText edt_age = findViewById(R.id.edt_age);
        EditText edt_addr = findViewById(R.id.edt_addr);
        EditText edt_nick = findViewById(R.id.edt_nick);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                userDTO dto = new userDTO();
                dto.setId(edt_id.getText()+"");
                dto.setPw(edt_pw.getText()+"");
                dto.setName(edt_name.getText()+"");
                dto.setAge(Integer.parseInt(edt_age.getText()+""));
                dto.setAddr(edt_addr.getText()+"");
                dto.setNick(edt_nick.getText()+"");
                intent.putExtra("dto", dto);
                startActivity(intent);
            }
        });
    }
}