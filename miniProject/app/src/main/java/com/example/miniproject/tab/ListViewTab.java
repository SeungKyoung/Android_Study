package com.example.miniproject.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.miniproject.Atask.AskTask;
import com.example.miniproject.DTO.ListViewDTO;
import com.example.miniproject.R;
import com.example.miniproject.activity.UserMainActivity;
import com.example.miniproject.adapter.ListViewAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListViewTab extends Fragment {
    ListView listView;
    UserMainActivity activity;
    ListViewAdapter adapter;

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
        listView = rootView.findViewById(R.id.tab_listview);

        AskTask conn = new AskTask("listview.test");
        try {
            InputStream is = conn.execute().get();
            Gson gson = new Gson();
            List<ListViewDTO> list = gson.fromJson(new InputStreamReader(is) ,
                    new TypeToken<List<ListViewDTO>>(){}.getType());
            adapter = new ListViewAdapter(activity.getApplicationContext()
                    , list
            );
            listView.setAdapter(adapter);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listView.setAdapter(adapter);
        return rootView;
    }
}