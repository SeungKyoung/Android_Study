package com.example.lastproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        //네비게이션 드로어 생성해서 리스터 붙이기 : res-strings으로
        //정의해놓은 값을 사용할 예정
        DrawerLayout drawer = findViewById(R.id.draw_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer, //drawrLayout <-> toolbar
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_claose
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView view = findViewById(R.id.nav_view);
        view.setNavigationItemSelectedListener(this);

        View headerView = view.getHeaderView(0);
        ImageView loginImage = headerView.findViewById(R.id.loginImage);
        Glide.with(MainActivity.this)
                .load("https://pds.joongang.co.kr//news/component/htmlphoto_mmdata/201710/07/4593bef7-ddf4-4f6c-a736-cafedfb6a1fe.gif")
                .into(loginImage);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home){
            Toast.makeText(MainActivity.this, "Home연결해야함", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, CameraActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawerLayout = findViewById(R.id.draw_layout);
        drawerLayout.closeDrawer(GravityCompat.START);

        return false;
    }
}