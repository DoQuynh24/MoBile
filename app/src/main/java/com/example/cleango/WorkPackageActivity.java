package com.example.cleango;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WorkPackageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_package);

        // Gán các TextView tab bằng ID
        TextView tabWaiting = findViewById(R.id.tab_waiting);
        TextView tabRepeat = findViewById(R.id.tab_repeat);
        ImageButton btnSupport = findViewById(R.id.btnSupport);
        ImageButton btnHome = findViewById(R.id.btnHome);
        ImageButton btnProfile = findViewById(R.id.btnProfile);
        // Xử lý sự kiện click cho tab_waiting
        tabWaiting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkPackageActivity.this, WorkWaitingActivity.class);
                startActivity(intent);
            }
        });

        // Xử lý sự kiện click cho tab_repeat
        tabRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkPackageActivity.this, WorkRepeatActivity.class);
                startActivity(intent);
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkPackageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến mess_chat.xml
                Intent intent = new Intent(WorkPackageActivity.this, MessChatActivity.class);
                startActivity(intent);
            }
        });
        PreferencesManager preferencesManager = PreferencesManager.getInstance(this);
        String hoTen = preferencesManager.getHoTen();
        btnProfile.setOnClickListener(v -> {
            if (hoTen != null) {
                // Nếu đã đăng nhập, chuyển sang AccountActivity
                Intent intent = new Intent(WorkPackageActivity.this, AccountActivity.class);
                startActivity(intent);
            } else {
                // Nếu chưa đăng nhập, chuyển sang AccActivity
                Intent intent = new Intent(WorkPackageActivity.this, AccActivity.class);
                startActivity(intent);
            }
        });
    }
}
