package com.example.myprojectx;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myprojectx.ATask.JoinInsert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class JoinActivity extends AppCompatActivity {
    private static final String TAG = "main:JoinActivity";
    
    File imgFile = null;
    String imgFilePath = "", state = "";
    // 이미지처리가 정상적으로 되었을때 onActivityResult에서 데이터를 받기 위한 코드
    public int reqPicCode = 1004;

    EditText etId, etPasswd, etName, etPhoneNum, etAddress;
    Button btnJoin, btnCancel;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        imageView = findViewById(R.id.imageView);
        etPasswd = findViewById(R.id.etPasswd);
        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etPhoneNum = findViewById(R.id.etPhoneNum);
        etAddress = findViewById(R.id.etAddress);
        btnJoin = findViewById(R.id.btnJoin);
        btnCancel = findViewById(R.id.btnCancel);

        // 이미지뷰에 기본 이미지를 입력한다
        imageView.setImageResource(R.drawable.guest);
        // Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + R.drawable.guest).toString();

        // 가입
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 회원가입에 필요한 정보를 가져온다
                String id = etId.getText().toString();
                String passwd = etPasswd.getText().toString();
                String name = etName.getText().toString();
                String phonenumber = etPhoneNum.getText().toString();
                String address = etAddress.getText().toString();
                String imgpath =  imgFilePath;

                // 이정보를 비동기 Task로 넘겨 서버에게 전달한다
                JoinInsert joinInsert = new
                        JoinInsert(id, passwd, name, phonenumber, address, imgpath);
                try {
                    state = joinInsert.execute().get().trim();
                    Log.d(TAG, "onClick: state => " + state);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(state.equals("1")){
                    Toast.makeText(JoinActivity.this,
                            "정상적으로 회원가입이 되었습니다", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(JoinActivity.this,
                            "회원가입에 실패 하였습니다 다시 시도해주세요", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // 취소
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 이미지뷰를 클릭하면 사진을 찍어서 데이터를 저장한다
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 암묵적인텐트 : 사진찍기(카메라를 불러옴)
                Intent picIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // 일단 이 인텐트가 사용가능한지 체크
                if(picIntent.resolveActivity(getPackageManager()) != null){
                    imgFile = null;
                    // creatFile 매소드를 이용하여 임시파일을 만듬
                    imgFile = creatFile();

                    if(imgFile != null){
                        // API24 이상부터는 FileProvider를 제공해야함
                        Uri imgUri = FileProvider.getUriForFile(getApplicationContext(),
                                getApplicationContext().getPackageName()+".fileprovider",
                                imgFile);
                        // 만약에 API24 이상이면
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){ // API24
                            picIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
                        }else {
                            picIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imgFile));
                        }

                        startActivityForResult(picIntent, reqPicCode);
                    }

                }
            }
        });

    }


    private File creatFile() {
        // 파일 이름을 만들기 위해 시간값을 생성함
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());
        String imageFileName = "My" + timestamp;
        // 사진파일을 저장하기 위한 경로
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File curFile = null;
        try {
            // 임시파일을 생성함(전체경로),  2번째 suffix 확장자:파일확장자(jpg)
            curFile = File.createTempFile(imageFileName, ".jpg"
                    , storageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 스트링타입으로 임시파일이 있는 곳의 절대경로를 저장함
        imgFilePath = curFile.getAbsolutePath();

        return curFile;
    }

    // 사진찍은 후 데이터를 받는곳
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == reqPicCode && resultCode == RESULT_OK){
            // 저장처리를 함
            Toast.makeText(JoinActivity.this, "사진이 잘 찍힘", Toast.LENGTH_SHORT).show();

            setPic();
        }

    }

    // 사진을 저장처리 하는 곳
    private void setPic() {
        // 이미지뷰의 크기 알아오기
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // 사진의 크기 가져오기
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        int photoW = options.outWidth;
        int photoH = options.outHeight;

        // 이미지 크기를 맟출비율을 결정
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // 이미지뷰의 크기에 맞게 이미지크기를 조절
        options.inJustDecodeBounds = false;
        options.inSampleSize = scaleFactor;
        options.inPurgeable = true;

        // 비트맵 이미지를 생성
        Bitmap bitmap = BitmapFactory.decodeFile(imgFilePath);
        // 이미지를 갤러리에 저장하기
        gelleryAddPic(bitmap);
        // Glide.with(this).load("http://www.selphone.co.kr/homepage/img/team/3.jpg").into(imageView);
        Glide.with(this).load(bitmap).circleCrop().into(imageView);

        //imageView.setImageBitmap(bitmap);

        /*BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        Bitmap bitmap = BitmapFactory.decodeFile(imgFilePath);
        imageView.setImageBitmap(bitmap);*/

    }

    // 이미지를 갤러리에 저장하기
    private void gelleryAddPic(Bitmap bitmap) {
        FileOutputStream fos;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){ // API29
            ContentResolver resolver = getContentResolver();

            // 맵구조를 가진 ContentValues : 파일정보를 저장함
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME,
                    "Image_" + "jpg");
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE,
                    "image/jpeg");
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH,
                    Environment.DIRECTORY_PICTURES + File.separator + "TestFolder");

            Uri imageUri = resolver.insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    contentValues );
            try {
                fos = (FileOutputStream) resolver
                        .openOutputStream(Objects.requireNonNull(imageUri));
                Toast.makeText(JoinActivity.this,
                        "fos 작업됨", Toast.LENGTH_SHORT).show();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                Objects.requireNonNull(fos);

            }catch (Exception e){

            }

        }else {
            // 이미지 파일을 스캔해서 갤러리에 저장하기 위한 인텐트
            Intent msIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            // 처음에 CreateFile에서 생성해둔 이미지경로(imgFilePath)를 이용하여 파일객체를 만든다
            File f = new File(imgFilePath);
            Uri contentUri = Uri.fromFile(f);
            msIntent.setData(contentUri);
            // sendBroadcast를 이용하여 저장
            this.sendBroadcast(msIntent);
        }
    }




}