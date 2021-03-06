package com.example.myprojectx.ATask;

import static com.example.myprojectx.Common.CommonMethod.ipConfig;
import static com.example.myprojectx.Common.CommonMethod.loginDTO;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.myprojectx.DTO.MemberDTO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class LoginSelect extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "main:LoginSelect";

    String id, pw;

    public LoginSelect(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    // 반드시 선언해야 할것들 : 무조건 해야함  복,붙
    HttpClient httpClient;       // 클라이언트 객체
    HttpPost httpPost;           // 클라이언트에 붙일 본문
    HttpResponse httpResponse;   // 서버에서의 응답받는 부분
    HttpEntity httpEntity;       // 응답내용

    // doInBackground 하기전에 설정 및 초기화 : 여기서는 사용않함
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    // 실질적으로 일을하는 부분 : 접근 못함(textView, button)
    @Override
    protected Void doInBackground(Void... voids) {

        try {
            // MultipartEntityBuilder 생성 : 무조건 해야함  복,붙
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            // 여기가 우리가 수정해야 하는 부분 : 서버로 보내는 데이터
            // builder에 문자열 및 데이터 추가하기
            builder.addTextBody("id", id, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("passwd", pw, ContentType.create("Multipart/related", "UTF-8"));

            // 전송
            // 전송 url : 우리가 수정해야 하는 부분
            String postURL = ipConfig + "/app/anLogin";
            // 그대로 사용  복,붙
            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost);  // 보내고 응답 받는 부분
            httpEntity = httpResponse.getEntity();    // 응답내용을 저장
            inputStream = httpEntity.getContent();    // 응답내용을 inputStream 에 넣음

            // 응답 처리 : DTO 형태
            loginDTO = readMessage(inputStream);

            inputStream.close();

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

    // doInBackground 끝난후에 실행하는 부분
    @Override
    protected void onPostExecute(Void voids) {
        super.onPostExecute(voids);
    }

    // 하나의 DTO형태로 데이터를 받을때 파싱하는 부분
    private MemberDTO readMessage(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8" ));

        String id="", name="", phonenumber="", address="", imgpath="";
        reader.beginObject();
        while (reader.hasNext()){
            String readStr = reader.nextName();
            if(readStr.equals("id")){
                id = reader.nextString();
            }else if(readStr.equals("name")){
                name = reader.nextString();
            }else if(readStr.equals("phonenumber")){
                phonenumber = reader.nextString();
            }else if(readStr.equals("address")){
                address = reader.nextString();
            }else if(readStr.equals("filename")){
                imgpath = ipConfig + "/app/resources/" + reader.nextString();
            }else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Log.d(TAG, "readMessage: " + id + ", " + name);
        return new MemberDTO(id, name, phonenumber, address, imgpath);
    }

}
