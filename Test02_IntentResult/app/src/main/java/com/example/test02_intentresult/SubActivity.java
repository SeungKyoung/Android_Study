package com.example.test02_intentresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Button btn2 = findViewById(R.id.btn2);
        TextView tv = findViewById(R.id.tv);
        Intent intent = getIntent();
        userDTO dto = (userDTO) intent.getSerializableExtra("dto");
        tv.setText(dto.getId() + "\r\n" + dto.getPw() + "\r\n" + dto.getName() + "\r\n" + dto.getAge() + "\r\n" + dto.getAddr() + "\r\n" + dto.getNick());

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, LoginActivity.class);
                intent.putExtra("dto", dto);
                startActivity(intent);
            }
        });
    }
}