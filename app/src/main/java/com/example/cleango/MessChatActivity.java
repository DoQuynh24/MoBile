package com.example.cleango;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MessChatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mess_chat);

        // Gán các nút bằng ID
        ImageButton btnHistory = findViewById(R.id.btnHistory);
        ImageButton btnHome = findViewById(R.id.btnHome);
        ImageButton btnProfile = findViewById(R.id.btnProfile);
        TextView notifyTextView = findViewById(R.id.Notify); // TextView Notify

        // Xử lý sự kiện click cho btnHome
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferencesManager preferencesManager = PreferencesManager.getInstance(MessChatActivity.this);
                String role = preferencesManager.getRole(); // Lấy vai trò từ SharedPreferences

                Intent intent;
                if ("Cộng tác viên".equals(role)) {
                    intent = new Intent(MessChatActivity.this, MainCtvActivity.class); // Chuyển đến MainCtvActivity nếu là Cộng tác viên
                } else if ("Thành viên".equals(role)) {
                    intent = new Intent(MessChatActivity.this, MainActivity.class); // Chuyển đến MainActivity nếu là Thành viên
                } else {
                    intent = new Intent(MessChatActivity.this, MainActivity.class);
                }
                startActivity(intent);
            }
        });
        // Xử lý sự kiện click cho btnHistory
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessChatActivity.this, WorkPackageActivity.class);
                startActivity(intent);
            }
        });
        PreferencesManager preferencesManager = PreferencesManager.getInstance(this);
        String hoTen = preferencesManager.getHoTen();
        btnProfile.setOnClickListener(v -> {
            if (hoTen != null) {
                // Nếu đã đăng nhập, chuyển sang AccountActivity
                Intent intent = new Intent(MessChatActivity.this, AccountActivity.class);
                startActivity(intent);
            } else {
                // Nếu chưa đăng nhập, chuyển sang AccActivity
                Intent intent = new Intent(MessChatActivity.this, AccActivity.class);
                startActivity(intent);
            }
        });
        // Xử lý sự kiện click cho TextView Notify
        notifyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessChatActivity.this, MessNewsActivity.class);
                startActivity(intent);
            }
        });
    }
}
