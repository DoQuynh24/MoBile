package com.example.cleango;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Gán các ImageView bằng ID
        ImageView imgCleaningService = findViewById(R.id.img_cleaning_service); // Thay thế ID tương ứng
        ImageView imgCookingService = findViewById(R.id.img_cooking_service);
        ImageView imgLaundryService = findViewById(R.id.img_laundry_service);
        ImageView imgBabysittingService = findViewById(R.id.img_babysitting_service);
        ImageView imgAirConditionerService = findViewById(R.id.img_air_conditioner_service);
        ImageView imgMovingService = findViewById(R.id.img_moving_service);

        // Gán các nút khác bằng ID
        ImageButton btnHistory = findViewById(R.id.btnHistory);
        ImageButton btnSupport = findViewById(R.id.btnSupport);
        ImageButton btnProfile = findViewById(R.id.btnProfile);

        // Xử lý sự kiện click cho từng ImageView
        View.OnClickListener navigateToConfirm = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến confirm.xml
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
                // Chuyển đến work_package.xml
                Intent intent = new Intent(MainActivity.this, WorkWaitingActivity.class);
                startActivity(intent);
            }
        });

        // Xử lý sự kiện click cho btnSupport
        btnSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến mess_chat.xml
                Intent intent = new Intent(MainActivity.this, MessChatActivity.class);
                startActivity(intent);
            }
        });

        // Xử lý sự kiện click cho btnProfile
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến account.xml
                Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });
    }
}
