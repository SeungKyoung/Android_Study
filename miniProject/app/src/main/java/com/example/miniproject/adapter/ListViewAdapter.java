package com.example.miniproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miniproject.DTO.ListViewDTO;
import com.example.miniproject.DTO.UserDTO;
import com.example.miniproject.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    Context context ;
    List<ListViewDTO> dtos;
    LayoutInflater inflater;

    public ListViewAdapter(Context context, List<ListViewDTO> list) {
        this.context = context;
        this.dtos = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void addDTO(ListViewDTO dto){dtos.add(dto);}

    @Override
    public int getCount() {
        return dtos.size();
    }

    @Override
    public Object getItem(int position) {
        return dtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewHolder viewHolder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.listitemview,parent, false);
            viewHolder = new ListViewHolder();
            viewHolder.list_imgv1 = convertView.findViewById(R.id.list_imgv1);
            viewHolder.user_id = convertView.findViewById(R.id.list_id);
            viewHolder.user_msg = convertView.findViewById(R.id.list_msg);

            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ListViewHolder) convertView.getTag();
        }

        ListViewDTO dto = dtos.get(position);
        String user_id = dto.getUser_id();
        String user_msg = dto.getUser_msg();
        int ref_id = dto.getRefid();

        viewHolder.user_id.setText(user_id);
        viewHolder.user_msg.setText(user_msg);
        viewHolder.list_imgv1.setImageResource(ref_id);

        viewHolder.list_imgv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, dtos.get(position).getUser_id(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    public  class ListViewHolder{
        public ImageView list_imgv1;
        public TextView user_id , user_msg;
    }
}
