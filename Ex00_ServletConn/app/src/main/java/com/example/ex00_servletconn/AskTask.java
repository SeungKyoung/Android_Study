package com.example.ex00_servletconn;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AskTask extends AsyncTask<String,String,String> {
    private static final String TAG = "common";
    HttpClient httpClient;//접속을 위한객체
    HttpPost httpPost; //url을 담을 객체
    HttpResponse httpResponse;//Middle <->And //미사용
    HttpEntity httpEntity; //속성을 정의함
    MultipartEntityBuilder builder;//파라메터,파일 등등을 보내기위한 객체
    final String HTTPIP = "http://192.168.0.60";//IP
    final String SVRPATH = "/mid/"; //
    private String postUrl ;
    String mapping;

    public AskTask(String mapping) {
        this.mapping = mapping;
    }

    @Override
    protected String doInBackground(String... strings) {

        postUrl = HTTPIP + SVRPATH + mapping;
        builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        //===========================================
        ArrayList<UserDTO> list = new ArrayList<>();
        Gson gson = new Gson();
        String data = gson.toJson(list);
        builder.addTextBody("dto" , data ,
                ContentType.create("Multipart/related" , "UTF-8"));
        //===========================================
        httpClient = AndroidHttpClient.newInstance("Android");
        httpPost = new HttpPost(postUrl);
        httpPost.setEntity(builder.build());
        InputStream in = null;
        try {
            in = httpClient.execute(httpPost).getEntity().getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
