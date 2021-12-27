package com.example.new02_fragmentlistview.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.new02_fragmentlistview.R;
import com.example.new02_fragmentlistview.dto.PlayListDTO;

import java.util.ArrayList;

public class PlayListAdapter extends BaseAdapter {
    ArrayList<PlayListDTO> list;
    LayoutInflater inflater;

    public PlayListAdapter(ArrayList<PlayListDTO> list, LayoutInflater inflater) {
        this.list = list;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PlayListViewHolder holder = new PlayListViewHolder();
        if(convertView == null){
            convertView = inflater.inflate(R.layout.listitem2, parent, false);
            holder.playlist_count = convertView.findViewById(R.id.playlist_count);
            holder.playlist_image = convertView.findViewById(R.id.playlist_image);
            holder.playlist_tv1 = convertView.findViewById(R.id.playlist_tv1);
            holder.playlist_tv2 = convertView.findViewById(R.id.playlist_tv2);
            convertView.setTag(holder);
        }else {
            holder = (PlayListViewHolder) convertView.getTag();
        }

        holder.playlist_count.setText(position+1+"");
        holder.playlist_image.setImageResource(list.get(position).getImageId());
        holder.playlist_tv1.setText(list.get(position).getSubject());
        holder.playlist_tv2.setText(list.get(position).getSubject());

        return convertView;
    }




    public class PlayListViewHolder{
        public TextView playlist_count,playlist_tv1, playlist_tv2;
        public ImageView playlist_image;
    }
}

