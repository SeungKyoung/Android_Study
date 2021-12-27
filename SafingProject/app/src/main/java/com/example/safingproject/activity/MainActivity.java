package com.example.safingproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.safingproject.R;
import com.example.safingproject.navigation.HomeNavigation;
import com.example.safingproject.navigation.IotNavigation;
import com.example.safingproject.navigation.MoiveNavigation;
import com.example.safingproject.navigation.MyNavigation;
import com.example.safingproject.navigation.ShoppingNavigation;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottom_nav;
    private long backKeyPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottom_nav = findViewById(R.id.bottom_nav);

        changeFragment(new HomeNavigation(), "Home");

        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.tab1){
                    changeFragment(new HomeNavigation(), "Home");
                    return true;
                }else if(item.getItemId() == R.id.tab2){
                    changeFragment(new MoiveNavigation() , "Moive");
                    return true;
                }else if(item.getItemId() == R.id.tab3){
                    changeFragment(new IotNavigation() , "IoT");
                    return true;
                }else if(item.getItemId() == R.id.tab4){
                    changeFragment(new ShoppingNavigation() , "Shopping");
                    return true;
                }else if(item.getItemId() == R.id.tab5){
                    changeFragment(new MyNavigation() , "My");
                    return true;
                }
                return false;
            }
        });
    }
    public void changeFragment(Fragment fragment , String msg){
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.container , fragment).commit();
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2500) {
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(this, "한 번 더 누르시면 종료됩니다.", Toast.LENGTH_LONG);
            return;
        }

        if (System.currentTimeMillis() <= backKeyPressedTime + 2500) {
            finish();
        }
    }
}