package com.example.cleango;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    }
}
