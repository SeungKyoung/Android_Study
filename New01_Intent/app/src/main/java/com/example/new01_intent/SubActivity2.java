package com.example.new01_intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SubActivity2 extends AppCompatActivity {
    Button btn1, btn2, btn3;
    EditText edt1;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);
        btn1 = findViewById(R.id.sub2_btn1);
        btn2 = findViewById(R.id.sub2_btn2);
        btn3 = findViewById(R.id.sub2_btn3);
        edt1 = findViewById(R.id.sub2_edt1);

        intent = getIntent();
        TestDTO dto = (TestDTO) intent.getSerializableExtra("dto");
        edt1.setText(dto.getText() + dto.getNum());

        Common.userDTO = new TestDTO(dto.getText(), dto.getNum());

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SubActivity2.this, MainActivity.class);
                intent.putExtra("text", dto.getText());
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SubActivity2.this, SubActivity1.class);
                intent.putExtra("text", dto.getText());
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SubActivity2.this, SubActivity3.class);
                intent.putExtra("text", dto.getText());
                startActivity(intent);
            }
        });

    }
}