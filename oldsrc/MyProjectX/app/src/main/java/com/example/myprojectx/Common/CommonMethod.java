package com.example.myprojectx.Common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.myprojectx.DTO.MemberDTO;

// 어느곳에서나 접근이 가능한 변수나 매소드를 ststic으로 생성한다
public class CommonMethod {
    // 나의 ip를 선언해 놓는다
    public static String ipConfig = "http://192.168.0.44:8989";

    // 로그인이 되었을때 loginDTO에 입력해 놓을 dto를 생성한다
    public static MemberDTO loginDTO = null;


    // 네트워크에 연결되어 있는가
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService( Context.CONNECTIVITY_SERVICE );
        NetworkInfo info = cm.getActiveNetworkInfo();
        if(info != null){
            if(info.getType() == ConnectivityManager.TYPE_WIFI){
                Log.d("isconnected : ", "WIFI 로 설정됨");
            }else if(info.getType() == ConnectivityManager.TYPE_MOBILE){
                Log.d("isconnected : ", "일반망으로 설정됨");
            }
            Log.d("isconnected : ", "OK => " + info.isConnected());
            return true;
        }else {
            Log.d("isconnected : ", "False => 데이터 통신 불가!!!" );
            return false;
        }

    }

}
