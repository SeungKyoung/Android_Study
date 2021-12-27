package com.example.new02_fragmentlistview.list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;

import com.example.new02_fragmentlistview.R;

import java.util.ArrayList;

public class ListCusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListView listView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cus);
        listView = findViewById(R.id.listcusview);
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0 ; i <50 ; i++){
            list.add("data" + i);
        }

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        CustomAdapter adapter = new CustomAdapter(list, inflater);
        listView.setAdapter(adapter);
    }
}