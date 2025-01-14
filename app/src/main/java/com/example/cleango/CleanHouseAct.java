package com.example.cleango;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CleanHouseAct extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clean_house);

        Button btnNext = findViewById(R.id.btnNext);
        // Xử lý sự kiện thay đổi màu sắc của văn bản
        TextView textView = findViewById(R.id.TVS);
        String text = "* Lưu ý: Dịch vụ hỗ trợ tối đa 4 giờ. Đối với thời gian dài hơn, vui lòng đặt dịch vụ Tổng Vệ Sinh hoặc đặt 2 công việc có khung thời gian gần nhau.";

        // Tạo SpannableString từ text
        SpannableString spannableString = new SpannableString(text);

        // Tìm vị trí của từ "dịch vụ Tổng Vệ Sinh" trong văn bản
        int start = text.indexOf("dịch vụ Tổng Vệ Sinh");
        int end = start + "dịch vụ Tổng Vệ Sinh".length();

        // Áp dụng màu đỏ cho từ "dịch vụ Tổng Vệ Sinh"
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF5733")), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Đặt văn bản đã thay đổi màu cho TextView
        textView.setText(spannableString);

        // Xử lý sự kiện click cho nút btnNext
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang CleanHouse1Act với layout clean_house1.xml
                Intent intent = new Intent(CleanHouseAct.this, CleanHouse1Act.class);
                startActivity(intent);
            }
        });
        // Tìm ImageView btnBack theo ID
        ImageView btnBack = findViewById(R.id.btnBack);

        // Xử lý sự kiện click cho btnBack
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay lại MainActivity
                Intent intent = new Intent(CleanHouseAct.this, HomeCleanhouseActivity.class);
                startActivity(intent);
                finish(); // Kết thúc activity hiện tại
            }
        });
    }
}
