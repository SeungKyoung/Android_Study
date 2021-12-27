package com.example.myprojectx;

import static com.example.myprojectx.Common.CommonMethod.isNetworkConnected;
import static com.example.myprojectx.Common.CommonMethod.loginDTO;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myprojectx.ATask.MemberSelect;
import com.example.myprojectx.DTO.MemberDTO;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/*1. DB에 있는 테이블을 기본으로 하여 DTO를 만든다
        1. DB에 MemberDTO라는 테이블이 있다
        2. MemberDTO라는 테이블에 id, name, phonenumber, address, imgpath(이미지 경로)의 칼럼이 있다

    2. 1에서 만든 DTO에서 내가 보여주고 싶은 데이터를 골라 xml파일(member_view)을 만든다
    3. xml 파일에서 사용된 모든 변수를 adapter에서 클래스(ItemViewHolder)로 선언한다
    4. 선언한 클래스와 xml 파일을 이용하여 화면을 adapter에서 생성한다
    5. 만든 adapter를 RecyclerView에 붙여준다*/

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    RecyclerView recyclerView;

    ArrayList<MemberDTO> dtos;
    MemberAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        Glide.with(MainActivity.this)
                .load(loginDTO.getImgpath())
                .circleCrop()
                .into(imageView);

        // 리사이클러뷰 시작
        dtos = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        // recyclerView에서 반드시 초기화를 아래와 같이 해줘야함
        LinearLayoutManager layoutManager = new LinearLayoutManager
                (this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // 어댑터 객체 생성
        adapter = new MemberAdapter(MainActivity.this, dtos);

        recyclerView.setAdapter(adapter);

        // 서버에 멤버 ArrayList를 요구한다
        if(isNetworkConnected(this) == true){
            // AsyncTask 를 만든다
            MemberSelect memberSelect = new MemberSelect(dtos, adapter);
            try {
                memberSelect.execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            Toast.makeText(this, "인터넷에 연결되어 있지 않습니다", Toast.LENGTH_SHORT).show();
        }

    }


}