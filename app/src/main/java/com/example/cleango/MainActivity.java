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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private HorizontalScrollView horizontalScrollView;
    private int scrollPosition = 0; // Biến giữ vị trí cuộn
    private Handler handler = new Handler(); // Handler để thực hiện cuộn
    private Runnable scrollRunnable = new Runnable() {
        @Override
        public void run() {
            // Lấy chiều rộng của HorizontalScrollView
            int width = horizontalScrollView.getWidth();

            // Lấy chiều rộng của tất cả các ảnh trong HorizontalScrollView
            int totalWidth = horizontalScrollView.getChildAt(0).getWidth();

            // Cập nhật vị trí cuộn
            scrollPosition += width;

            // Nếu đã cuộn hết các ảnh, quay lại đầu
            if (scrollPosition >= totalWidth) {
                scrollPosition = 0;
            }

            // Thực hiện cuộn mượt mà đến vị trí mới
            horizontalScrollView.smoothScrollTo(scrollPosition, 0);

            // Tiếp tục cuộn sau 3 giây
            handler.postDelayed(this, 3000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        PreferencesManager preferencesManager = PreferencesManager.getInstance(this);
        String hoTen = preferencesManager.getHoTen();

        // Hiển thị tên người dùng vào TextView
        TextView tvHello = findViewById(R.id.appName);
        if (hoTen != null) {
            // Nếu đã đăng nhập, hiển thị tên người dùng
            tvHello.setText("Xin chào " + hoTen);
        } else {
            // Nếu chưa đăng nhập, hiển thị thông báo
            tvHello.setText("Xin chào, vui lòng đăng nhập!");
        }

        // Gán các ImageView bằng ID
        // Lấy HorizontalScrollView
        horizontalScrollView = findViewById(R.id.horizontalScrollView);

        // Gán các ImageView và các nút (các phần này đã có trong code của bạn)
        ImageView imgCleaningService = findViewById(R.id.img_cleaning_service);
        ImageView imgCookingService = findViewById(R.id.img_cooking_service);
        ImageView imgLaundryService = findViewById(R.id.img_laundry_service);
        ImageView imgBabysittingService = findViewById(R.id.img_babysitting_service);
        ImageView imgAirConditionerService = findViewById(R.id.img_air_conditioner_service);
        ImageView imgMovingService = findViewById(R.id.img_moving_service);

        // Các nút khác
        ImageButton btnHistory = findViewById(R.id.btnHistory);
        ImageButton btnSupport = findViewById(R.id.btnSupport);
        ImageButton btnProfile = findViewById(R.id.btnProfile);

        // Xử lý sự kiện click cho các dịch vụ
        View.OnClickListener navigateToConfirm = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeCleanhouseActivity.class);
                startActivity(intent);
            }
        };

        imgCleaningService.setOnClickListener(navigateToConfirm);
        imgCookingService.setOnClickListener(navigateToConfirm);
        imgLaundryService.setOnClickListener(navigateToConfirm);
        imgBabysittingService.setOnClickListener(navigateToConfirm);
        imgAirConditionerService.setOnClickListener(navigateToConfirm);
        imgMovingService.setOnClickListener(navigateToConfirm);

        // Xử lý sự kiện click cho btnHistory
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WorkWaitingActivity.class);
                startActivity(intent);
            }
        });

        // Xử lý sự kiện click cho btnSupport
        btnSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MessChatActivity.class);
                startActivity(intent);
            }
        });
        btnProfile.setOnClickListener(v -> {
            if (hoTen != null) {
                // Nếu đã đăng nhập, chuyển sang AccountActivity
                Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                startActivity(intent);
            } else {
                // Nếu chưa đăng nhập, chuyển sang AccActivity
                Intent intent = new Intent(MainActivity.this, AccActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Bắt đầu cuộn tự động khi Activity được hiển thị
        handler.postDelayed(scrollRunnable, 3000); // Chạy sau 3 giây
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Dừng cuộn khi Activity bị dừng
        handler.removeCallbacks(scrollRunnable);
    }
}
