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
public class MainCtvWorkActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_ctvwork);
        PreferencesManager preferencesManager = PreferencesManager.getInstance(this);
        String hoTen = preferencesManager.getHoTen();
        // Các nút khác
        ImageButton btnHistory = findViewById(R.id.btnHistory);
        ImageButton btnSupport = findViewById(R.id.btnSupport);
        ImageButton btnProfile = findViewById(R.id.btnProfile);
        TextView notifyTextView = findViewById(R.id.Notify); // TextView Notify
        // Hiển thị tên người dùng vào TextView
        TextView tvHello = findViewById(R.id.appName);
        if (hoTen != null) {
            // Nếu đã đăng nhập, hiển thị tên người dùng
            tvHello.setText("Xin chào " + hoTen);
        } else {
            // Nếu chưa đăng nhập, hiển thị thông báo
            tvHello.setText("Xin chào, vui lòng đăng nhập!");
        }
        // Xử lý sự kiện click cho btnHistory
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainCtvWorkActivity.this, CtvWelfActivity.class);
                startActivity(intent);
            }
        });

        // Xử lý sự kiện click cho btnSupport
        btnSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainCtvWorkActivity.this, MessChatActivity.class);
                startActivity(intent);
            }
        });
        btnProfile.setOnClickListener(v -> {
            if (hoTen != null) {
                // Nếu đã đăng nhập, chuyển sang AccountActivity
                Intent intent = new Intent(MainCtvWorkActivity.this, AccountActivity.class);
                startActivity(intent);
            } else {
                // Nếu chưa đăng nhập, chuyển sang AccActivity
                Intent intent = new Intent(MainCtvWorkActivity.this, MainCtvActivity.class);
                startActivity(intent);
            }
        });
        // Xử lý sự kiện click cho TextView Notify
        notifyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainCtvWorkActivity.this, MainCtvActivity.class);
                startActivity(intent);
            }
        });
        // Nhận dữ liệu từ Intent
        String jobTitle = getIntent().getStringExtra("job_title");
        String jobTime = getIntent().getStringExtra("job_time");
        String jobDate = getIntent().getStringExtra("job_date");
        String jobDuration = getIntent().getStringExtra("job_duration");
        String jobPrice = getIntent().getStringExtra("job_price");
        String jobAddress = getIntent().getStringExtra("job_address");

        // Ánh xạ các TextView trong giao diện
        TextView tvJobTitle = findViewById(R.id.tvJobTitle);
        TextView tvJobTime = findViewById(R.id.tvJobTime);
        TextView tvDate = findViewById(R.id.tvDate);
        TextView tvJobDuration = findViewById(R.id.tvJobDuration);
        TextView tvJobPrice = findViewById(R.id.tvJobPrice);
        TextView tvJobAddress = findViewById(R.id.tvJobAddress);
        // Hiển thị dữ liệu
        tvJobTitle.setText(jobTitle);
        tvJobTime.setText(jobTime);
        tvDate.setText(jobDate);
        tvJobDuration.setText(jobDuration);
        tvJobPrice.setText(jobPrice);
        tvJobAddress.setText(jobAddress);
    }
}
