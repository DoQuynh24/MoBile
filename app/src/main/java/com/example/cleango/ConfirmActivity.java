package com.example.cleango;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
public class ConfirmActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm);
        // Tìm ImageView btnBack theo ID?
        ImageView btnBack = findViewById(R.id.btnBack);

        // Xử lý sự kiện click cho btnBack
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay lại MainActivity
                Intent intent = new Intent(ConfirmActivity.this, CleanHouse1Act.class);
                startActivity(intent);
            }
        });
    }
}
