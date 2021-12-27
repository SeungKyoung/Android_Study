package com.example.new01_intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SubActivity1 extends AppCompatActivity {
    Button btn1, btn2, btn3;
    EditText edt1;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);
        btn1 = findViewById(R.id.sub1_btn1);
        btn2 = findViewById(R.id.sub1_btn2);
        btn3 = findViewById(R.id.sub1_btn3);
        edt1 = findViewById(R.id.sub1_edt1);

        intent = getIntent();
        String text = intent.getStringExtra("text");
        edt1.setText(text);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SubActivity1.this, MainActivity.class);
                intent.putExtra("text", text);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestDTO dto = new TestDTO(text, 2);
                intent = new Intent(SubActivity1.this, SubActivity2.class);
                intent.putExtra("dto", dto);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SubActivity1.this, SubActivity3.class);
                intent.putExtra("text", text);
                startActivity(intent);
            }
        });

    }
}