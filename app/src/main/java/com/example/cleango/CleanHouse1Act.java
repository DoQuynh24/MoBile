package com.example.cleango;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
public class CleanHouse1Act extends AppCompatActivity {

    private Calendar calendar;
    private TextView tvGiaTien, tvThoiGianBatDau, tvNgayBatDau;
    private EditText etGhiChu;
    private String thoiGianHoanThanh, khoiLuongCV, giaTien, diaChi ,thoiGianBatDau, ngayBatDau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clean_house1);

        tvThoiGianBatDau = findViewById(R.id.tvThoiGianBatDau);
        tvNgayBatDau = findViewById(R.id.tvNgayBatDau);
        tvGiaTien = findViewById(R.id.tv_giaTien);
        etGhiChu = findViewById(R.id.etGhiChu);

        Button btnNext = findViewById(R.id.btnNext);
        calendar = Calendar.getInstance();

        // Nhận dữ liệu từ intent
        Intent intent = getIntent();
        thoiGianHoanThanh = intent.getStringExtra("thoiGianHoanThanh");
        khoiLuongCV = intent.getStringExtra("khoiLuongCV");
        giaTien = intent.getStringExtra("giaTien");

        tvGiaTien.setText(giaTien);

        diaChi = intent.getStringExtra("address");

        // Sự kiện chọn ngày
        findViewById(R.id.imgCalendar).setOnClickListener(v -> showDatePickerDialog());

        // Sự kiện chọn giờ
        findViewById(R.id.imgTime).setOnClickListener(v -> showTimePickerDialog());

        // Xử lý sự kiện nút Next
        btnNext.setOnClickListener(v -> {
            // Kiểm tra xem đã chọn cả ngày và giờ chưa
            if (tvThoiGianBatDau.getText().toString().equals("Chọn giờ làm") || tvNgayBatDau.getText().toString().equals("Chọn ngày")) {
                // Hiển thị thông báo yêu cầu chọn ngày và giờ
                Toast.makeText(CleanHouse1Act.this, "Vui lòng chọn cả ngày và giờ trước khi tiếp tục!", Toast.LENGTH_SHORT).show();
            } else {
                // Chuyển sang ConfirmAct
                Intent nextIntent = new Intent(CleanHouse1Act.this, ConfirmAct.class);
                nextIntent.putExtra("thoiGianHoanThanh", thoiGianHoanThanh);
                nextIntent.putExtra("khoiLuongCV", khoiLuongCV);
                nextIntent.putExtra("giaTien", giaTien);
                nextIntent.putExtra("thoiGianBatDau", tvThoiGianBatDau.getText().toString());
                nextIntent.putExtra("ngayBatDau", tvNgayBatDau.getText().toString());
                String ghiChu = etGhiChu.getText().toString();
                nextIntent.putExtra("ghiChu", ghiChu.isEmpty() ? "Không có" : ghiChu);
                nextIntent.putExtra("diaChi", diaChi);
                startActivity(nextIntent);
            }
        });

        // Xử lý sự kiện nút Back
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent backIntent = new Intent(CleanHouse1Act.this, CleanHouseAct.class);
            startActivity(backIntent);

        });
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    calendar.set(year, month, dayOfMonth);
                    updateDateText();
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, hourOfDay, minute) -> {
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calendar.set(Calendar.MINUTE, minute);
                    updateTimeText();
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
        );
        timePickerDialog.show();
    }

    private void updateDateText() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMM yyyy", new Locale("vi", "VN"));
        String formattedDate = sdf.format(calendar.getTime());
        tvNgayBatDau.setText(formattedDate);
    }

    private void updateTimeText() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", new Locale("vi", "VN"));
        String formattedTime = sdf.format(calendar.getTime());
        tvThoiGianBatDau.setText(formattedTime);
    }
}
