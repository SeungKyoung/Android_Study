package com.example.new02_fragmentlistview.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.TextView;

import com.example.new02_fragmentlistview.R;
import com.example.new02_fragmentlistview.dto.PlayListDTO;

import java.util.ArrayList;

public class PlayListActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<PlayListDTO> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        listView = findViewById(R.id.playlistview);

        for(int i = 0 ; i < 50 ; i++){
            if(i%2 == 0){
                list.add(new PlayListDTO(R.drawable.heart, "Spaced out","Khailil wakes up finds"));
            }else {
                list.add(new PlayListDTO(R.drawable.user, "House in the clouds","Jonathan creates a new world"));
            }
        }
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        PlayListAdapter adapter = new PlayListAdapter(list, inflater);
        listView.setAdapter(adapter);
    }
}