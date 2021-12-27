package com.example.new01_intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SubActivity3 extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4;
    EditText edt1;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub3);
        btn1 = findViewById(R.id.sub3_btn1);
        btn2 = findViewById(R.id.sub3_btn2);
        btn3 = findViewById(R.id.sub3_btn3);
        btn4 = findViewById(R.id.sub3_btn4);
        edt1 = findViewById(R.id.sub3_edt1);

        intent = getIntent();
        String text = intent.getStringExtra("text");
        edt1.setText(text);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SubActivity3.this, MainActivity.class);
                intent.putExtra("text", text);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SubActivity3.this, SubActivity1.class);
                intent.putExtra("text", text);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestDTO dto = new TestDTO(text, 3);
                intent = new Intent(SubActivity3.this, SubActivity2.class);
                intent.putExtra("dto", dto);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent로 데이터를 다시 MainActivity로 보내줄 수 가 있음.
                //Intent를 초기화시켜놓고 putExtra
                TestDTO dto =(TestDTO) intent.getSerializableExtra("dto");
                setResult(RESULT_OK);
                finish();
            }
        });

    }
}