package com.example.myprojectx.ATask;

import static com.example.myprojectx.Common.CommonMethod.ipConfig;

import android.content.Context;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.myprojectx.DTO.MemberDTO;
import com.example.myprojectx.MemberAdapter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MemberSelect extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "main:MemberSelect";

    // 생성자를 만들어서 데이터를 받는다
    //Context context;  // 필요하면 받는다
    ArrayList<MemberDTO> dtos;
    MemberAdapter adapter;

    public MemberSelect(ArrayList<MemberDTO> dtos, MemberAdapter adapter) {
        this.dtos = dtos;
        this.adapter = adapter;
    }

    // 반드시 선언해야 할것들 : 무조건 해야함  복,붙
    HttpClient httpClient;       // 클라이언트 객체
    HttpPost httpPost;           // 클라이언트에 붙일 본문
    HttpResponse httpResponse;   // 서버에서의 응답받는 부분
    HttpEntity httpEntity;       // 응답내용

    // 실질적으로 일을하는 부분 : 접근 못함(textView, button)
    @Override
    protected Void doInBackground(Void... voids) {

        try {
            // MultipartEntityBuilder 생성 : 무조건 해야함  복,붙
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            // 전송
            // 전송 url : 우리가 수정해야 하는 부분
            String postURL = ipConfig + "/app/anSelectMember";
            // 그대로 사용  복,붙
            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost);  // 보내고 응답 받는 부분
            httpEntity = httpResponse.getEntity();    // 응답내용을 저장
            inputStream = httpEntity.getContent();    // 응답내용을 inputStream 에 넣음

            // 데이터가 ArrayList<DTO> 형태일때
            readJsonStream(inputStream);


        }catch (Exception e){
            e.getMessage();
        }finally {
            if(httpEntity != null){
                httpEntity = null;
            }
            if(httpResponse != null){
                httpResponse = null;
            }
            if(httpPost != null){
                httpPost = null;
            }
            if(httpClient != null){
                httpClient = null;
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);

        Log.d(TAG, "onPostExecute: List Select Complete !!!" );

        // 데이터가 새로 삽입되어서 화면 갱신을 해준다
        adapter.notifyDataSetChanged();
    }

    public void readJsonStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                dtos.add(readMessage(reader));
            }
            reader.endArray();
        } finally {
            reader.close();
        }
    }

    public MemberDTO readMessage(JsonReader reader) throws IOException {
        String id = "", name = "", phonenum = "", address = "", imgpath = "";

        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if (readStr.equals("id")) {
                id = reader.nextString();
            } else if (readStr.equals("name")) {
                name = reader.nextString();
            } else if (readStr.equals("phonenumber")) {
                phonenum = reader.nextString();
            } else if (readStr.equals("address")) {
                address = reader.nextString();
            }else if (readStr.equals("filename")) {
                imgpath = ipConfig + "/app/resources/" + reader.nextString();
            }else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Log.d(TAG, id + "," + name + "," + phonenum + "," + address + "," + imgpath);
        return new MemberDTO(id, name, phonenum, address, imgpath);
    }

}
