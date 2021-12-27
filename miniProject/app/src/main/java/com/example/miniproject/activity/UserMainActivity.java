package com.example.miniproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miniproject.DTO.UserDTO;
import com.example.miniproject.R;
import com.example.miniproject.tab.ListViewTab;
import com.example.miniproject.tab.UserMainTab;
import com.google.android.material.tabs.TabLayout;

public class UserMainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        tabLayout = findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Main"));
        tabLayout.addTab(tabLayout.newTab().setText("ListView"));
        changeFragment(new UserMainTab(), "Main");

        tabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position == 0){
                    changeFragment(new UserMainTab(), "Main");
                }else if(position == 1){
                    changeFragment(new ListViewTab(), "ListView");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void changeFragment(Fragment fragment, String msg){
        Toast.makeText(UserMainActivity.this, msg, Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.container , fragment).commit();
    }
}