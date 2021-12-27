package com.example.myprojectx;

import static com.example.myprojectx.Common.CommonMethod.loginDTO;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myprojectx.ATask.LoginSelect;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "main:LoginActivity";

    EditText etId, etPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        checkDangerousPermissions();

        etId = findViewById(R.id.etId);
        etPw = findViewById(R.id.etPASSWD);

        // 회원가입
        findViewById(R.id.btnJoin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,
                        JoinActivity.class);
                startActivity(intent);
            }
        });

        // 로그인
        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 에디트텍스트에 있는 데이터를 가져온다
                if(etId.getText().toString().length() > 0 &&
                        etPw.getText().toString().length() > 0){
                    String id = etId.getText().toString();
                    String pw = etPw.getText().toString();

                    // 서버로 데이터를 보낸다
                    LoginSelect loginSelect = new LoginSelect(id, pw);
                    try {
                        loginSelect.execute().get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(LoginActivity.this,
                            "아이디나 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    etId.requestFocus();
                    return;
                }

                if(loginDTO != null){
                    Log.d(TAG, "onClick: " + loginDTO.getId());
                    // 서버에서 받은 데이터를 확인해서 로그인 정보가 있으면 loginDTO에
                    // 넣고 메인을 부른다 그리고 로그인 화면을 제거한다
                    Intent intent = new Intent(LoginActivity.this,
                            MainActivity.class);
                    startActivity(intent);

                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,
                            "아이디나 비밀번호가 맞지 않습니다", Toast.LENGTH_SHORT).show();
                }

                
            }
        });
    }

    // 위험권한
    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_MEDIA_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


}