package com.example.cleango;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.HorizontalScrollView;
import androidx.appcompat.app.AppCompatActivity;
public class CtvWelfActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ctv_welf);
        PreferencesManager preferencesManager = PreferencesManager.getInstance(this);
        String hoTen = preferencesManager.getHoTen();
        // Các nút khác
        ImageButton btnHome = findViewById(R.id.btnHome);
        ImageButton btnSupport = findViewById(R.id.btnSupport);
        ImageButton btnProfile = findViewById(R.id.btnProfile);
        // Xử lý sự kiện click cho btnHome
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferencesManager preferencesManager = PreferencesManager.getInstance(CtvWelfActivity.this);
                String role = preferencesManager.getRole(); // Lấy vai trò từ SharedPreferences

                Intent intent;
                if ("Cộng tác viên".equals(role)) {
                    intent = new Intent(CtvWelfActivity.this, MainCtvActivity.class); // Chuyển đến MainCtvActivity nếu là Cộng tác viên
                } else if ("Thành viên".equals(role)) {
                    intent = new Intent(CtvWelfActivity.this, MainActivity.class); // Chuyển đến MainActivity nếu là Thành viên
                } else {
                    intent = new Intent(CtvWelfActivity.this, MainActivity.class);
                }
                startActivity(intent);
            }
        });

        // Xử lý sự kiện click cho btnSupport
        btnSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CtvWelfActivity.this, MessChatActivity.class);
                startActivity(intent);
            }
        });
        btnProfile.setOnClickListener(v -> {
            if (hoTen != null) {
                // Nếu đã đăng nhập, chuyển sang AccountActivity
                Intent intent = new Intent(CtvWelfActivity.this, AccountActivity.class);
                startActivity(intent);
            } else {
                // Nếu chưa đăng nhập, chuyển sang AccActivity
                Intent intent = new Intent(CtvWelfActivity.this, AccActivity.class);
                startActivity(intent);
            }
        });
    }
}
