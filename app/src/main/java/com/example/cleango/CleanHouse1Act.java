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
import android.view.View;
import android.widget.Button;
public class CleanHouse1Act extends AppCompatActivity {

    private TextView tvChosenDate; // TextView hiển thị ngày đã chọn
    private TextView tvChosenTime; // TextView hiển thị giờ đã chọn
    private Calendar calendar; // Lưu trữ ngày giờ hiện tại

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clean_house1);

        tvChosenDate = findViewById(R.id.tvChosenDate); // TextView cho ngày
        tvChosenTime = findViewById(R.id.tvChosenTime); // TextView cho giờ

        Button btnNext = findViewById(R.id.btnNext);
        calendar = Calendar.getInstance();

        // Sự kiện chọn ngày
        findViewById(R.id.imgCalendar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Sự kiện chọn giờ
        findViewById(R.id.imgTime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });
        // Xử lý sự kiện click cho nút btnNext
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang CleanHouse1Act với layout clean_house1.xml
                Intent intent = new Intent(CleanHouse1Act.this, ConfirmActivity.class);
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
                Intent intent = new Intent(CleanHouse1Act.this, CleanHouseAct.class);
                startActivity(intent);
                finish(); // Kết thúc activity hiện tại
            }
        });
    }

    // Hiển thị DatePickerDialog để chọn ngày
    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                CleanHouse1Act.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Cập nhật thông tin ngày sau khi chọn
                        calendar.set(year, month, dayOfMonth);
                        updateDateText();
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    // Hiển thị TimePickerDialog để chọn giờ
    private void showTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                CleanHouse1Act.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Cập nhật giờ và phút sau khi chọn
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        updateTimeText();
                    }
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
        );
        timePickerDialog.show();
    }

    // Cập nhật thông tin ngày vào TextView
    private void updateDateText() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMM yyyy", getResources().getConfiguration().locale);
        String formattedDate = sdf.format(calendar.getTime());
        tvChosenDate.setText(formattedDate); // Hiển thị ngày đã chọn
    }

    // Cập nhật thông tin giờ vào TextView
    private void updateTimeText() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", getResources().getConfiguration().locale);
        String formattedTime = sdf.format(calendar.getTime());
        tvChosenTime.setText(formattedTime); // Hiển thị giờ đã chọn
    }
}
