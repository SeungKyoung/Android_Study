package com.example.new02_fragmentlistview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.new02_fragmentlistview.dto.FragmentDTO;
import com.example.new02_fragmentlistview.fragments.Test01_Fragment;
import com.example.new02_fragmentlistview.fragments.Test02_Fragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
//activity : 앱과 상호작용을 위한 진입점 <- OS랑 연동이 되서 화면에 보여주는 처리를 함
//UI 화면은 보여주는 기능), Context(OS의 기능을 사용할 수 있는 권한, 제어권)
//Fragment : 화면을 보여주는 기능, Context를 가지고 있지 않음
//독립적으로 화면에 나타날 수가 없음 Activity 또는 다른 Fragment(Activity) 붙어야 나옴)
//Fragment <- FragmentManager <- Context

//ListView vs RecyclerView(ListView 보완해서 나옴)
//ListView 세로로만 리스트를 보여줄 수 있음 vs 가로, 세로, 지그재그(많이 사용안함)
//어댑터를 어느정도는 android제공 vs 직접 만들어야 함(커스터마이징 다양함)
//ViewHolder를 제공 안함(기본) vs 무조건 ViewHolder를 강제함 (포퍼먼스 다양)
//디자인적인 요소 적음 vs 디자인적인 요소 많음

//Fragment를 붙일 때 제일 많이 사용되는 Layout -> FrameLayout
//xml형식만 화면에 붙이기 때문에 화면 로딩이 빠름
    Button btn;
    ArrayList<FragmentDTO> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getSupportFragmentManager();
        //OS에서 제공을 해줌. (Context)

        Test01_Fragment fragment1 = new Test01_Fragment();
        Test02_Fragment fragment2 = new Test02_Fragment();
        //붙일 프래그먼트를 초기화

        list.add(new FragmentDTO(fragment1, "frag1"));
        list.add(new FragmentDTO(fragment2, "frag2"));

        btn = findViewById(R.id.main_btn);
        changeFrag(list.get(0).getFragment(), list.get(0).getMsg());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFrag(list.get(1).getFragment(), list.get(1).getMsg());
            }
        });
    }

    public void changeFrag(Fragment fragment, String msg){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}