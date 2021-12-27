package com.example.project01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_alert;
    ScrollView scroll1;
    View view1, view2;
    TextView tv1; //view <- 손은 대거나 눌렀을 때 등등의 이벤트가 일어났을 때 보여지게 끔.

    //제스처를 사용하기 위한 객체
    GestureDetector detector;


    public static boolean isDown = false;
    private static final String TAG = "MainActivity";
    //progressbar <- MainActivity 를 이용하는 방법
    //static . method, static progressbar
    public static ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_alert = findViewById(R.id.btn_alert);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setTag("N");

        //제스처용 위젯
        scroll1 = findViewById(R.id.scroll1);
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        tv1 = findViewById(R.id.tv1);

        view1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //손가락이 눌렸는지, 움직였는지 action 1.움직임 2.눌림
                int action = event.getAction();
                float curX = event.getX();
                float curY = event.getY();
                if(action == MotionEvent.ACTION_DOWN){
                    println("손가락이 눌림 X : " + curX + " -Y : " + curY);
                } else if(action == MotionEvent.ACTION_MOVE){
                    println("손가락이 움직임 X : " + curX + " -Y : " + curY);
                } else if(action == MotionEvent.ACTION_UP){
                    println("손가락이 떼어짐 X : " + curX + " -Y : " + curY);
                }
                return true;
            }
        });

        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });

        detector = new GestureDetector(MainActivity.this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                println("눌림 X : " + e.getX() + " -Y : " + e.getY());
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                println("떼어짐 X : " + e.getX() + " -Y : " + e.getY());

            }

            //한손가락으로 눌렀다가 떼어지는 경우(판단하기 애매함)
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                println("onSingleTapUp X : " + e.getX() + " -Y : " + e.getY());
                return true;
            }

            //화면이 눌린채 일정한 속도와 방향으로 움직였다 떼어지는 경우
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                println("일점함 X : " + distanceX + " -Y : " + distanceY);
                return true;
            }

            //오래 눌렀을 때 onLongPress
            @Override
            public void onLongPress(MotionEvent e) {
                println("오래눌림 X : " + e.getX() + " -Y : " + e.getY());

            }

            //화면이 눌려진채 가속도를 붙여서 손가락을 움직인 경우
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                println("가속도 X : " + velocityX + " -Y : " + velocityY);
                return true;
            }
        });

        //중요함 키를 눌렀을 때 이벤트


        btn_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AlertDilog Custom 인터넷 예제보고 공부해보기
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("안내");
                builder.setMessage("Service를 이용해서 파일 다운로드");
                builder.setIcon(android.R.drawable.ic_dialog_alert);//android자체에서 제공하는 icon모양.
                //builder를 이용해서 생성을 하고 있지만 실제로 띄우는 코드를 넣지 않음.

                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //syso logb
                        if(!isDown) {
                            isDown = true;
                            Log.d(TAG, "MainActivity : " + which);
                            Toast.makeText(MainActivity.this, "예 눌림", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, FileDownLoadService.class);
                            intent.putExtra("command", "작업시작");
                            startService(intent);
                        }
                    }
                });

                //builder.nagative 아니오 눌림 Toast
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "MainActivity : " + which);
                        Toast.makeText(MainActivity.this, "아니오 눌림", Toast.LENGTH_SHORT).show();
                        //Service라는 것을 만들고 메인액티비티와 별개로 작업을 하게끔 만듬.
                    }
                });

                //setNeutral 취소눌림 Toast
                builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "MainActivity : " + which);
                        Toast.makeText(MainActivity.this, "취소 눌림", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog dialog  = builder.create();
                dialog.show();
            }
        });
    }//onCreate

    //****중요함 키를 눌렀을 때 이벤트***

    //key를 눌렀을 때 동작하는 메소드
    //C# winform <-, java / keycode if문으로 분기해서 사용하는 거랑 거의 똑같음.
    //알아두면 개발에 매우 도움됨.
    //ex) 뒤로가기를 두번 눌렀을 때 체크, -> AlertDilag(정말 종료하시겠습니까?)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //keyCode -> 0101010 -> int 형으로 바꿔서 줌, 우리는 int gud keyCode만 알면됨.
        //미리 상수로 정의 되어있음(final)
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            //alert 창을 띄위서 정말 뒤로 가시겠습니까?
            //예를 누르면 Splash가 뜸
            //아니오를 누르면 그대로 화면 유지

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("종료");
            builder.setMessage("정말 종료하시겠습니까?");
            builder.setIcon(android.R.drawable.ic_dialog_alert);

            builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "MainActivity : " + which);
                        Toast.makeText(MainActivity.this, "예 눌림", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                        startActivity(intent);
                }
            });

            builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.d(TAG, "MainActivity : " + which);
                    Toast.makeText(MainActivity.this, "아니오 눌림", Toast.LENGTH_SHORT).show();
                }
            });

            AlertDialog dialog  = builder.create();
            dialog.show();
            return true;
        } else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            println("볼륨이 업됨.");
        } else if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            println("볼륨이 다움됨.");
        }
        return super.onKeyDown(keyCode, event);
    }

    public  void println(String data){
        tv1.append(data + "\n");
        scroll1.fullScroll(View.FOCUS_DOWN);//스크롤뷰가 영역이 있으면 맨 아래로 내리는 처리

    }

}