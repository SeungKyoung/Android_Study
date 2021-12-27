package com.example.miniproject.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.miniproject.Atask.AskTask;
import com.example.miniproject.DTO.UserDTO;
import com.example.miniproject.R;
import com.example.miniproject.activity.UserMainActivity;
import com.example.miniproject.common.Common;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

public class UserMainTab extends Fragment {
    UserMainActivity activity;
    TextView tv;
    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.tab_listview
                , container
                , false);

        activity = (UserMainActivity) getActivity();
        tv = rootView.findViewById(R.id.main_tv);
        if(Common.dto != null){
            tv.setText(Common.dto.getId() + "\r\n"+ Common.dto.getName() + "\r\n" + Common.dto.getBirth() );
        }
        return rootView;
    }
}