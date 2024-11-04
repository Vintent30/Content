package com.example.contentprovider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvent();
    }

    private void addEvent(){
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyMoManHinhDanhBa();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyMoManHinhTinNhan();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyMoManHinhCuocgoi();
            }
        });
    }

    private void xuLyMoManHinhDanhBa(){
        Intent intent = new Intent(MainActivity.this, DanhBa.class); // Sửa đổi ở đây
        startActivity(intent);
    }

    private void xuLyMoManHinhTinNhan() {
        Intent intent = new Intent(MainActivity.this, TinNhan.class);
        startActivity(intent);
    }
    private void xuLyMoManHinhCuocgoi() {
        Intent intent = new Intent(MainActivity.this, Call.class);
        startActivity(intent);
    }

    private void addControl(){
        btn1 = findViewById(R.id.btnLoadDB);
        btn2 = findViewById(R.id.btnLoadTN);
        btn3 = findViewById(R.id.btnLoadCL);
    }
}
