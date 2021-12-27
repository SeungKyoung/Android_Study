package com.example.new02_fragmentlistview.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.new02_fragmentlistview.R;

import java.util.ArrayList;

//다른 사람 소스를 보고 어느 부분을 고치면 내가 사용할 수 있을까?
public class CustomAdapter extends BaseAdapter {
    //메인에서 넘겨받아야할 것들, ( Context, Item(ArrayList, 배열) );
    //Context <- LayoutInflater를 사용하기 위해
    //LayoutInflater 바로 받아와도 됨.
    ArrayList<String> list;
    LayoutInflater inflater;

    public CustomAdapter(ArrayList<String> list, LayoutInflater inflater) {
        this.list = list;
        this.inflater = inflater;
    }

    //data <-> xml 은 유기적인 관계
    //DTO 개수
    @Override
    public int getCount() {
        return list.size();
    }

    //getItem은 ArrayList에 있는 Element(요소)를 return해주는 부분.
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
        ListItemHolder holder = new ListItemHolder();
        //캐시 된 뷰(생성된 뷰) 없을 경우 새로 뷰홀더로 만들어주고 만든 객체를 Tag에 넣어줌
        //                      있을 경우 이미 만들어서 넣어둔 Tag에서 뷰홀더를 다시 빼서 리턴을 해준다.
        if(convertView == null){
            convertView = inflater.inflate(R.layout.listitem, parent, false);
            holder.list_tv = convertView.findViewById(R.id.list_tv);
            holder.list_image = convertView.findViewById(R.id.list_image);
            convertView.setTag(holder);
            //나중에 convertView(리스트에 있는 아이템) null 아닌 상태 이미 한번 생성
            //한 상태라면 미리 만들어놓은 위젯묶음을 리턴해서 사용가능하게 한다.
        }else {
            holder = (ListItemHolder) convertView.getTag();
        }


        holder.list_tv.setText(list.get(position));
        if(position%2 == 0){
            holder.list_image.setImageResource(R.drawable.heart);
        } else {
            holder.list_image.setImageResource(R.drawable.user);
        }


        return convertView;
    }

    //Recycler와 차이점 ※ 뷰홀더를 강제하지 않아서(xml 묶음) 직접 구현을 해야한다.
    //뷰홀더 : xml에 있는 widget의 묶음 , 뷰홀더로 묶인 객체를 Tag라는 곳에
    // 넣어두고 계속해서 뷰를 생성하는게 아니라 미리 생성이 되었으면 생성된 자원을 활용함.
    public class ListItemHolder{
       public TextView list_tv;
       public ImageView list_image;
    }
}
