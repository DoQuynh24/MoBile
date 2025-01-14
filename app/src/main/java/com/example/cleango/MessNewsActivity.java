package com.example.cleango;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MessNewsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mess_news);
        // Gán các nút bằng ID
        ImageButton btnHistory = findViewById(R.id.btnHistory);
        ImageButton btnHome = findViewById(R.id.btnHome);
        ImageButton btnProfile = findViewById(R.id.btnProfile);
        TextView btnMess = findViewById(R.id.btnMess); // TextView Notify

        // Xử lý sự kiện click cho btnHome
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessNewsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Xử lý sự kiện click cho btnHistory
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessNewsActivity.this, WorkPackageActivity.class);
                startActivity(intent);
            }
        });

        // Xử lý sự kiện click cho btnProfile
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessNewsActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });
        // Xử lý sự kiện click cho TextView Notify
        btnMess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessNewsActivity.this, MessChatActivity.class);
                startActivity(intent);
            }
        });
    }
}
