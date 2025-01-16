package com.example.cleango;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WorkWaitingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_waiting);

        TextView tabRepeat = findViewById(R.id.tab_repeat);
        TextView tabPackage = findViewById(R.id.tab_package);
        ImageButton btnSupport = findViewById(R.id.btnSupport);
        ImageButton btnHome = findViewById(R.id.btnHome);
        ImageButton btnProfile = findViewById(R.id.btnProfile);

        // Xử lý sự kiện click cho tab_repeat
        tabRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkWaitingActivity.this, WorkRepeatActivity.class);
                startActivity(intent);
            }
        });

        // Xử lý sự kiện click cho tab_package
        tabPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkWaitingActivity.this, WorkPackageActivity.class);
                startActivity(intent);
            }
        });

        // Xử lý sự kiện click cho btnHome
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferencesManager preferencesManager = PreferencesManager.getInstance(WorkWaitingActivity.this);
                String role = preferencesManager.getRole(); // Lấy vai trò từ SharedPreferences

                Intent intent;
                if ("Cộng tác viên".equals(role)) {
                    intent = new Intent(WorkWaitingActivity.this, MainCtvActivity.class); // Chuyển đến MainCtvActivity nếu là Cộng tác viên
                } else if ("Thành viên".equals(role)) {
                    intent = new Intent(WorkWaitingActivity.this, MainActivity.class); // Chuyển đến MainActivity nếu là Thành viên
                } else {
                    intent = new Intent(WorkWaitingActivity.this, MainActivity.class);
                }
                startActivity(intent);
            }
        });

        // Xử lý sự kiện click cho btnSupport
        btnSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến mess_chat.xml
                Intent intent = new Intent(WorkWaitingActivity.this, MessChatActivity.class);
                startActivity(intent);
            }
        });

        PreferencesManager preferencesManager = PreferencesManager.getInstance(this);
        String hoTen = preferencesManager.getHoTen();
        btnProfile.setOnClickListener(v -> {
            if (hoTen != null) {
                // Nếu đã đăng nhập, chuyển sang AccountActivity
                Intent intent = new Intent(WorkWaitingActivity.this, AccountActivity.class);
                startActivity(intent);
            } else {
                // Nếu chưa đăng nhập, chuyển sang AccActivity
                Intent intent = new Intent(WorkWaitingActivity.this, AccActivity.class);
                startActivity(intent);
            }
        });
    }
}
