package com.example.cleango;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class WorkRepeatActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_repeat);
        TextView tabWaiting = findViewById(R.id.tab_waiting);
        TextView tabPackage = findViewById(R.id.tab_package);
        ImageButton btnSupport = findViewById(R.id.btnSupport);
        ImageButton btnHome = findViewById(R.id.btnHome);
        ImageButton btnProfile = findViewById(R.id.btnProfile);
        // Xử lý sự kiện click cho tab_waiting
        tabWaiting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkRepeatActivity.this, WorkWaitingActivity.class);
                startActivity(intent);
            }
        });
        // Xử lý sự kiện click cho tab_package
        tabPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkRepeatActivity.this, WorkPackageActivity.class);
                startActivity(intent);
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkRepeatActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến mess_chat.xml
                Intent intent = new Intent(WorkRepeatActivity.this, MessChatActivity.class);
                startActivity(intent);
            }
        });
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến account.xml
                Intent intent = new Intent(WorkRepeatActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });
    }
}
