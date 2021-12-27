package com.example.new01_intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public int reqCode = 3000; //100번 이상으로 코드를 만들기
    Button btn1, btn2, btn3;
    EditText edt1;
    Intent intent;
    TestDTO dto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    btn1 = findViewById(R.id.main_btn1);
    btn2 = findViewById(R.id.main_btn2);
    btn3 = findViewById(R.id.main_btn3);
    edt1 = findViewById(R.id.main_edt1);

    intent = getIntent();
    String text = intent.getStringExtra("text");
    edt1.setText(text);

    btn1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String text = edt1.getText()+"";
            intent = new Intent(MainActivity.this, SubActivity1.class);
            intent.putExtra("text", text);
            startActivity(intent);
        }
    });

    btn2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String text = edt1.getText()+"";
            dto = new TestDTO(text, 1);
            intent = new Intent(MainActivity.this, SubActivity2.class);
            intent.putExtra("dto", dto);
            //dto객체는 반드시 직렬화(Serializable)을 해줘야함.
            startActivity(intent);
        }
    });

    btn3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Activity를 Intent로 다른 Activity에 요청을 하고 그 결과를
            //받아와야 하는 상황(일반적으로는 Activity 요청해 놓고 결과를 받아오는
            //경우는 드물다.
            //묵시적(암시적) Intent : url, Camera, 전화번호부 등 여러가지
            //안드로이드 OS에서 제공하고 내가 소스로 가지고있지 않은 부분을 요청하고나서는
            //그 결과를 받아와야하기 때문에 사용. (ex.Camera사진찍고, 갤러리)
            //※ 보내는 쪽에서 코드를 주고 다시 그결과를 코드로 받는것
            //1 -> 다시 1 -> 받는 것 성공 1 -> 다시 다른 값 받음 실패
            String text = edt1.getText()+"";
            dto = new TestDTO(text, 1);
            intent = new Intent(MainActivity.this, SubActivity3.class);
            intent.putExtra("dto", dto);
            startActivityForResult(intent, reqCode);
        }
    });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.main_btn1){
            Toast.makeText(MainActivity.this, "버튼1", Toast.LENGTH_SHORT).show();
        }
        if(v.getId() == R.id.main_btn2){
            Toast.makeText(MainActivity.this, "버튼2", Toast.LENGTH_SHORT).show();
        }
        if(v.getId() == R.id.main_btn3){
            Toast.makeText(MainActivity.this, "버튼3", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //우리가 보냈던 코드를 다시 받아왔는지 비교를 해줌
        //보냈던 코드가 그대로 나왔다는 것은 intent에 데이터가 있다고 보면 됨.
        if(requestCode == reqCode){
            Toast.makeText(MainActivity.this, "받음", Toast.LENGTH_SHORT).show();
        }
    }
}