package com.example.cleango;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AccActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acc); // Kết nối với layout acc.xml

        // Tìm các nút TextView theo ID
        TextView btnLogin = findViewById(R.id.btnLogin);
        TextView btnRegister = findViewById(R.id.btnRegister);
        ImageButton btnHome = findViewById(R.id.btnHome);
        ImageButton btnHistory = findViewById(R.id.btnHistory);
        ImageButton btnSupport = findViewById(R.id.btnSupport);
        // Xử lý sự kiện click cho btnLogin
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang giao diện login.xml
                Intent intent = new Intent(AccActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // Xử lý sự kiện click cho btnRegister
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang giao diện register.xml
                Intent intent = new Intent(AccActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        // Xử lý sự kiện click cho btnHome (trở về trang chính)
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Xử lý sự kiện click cho btnHistory (chuyển đến work_package.xml)
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccActivity.this, WorkPackageActivity.class);
                startActivity(intent);
            }
        });

        // Xử lý sự kiện click cho btnSupport (chuyển đến mess_chat.xml)
        btnSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccActivity.this, MessChatActivity.class);
                startActivity(intent);
            }
        });

    }
}
