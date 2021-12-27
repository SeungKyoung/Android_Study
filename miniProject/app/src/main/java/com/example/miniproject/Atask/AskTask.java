package com.example.miniproject.Atask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import com.example.miniproject.DTO.UserDTO;
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

public class AskTask extends AsyncTask<String,String,InputStream> {
    private static final String TAG = "asktask";
    HttpClient httpClient;//접속을 위한객체
    HttpPost httpPost; //url을 담을 객체
    MultipartEntityBuilder builder;//파라메터,파일 등등을 보내기위한 객체
    final String HTTPIP = "http://192.168.0.12";//IP
    final String SVRPATH = "/00.MiniProject/"; //
    private String postUrl ;
    String mapping ;
    String id;
    UserDTO dto;
    ArrayList<UserDTO> list = new ArrayList<>();

    public AskTask(String mapping) {
        this.mapping = mapping;
    }

    public AskTask(String mapping , String id) {
        this.mapping = mapping;
        this.id = id;
    }

    public AskTask(String mapping , UserDTO dto) {
        this.mapping = mapping;
        this.dto = dto;
    }

    public void addItem(UserDTO dto){
        list.add(dto);
    }

    @Override
    protected InputStream doInBackground(String... strings) {

        postUrl = HTTPIP + SVRPATH + mapping;
        builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        //===========================================
        Gson gson = new Gson();
        String data = "";
        String data1 = gson.toJson(id);
        String data2 = gson.toJson(dto);
        builder.addTextBody("id", data1,
                ContentType.create("Multipart/related", "UTF-8"));
        builder.addTextBody("dtd", data2,
                ContentType.create("Multipart/related", "UTF-8"));
        for (int i = 0 ; i <list.size(); i++) {
            builder.addTextBody("param" + (i + 1), data,
                    ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("size", list.size()+"",
                    ContentType.create("Multipart/related", "UTF-8"));
        }
        //===========================================
        httpClient = AndroidHttpClient.newInstance("Android");
        httpPost = new HttpPost(postUrl);
        httpPost.setEntity(builder.build());
        InputStream   in = null;
        try {
            in = httpClient.execute(httpPost).getEntity().getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }
}
