package com.example.cleango;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeCleanhouseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_cleanhouse);

        // Tìm nút btnBook theo ID
        Button btnBook = findViewById(R.id.btnBook);

        // Xử lý sự kiện click cho nút btnBook
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang giao diện clean_house.xml
                Intent intent = new Intent(HomeCleanhouseActivity.this, CleanHouseAct.class);
                startActivity(intent);
            }
        });

        // Tìm ImageView btnBack theo ID
        ImageButton btnBack = findViewById(R.id.btnBack);

        // Xử lý sự kiện click cho btnBack
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay lại MainActivity
                Intent intent = new Intent(HomeCleanhouseActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Kết thúc activity hiện tại
            }
        });
    }
}
