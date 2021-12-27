package com.example.safingproject.Atask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AskTask extends AsyncTask<String,String,InputStream> {
    //==================인터넷 연결 필드 선언=========================

    HttpClient httpClient;          //접속을 위한객체
    HttpPost httpPost;              //url을 담을 객체
    MultipartEntityBuilder builder; //파라메터,파일 등등을 보내기위한 객체
    final String HTTPIP = "http://192.168.0.12";//IP
    final String SVRPATH = "/safing/";  //path
    private String postUrl ;            //실제 url
    String mapping ;                    //mapping 선언
    ArrayList<String> params ;          //DB로 보낼 데이터

    //======================= 생성자  ===========================

    //mapping만 받는 생성자
    public AskTask(String mapping) {
        this.mapping = mapping;
    }

    //DB에 보낼 데이터 받는 생성자
    public AskTask(String mapping , ArrayList<String> params) {
        this.mapping = mapping;
        this.params = params;
    }

    //================= 실제 데이터 주고 받는 부분  ================

    @Override
    protected InputStream doInBackground(String... strings) {

        //================= url 연결 부분  ================
        postUrl = HTTPIP + SVRPATH + mapping;       //실제 url
        builder = MultipartEntityBuilder.create();  //DB로 데이터 보내는 메소드
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

        //================= DB로 데이터 보내는 부분  ================

        //데이터 사이즈 보냄
        String size = params.size()+"";
        builder.addTextBody("size", size,
                ContentType.create("Multipart/related" , "UTF-8"));

        //데이터 보냄
        for (int i = 0 ; i<params.size() ; i ++){
            builder.addTextBody("param" + (i+1) , params.get(i) ,
                    ContentType.create("Multipart/related" , "UTF-8"));
        }
        httpClient = AndroidHttpClient.newInstance("Android");

        //================= DB에서 데이터 받는 부분  ================

        httpPost = new HttpPost(postUrl);
        httpPost.setEntity(builder.build());
        InputStream in = null; //데이터는 담는 클래스 초기화
        try {
            in = httpClient.execute(httpPost).getEntity().getContent();
            //DB에서 데이터 받아옴
        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }//doInBackground()
}
