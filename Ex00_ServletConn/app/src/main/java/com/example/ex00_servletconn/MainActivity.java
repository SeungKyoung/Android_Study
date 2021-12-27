package com.example.ex00_servletconn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    //1.Manifest확인 ( clearTrafic , Permission , Lib )
    //Permissions ( 사용 권한을 부여받는 것 ) , Lib(추가)
    //usesCleartextTraffic( Https , Http ) 추가
    //2.Gradle (라이브러리 추가) -> sync
    //3.Controller까지 가는거 테스트

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson = new Gson();
        UserDTO dto = new UserDTO(10,"a","c");
        ArrayList<String> list = new ArrayList<>();
        list.add(gson.toJson(dto));

        AskTask2 askTest2 = new AskTask2("dto.and" , list);
        try {
            InputStream in = askTest2.execute().get();
            UserDTO fromDto = gson.fromJson(new InputStreamReader(in), UserDTO.class);
            String aa = new String("d");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}