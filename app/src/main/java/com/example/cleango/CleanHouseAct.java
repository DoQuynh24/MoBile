package com.example.cleango;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.database.Cursor;
import androidx.cardview.widget.CardView;
import android.util.Log;
import java.text.DecimalFormat;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;  // Thêm import cho DecimalFormat

public class CleanHouseAct extends AppCompatActivity {

    private LinearLayout layoutDichVu;  // LinearLayout chứa các CardView
    private Data_DichVu dataDichVu;     // Đối tượng truy xuất dữ liệu dịch vụ
    private CardView selectedCardView;  // Lưu trữ CardView được chọn
    private TextView tvGiaTien;         // TextView hiển thị giá tiền

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clean_house);
        Button btnNext = findViewById(R.id.btnNext);
        layoutDichVu = findViewById(R.id.layoutDichVu); // Khởi tạo layout chứa các CardView
        dataDichVu = new Data_DichVu(this);  // Khởi tạo đối tượng Data_DichVu để truy xuất dữ liệu
        tvGiaTien = findViewById(R.id.tv_giaTien); // Khởi tạo TextView để hiển thị giá

        dataDichVu.addDichVuDonNha(this); // Gọi phương thức thêm dịch vụ Dọn nhà

        int soLuongDichVu = dataDichVu.getSoLuongDichVu();
        Log.i("SoLuongDichVu", "Số lượng dịch vụ trong bảng DichVu: " + soLuongDichVu);

        String tenDichVu = "Dọn nhà";
        loadChiTietDichVu(tenDichVu);  // Lấy và hiển thị chi tiết dịch vụ "Dọn nhà"
        // Xử lý sự kiện click cho nút btnNext
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang CleanHouse1Act với layout clean_house1.xml
                Intent intent = new Intent(CleanHouseAct.this, CleanHouse1Act.class);
                startActivity(intent);
            }
        });
    }

    private void loadChiTietDichVu(String tenDichVu) {
        Cursor cursor = dataDichVu.getChiTietDichVuByTen(tenDichVu);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int thoiGianHoanThanhIndex = cursor.getColumnIndex("ThoiGianHoanThanh");
                int khoiLuongCVIndex = cursor.getColumnIndex("KhoiLuongCV");
                int giaTienIndex = cursor.getColumnIndex("GiaTien");

                if (thoiGianHoanThanhIndex >= 0 && khoiLuongCVIndex >= 0 && giaTienIndex >= 0) {
                    String thoiGianHoanThanh = cursor.getString(thoiGianHoanThanhIndex);
                    String khoiLuongCV = cursor.getString(khoiLuongCVIndex);
                    final double giaTien = cursor.getDouble(giaTienIndex);

                    // Tạo đối tượng DecimalFormat để định dạng giá tiền
                    DecimalFormat decimalFormat = new DecimalFormat("#,###");
                    String giaTienFormatted = decimalFormat.format(giaTien);  // Định dạng giá tiền

                    // Tạo CardView mới cho mỗi chi tiết dịch vụ
                    CardView cardView = new CardView(this);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    cardView.setLayoutParams(params);

                    // Đảm bảo CardView có thể click và lấy focus
                    cardView.setClickable(true);
                    cardView.setFocusable(true);

                    // Đảm bảo CardView yêu cầu làm mới lại giao diện
                    cardView.invalidate();
                    cardView.requestLayout();

                    // Tạo TextView để hiển thị thông tin chi tiết dịch vụ
                    TextView textView = new TextView(this);
                    String text = thoiGianHoanThanh + ": " + khoiLuongCV ;
                    textView.setText(text);
                    textView.setPadding(30, 30, 30, 30);
                    params.setMargins(0, 15, 0, 15);
                    textView.setTextSize(15);
                    textView.setTextColor(Color.BLACK);
                    textView.setGravity(View.TEXT_ALIGNMENT_CENTER); // Căn giữa cho TextView

                    // Thiết lập sự kiện click cho CardView
                    cardView.setOnClickListener(v -> {
                        Log.i("CardViewClicked", "CardView đã được click");

                        // Cập nhật giá tiền đã định dạng vào TextView
                        tvGiaTien.setText(giaTienFormatted + " VNĐ / " + thoiGianHoanThanh);

                        // Khôi phục lại màu nền của CardView đã chọn trước đó, nếu có
                        if (selectedCardView != null) {
                            selectedCardView.setCardBackgroundColor(getResources().getColor(R.color.card_default_background));
                        }

                        // Đổi màu nền của CardView đã chọn
                        cardView.setCardBackgroundColor(getResources().getColor(R.color.card_selected_background));
                        selectedCardView = cardView;  // Lưu lại CardView hiện tại đã chọn

                        // Thông báo chi tiết dịch vụ khi nhấp
                        String message = "Bạn đã chọn dịch vụ: " + thoiGianHoanThanh + " - " + khoiLuongCV;
                        Log.i("DichVuSelected", message);
                    });

                    // Thêm TextView vào CardView
                    cardView.addView(textView);

                    // Thêm CardView vào layout
                    layoutDichVu.addView(cardView);
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
    }
}
