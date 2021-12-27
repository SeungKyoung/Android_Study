package com.example.miniproject.navigation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.miniproject.MainActivity;
import com.example.miniproject.R;
import com.example.miniproject.activity.LoginActivity;
import com.example.miniproject.activity.RegisterActivity;

public class MyNavigation extends Fragment {
    Button my_btn1, my_btn2;
    MainActivity activity;
    Intent intent;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.navigation_my
                , container
                , false);

        my_btn1 = rootView.findViewById(R.id.my_btn1);
        my_btn2 = rootView.findViewById(R.id.my_btn2);

        my_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Register", Toast.LENGTH_SHORT).show();
                intent = new Intent(activity, RegisterActivity.class);
                startActivity(intent);
            }
        });
        my_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(activity, LoginActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}