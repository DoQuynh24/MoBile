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
        // Xử lý sự kiện click cho btnHome
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferencesManager preferencesManager = PreferencesManager.getInstance(WorkRepeatActivity.this);
                String role = preferencesManager.getRole(); // Lấy vai trò từ SharedPreferences

                Intent intent;
                if ("Cộng tác viên".equals(role)) {
                    intent = new Intent(WorkRepeatActivity.this, MainCtvActivity.class); // Chuyển đến MainCtvActivity nếu là Cộng tác viên
                } else if ("Thành viên".equals(role)) {
                    intent = new Intent(WorkRepeatActivity.this, MainActivity.class); // Chuyển đến MainActivity nếu là Thành viên
                } else {
                    intent = new Intent(WorkRepeatActivity.this, MainActivity.class);
                }
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
        PreferencesManager preferencesManager = PreferencesManager.getInstance(this);
        String hoTen = preferencesManager.getHoTen();
        btnProfile.setOnClickListener(v -> {
            if (hoTen != null) {
                // Nếu đã đăng nhập, chuyển sang AccountActivity
                Intent intent = new Intent(WorkRepeatActivity.this, AccountActivity.class);
                startActivity(intent);
            } else {
                // Nếu chưa đăng nhập, chuyển sang AccActivity
                Intent intent = new Intent(WorkRepeatActivity.this, AccActivity.class);
                startActivity(intent);
            }
        });
    }
}
