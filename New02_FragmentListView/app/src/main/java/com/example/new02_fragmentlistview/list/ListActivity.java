package com.example.new02_fragmentlistview.list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.new02_fragmentlistview.R;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
//리스트뷰는 안드로이드에서 제공해주는 어댑터가 있음
//String 정도만 요소로 가지고 있는 리스트뷰를 구현
//기본적으로 제공해주는 것을 사용하는 것이 훨씬 편함
//이미지뷰, 텍스트뷰 기타 위젯을 하나로 묶어서 모양이 화려한 리스트뷰를 만들때는
//반드시 커스터마이징이 필요함.

//ListView에는 어댑터라는게 왜 필요할까?
//ArrayList<>() 개발자가 사용하는 DTO(객체, 상태) 각각 달라서 하나의 엘리먼트(요소) 묶어서 사용
//모든 개발자가 String 형태나 ImageView 형태만 사용했다면(정형화된 형태) 별도의 어댑터가 필요없음
//사용하는 용도가 각각 다르기 때문에 정형화 된 형태인 어댑터에 구현을 하면
//어댑터를 이용해서 ListView의 내용이 결정 된다.
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = findViewById(R.id.listview);

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0 ; i< 50 ; i++){
            list.add("테스트" + i);
        }

        //Context <- Activity가 가지고 있는 범위 (OS 기반, 화면 단)
        //ApplicationContext <- 가지고 있는 범위 (OS 기반, OS랑 백그라운드)
        //Context(Activity)에서 받아왔을 때 안되는 경우에는 바꿔주면 됨
        //어디에 어떤 Context가 사용되는지를 외우면 안됨
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getApplicationContext(), android.R.layout.simple_list_item_1, list
        );
        listView.setAdapter(adapter);
    }
}