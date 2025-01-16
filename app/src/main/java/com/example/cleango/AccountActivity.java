package com.example.cleango;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
        TextView btnLogOut = findViewById(R.id.btnLogOut);

        // Xử lý sự kiện click cho btnHome
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferencesManager preferencesManager = PreferencesManager.getInstance(AccountActivity.this);
                String role = preferencesManager.getRole(); // Lấy vai trò từ SharedPreferences

                Intent intent;
                if ("Cộng tác viên".equals(role)) {
                    intent = new Intent(AccountActivity.this, MainCtvActivity.class); // Chuyển đến MainCtvActivity nếu là Cộng tác viên
                } else if ("Thành viên".equals(role)) {
                    intent = new Intent(AccountActivity.this, MainActivity.class); // Chuyển đến MainActivity nếu là Thành viên
                } else {
                    // Nếu vai trò không hợp lệ, có thể hiển thị thông báo lỗi hoặc không làm gì
                    return;
                }
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

        // Ánh xạ nút Đăng xuất
        btnLogOut = findViewById(R.id.btnLogOut);

        // Xử lý sự kiện click Đăng xuất
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị hộp thoại xác nhận
                new AlertDialog.Builder(AccountActivity.this)
                        .setTitle("Xác nhận")
                        .setMessage("Bạn có chắc chắn muốn đăng xuất không?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Chuyển sang acc.xml
                                Intent intent = new Intent(AccountActivity.this, AccActivity.class); // Đảm bảo bạn đã có AccActivity
                                startActivity(intent);
                                finish(); // Đóng màn hình hiện tại
                            }
                        })
                        .setNegativeButton("Không", null)
                        .show();
            }
        });
    }
}
