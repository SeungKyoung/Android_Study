package com.example.myprojectx;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myprojectx.DTO.MemberDTO;

import java.util.ArrayList;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ItemViewHolder>{
    private static final String TAG = "main:MemberAdapter";

    // 나를 부르는곳에서 반드시 넘겨받아야 하는것
    Context mContext;
    ArrayList<MemberDTO> dtos;

    LayoutInflater inflater;

    // 생성자로 메인에게 넘겨받은것을 연결
    public MemberAdapter(Context mContext, ArrayList<MemberDTO> dtos) {
        this.mContext = mContext;
        this.dtos = dtos;

        inflater = LayoutInflater.from(this.mContext);
    }

    ////////////////////////////////////////////////////////

    // 매소드는 여기에 만든다
    // dtos에 dto 추가하는 매소드
    public void addDto(MemberDTO dto){
        dtos.add(dto);
    }

    // dtos의 특정위치에 dto를 삭제할수 있는 매소드
    public void delDto(int position){
        dtos.remove(position);
    }

    ////////////////////////////////////////////////////////

    // 화면을 인플레이트 시킨다 : 어떤 xml을 사용할것인지 정한다
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.member_view,
                parent, false);

        return new ItemViewHolder(itemView);
    }

    // 인플레이트 시킨 화면에 데이터를 셋팅시킨다
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + position);

        // dtos에 있는 데이터를 각각 불러온다
        MemberDTO dto = dtos.get(position);
        Log.d(TAG, "onBindViewHolder: " + dto.getImgpath());

        // 불러온 데이터를 ViewHolder에 만들어 놓은 setDto를 사용하여 데이터 셋팅시킨다
        holder.setDto(dto);
    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        // singerview.xml 에 사용된 모든 위젯을 정의한다
        public LinearLayout parentLayout;
        public ImageView iv_img;
        public TextView tv_id, tv_name, tv_phonenum;

        // singerview에 정의한 아이디를 찾아 연결시킨다(생성자)
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.parentLayout);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_phonenum = itemView.findViewById(R.id.tv_phonenum);
            iv_img = itemView.findViewById(R.id.iv_img);

        }

        // 함수를 만들어서 singerview에 데이터를 연결시킨다.
        public void setDto(MemberDTO dto){
            tv_id.setText(dto.getId());
            tv_name.setText(dto.getName());
            tv_phonenum.setText(dto.getPhonenumber());

            // Glide.with(this)
            // .load("http://www.selphone.co.kr/homepage/img/team/3.jpg")
            // .into(imageView);
            Glide.with(mContext)
                    .load(dto.getImgpath())
                    .circleCrop()
                    .into(iv_img);
        }

    }

}
