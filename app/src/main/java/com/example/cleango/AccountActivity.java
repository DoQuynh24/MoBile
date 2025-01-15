package com.example.cleango;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AccountActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);

        PreferencesManager preferencesManager = PreferencesManager.getInstance(this);
        String hoTen = preferencesManager.getHoTen();

        // Hiển thị tên người dùng vào TextView
        TextView tvHello = findViewById(R.id.appName);
        if (hoTen != null) {
            tvHello.setText(hoTen);
        }
        // Gán các nút với ID trong layout
        ImageButton btnHome = findViewById(R.id.btnHome);
        ImageButton btnHistory = findViewById(R.id.btnHistory);
        ImageButton btnSupport = findViewById(R.id.btnSupport);
        TextView btnInfor = findViewById(R.id.btnInfor);

        // Xử lý sự kiện click cho btnHome (trở về trang chính)
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Xử lý sự kiện click cho btnHistory (chuyển đến work_package.xml)
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, WorkPackageActivity.class);
                startActivity(intent);
            }
        });

        // Xử lý sự kiện click cho btnSupport (chuyển đến mess_chat.xml)
        btnSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, MessChatActivity.class);
                startActivity(intent);
            }
        });

        // Xử lý sự kiện click cho btnInfor (chuyển đến infor.xml)
        btnInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, InforActivity.class);
                startActivity(intent);
            }
        });
    }
}
