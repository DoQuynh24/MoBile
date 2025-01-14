package com.example.cleango;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class InforActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infor);

        // Tìm btnBack theo ID
        ImageView btnBack = findViewById(R.id.btnBack);

        // Xử lý sự kiện click cho btnBack
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay lại AccountActivity
                Intent intent = new Intent(InforActivity.this, AccountActivity.class);
                startActivity(intent);
                finish(); // Kết thúc InforActivity
            }
        });
    }
}
