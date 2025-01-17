package com.example.cleango;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.view.View;
import android.widget.ImageButton;
import android.util.Log;
import android.widget.Toast;

public class ConfirmAct extends AppCompatActivity {

    private TextView tvThoiGian, tvKhoiLuongCV, tvGiaTien, tvNgayBatDau, tvGhiChu, tvDiaChi;
    private String thoiGianHoanThanh, khoiLuongCV, giaTien, thoiGianBatDau, ngayBatDau, diaChi;
    private ImageButton btnMoney, btnBpay, btnMomo, btnZalo, btnShopee, btnVisa;
    private Button btnPost;
    private String selectedPaymentMethod = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm);

        PreferencesManager preferencesManager = PreferencesManager.getInstance(this);
        String hoTen = preferencesManager.getHoTen();
        String taiKhoan = preferencesManager.getTaiKhoan();

        // Hiển thị tên người dùng vào TextView
        TextView tvHello = findViewById(R.id.appName);
        if (hoTen != null) {
            tvHello.setText(hoTen);
        }

        TextView tvTaiKhoan = findViewById(R.id.tv_TaiKhoan);
        if (taiKhoan != null) {
            tvTaiKhoan.setText("(+84) " + taiKhoan); // Thêm (+84) vào và loại bỏ '0' ở đầu
        }

        // Khởi tạo TextView
        tvThoiGian = findViewById(R.id.tvThoiGian);
        tvKhoiLuongCV = findViewById(R.id.tvKhoiLuongCV);
        tvGiaTien = findViewById(R.id.tv_giaTien);
        tvNgayBatDau = findViewById(R.id.tvNgayBatDau);
        tvGhiChu = findViewById(R.id.tvGhiChu);
        tvDiaChi = findViewById(R.id.tvDiaChi);

        btnMoney = findViewById(R.id.btnMoney);
        btnBpay = findViewById(R.id.btnBpay);
        btnMomo = findViewById(R.id.btnMomo);
        btnZalo = findViewById(R.id.btnZalo);
        btnShopee = findViewById(R.id.btnShopee);
        btnVisa = findViewById(R.id.btnVisa);

        btnPost = findViewById(R.id.btnPost);

        // Xử lý sự kiện khi click vào từng nút phương thức thanh toán
        btnMoney.setOnClickListener(view -> selectPaymentMethod("Tiền mặt"));
        btnBpay.setOnClickListener(view -> selectPaymentMethod("bPay"));
        btnMomo.setOnClickListener(view -> selectPaymentMethod("Momo"));
        btnZalo.setOnClickListener(view -> selectPaymentMethod("Zalo"));
        btnShopee.setOnClickListener(view -> selectPaymentMethod("Shopee"));
        btnVisa.setOnClickListener(view -> selectPaymentMethod("Visa"));



        btnPost.setOnClickListener(view -> {
            if (selectedPaymentMethod.isEmpty()) {
                // Nếu chưa chọn phương thức thanh toán, hiển thị thông báo
                Toast.makeText(ConfirmAct.this, "Vui lòng chọn phương thức thanh toán", Toast.LENGTH_SHORT).show();
                return; // Dừng xử lý đặt lịch
            }

            // Lấy thông tin người dùng và các lựa chọn
            String tenDichVu = "Dọn nhà"; // Ví dụ, có thể lấy thông tin này từ UI hoặc Intent
            String maNguoiDung = preferencesManager.getTaiKhoan(); // Lấy mã người dùng từ Preferences
            String diaChi = tvDiaChi.getText().toString();
            String ten = preferencesManager.getHoTen();
            String sdt = preferencesManager.getTaiKhoan();
            String pttt = selectedPaymentMethod; // Phương thức thanh toán đã chọn
            String ghiChu = tvGhiChu.getText().toString();

            // Sử dụng dữ liệu nhận từ Intent thay vì lấy từ TextView
            String giaTienText = giaTien;  // Sử dụng giá trị giaTien từ Intent
            String giaTienCleaned = giaTienText.replaceAll("[^\\d.]", ""); // Loại bỏ ký tự không phải số và dấu chấm
            double giaTien = 0;

            try {
                giaTien = Double.parseDouble(giaTienCleaned); // Chuyển đổi thành double
            } catch (NumberFormatException e) {
                Log.e("ConfirmAct", "Lỗi khi chuyển đổi giá tiền: " + e.getMessage());
                Toast.makeText(ConfirmAct.this, "Lỗi chuyển đổi giá tiền", Toast.LENGTH_SHORT).show();
                return; // Dừng nếu có lỗi trong việc chuyển đổi giá tiền
            }

            // Sử dụng dữ liệu từ Intent cho thoiGianBatDau, thoiGianHoanThanh, ngayBatDau
            String thoiGianBatDau = this.thoiGianBatDau; // Dùng giá trị thoiGianBatDau từ Intent
            String thoiGianHoanThanh = this.thoiGianHoanThanh; // Dùng giá trị thoiGianHoanThanh từ Intent
            String ngayBatDau = this.ngayBatDau; // Dùng giá trị ngayBatDau từ Intent

            // Log data to logcat for debugging
            Log.i("ConfirmAct", "Preparing data for post request:");
            Log.i("ConfirmAct", "TenDichVu: " + tenDichVu);
            Log.i("ConfirmAct", "MaNguoiDung: " + maNguoiDung);
            Log.i("ConfirmAct", "DiaChi: " + diaChi);
            Log.i("ConfirmAct", "Ten: " + ten);
            Log.i("ConfirmAct", "Sdt: " + sdt);
            Log.i("ConfirmAct", "Payment Method: " + pttt);
            Log.i("ConfirmAct", "GhiChu: " + ghiChu);
            Log.i("ConfirmAct", "GiaTien: " + giaTien);
            Log.i("ConfirmAct", "ThoiGianBatDau: " + thoiGianBatDau);
            Log.i("ConfirmAct", "ThoiGianHoanThanh: " + thoiGianHoanThanh);
            Log.i("ConfirmAct", "NgayBatDau: " + ngayBatDau);

            // Thực hiện thêm đặt lịch vào database
            Data_DichVu dataDichVu = new Data_DichVu(this);
            boolean success = dataDichVu.addDatLich(tenDichVu, maNguoiDung, diaChi, ten, sdt, pttt, ghiChu, giaTien, thoiGianBatDau, thoiGianHoanThanh, ngayBatDau);

            if (success) {
                // Hiển thị thông báo thành công và chuyển đến trang work_waiting.xml
                Toast.makeText(ConfirmAct.this, "Đặt lịch thành công!", Toast.LENGTH_SHORT).show();

                // Chuyển đến trang work_waiting.xml

                Intent intent = new Intent(ConfirmAct.this, WorkWaitingActivity.class);

                startActivity(intent);
            } else {
                // Hiển thị thông báo lỗi và giữ lại trang hiện tại
                Toast.makeText(ConfirmAct.this, "Lỗi khi đặt lịch. Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
            }
        });



        // Nhận dữ liệu từ intent
        Intent intent = getIntent();
        thoiGianHoanThanh = intent.getStringExtra("thoiGianHoanThanh");
        khoiLuongCV = intent.getStringExtra("khoiLuongCV");
        giaTien = intent.getStringExtra("giaTien");
        thoiGianBatDau = intent.getStringExtra("thoiGianBatDau");
        ngayBatDau = intent.getStringExtra("ngayBatDau");
        diaChi = intent.getStringExtra("diaChi");

        // Hiển thị thông tin lên TextView
        tvGiaTien.setText(giaTien);
        tvKhoiLuongCV.setText(khoiLuongCV);
        String ghiChu = intent.getStringExtra("ghiChu");
        tvGhiChu.setText(ghiChu);
        tvDiaChi.setText(diaChi);

        // Hiển thị ngày và thời gian bắt đầu dạng "ngayBatDau - thoiGianBatDau"
        if (ngayBatDau != null && thoiGianBatDau != null) {
            String ngayVaThoiGianBatDau = ngayBatDau + " - " + thoiGianBatDau;
            tvNgayBatDau.setText(ngayVaThoiGianBatDau);
        }

        // Xử lý thời gian hoàn thành và hiển thị chuỗi "Làm trong"
        if (thoiGianBatDau != null && thoiGianHoanThanh != null) {
            try {
                String[] parts = thoiGianHoanThanh.split(" ");
                int duration = Integer.parseInt(parts[0].replace("giờ", "").trim());

                String[] timeParts = thoiGianBatDau.split(":");
                int startHour = Integer.parseInt(timeParts[0]);
                int startMinute = Integer.parseInt(timeParts[1]);

                int endHour = startHour + duration;
                String formattedTime = String.format("%d giờ, từ %s đến %02d:%02d", duration, thoiGianBatDau, endHour, startMinute);
                tvThoiGian.setText(formattedTime);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                Log.e("ConfirmAct", "Lỗi xử lý thời gian: " + e.getMessage());
                tvThoiGian.setText("Lỗi định dạng thời gian");
            }
        }
    }

    private void selectPaymentMethod(String paymentMethod) {
        // Đặt lại màu sắc của tất cả các nút thanh toán về trạng thái mặc định
        resetPaymentButtons();

        // Đổi màu của nút đã chọn
        if (paymentMethod.equals("Tiền mặt")) {
            btnMoney.setBackgroundColor(getResources().getColor(R.color.selected));
        } else if (paymentMethod.equals("bPay")) {
            btnBpay.setBackgroundColor(getResources().getColor(R.color.selected));
        } else if (paymentMethod.equals("Momo")) {
            btnMomo.setBackgroundColor(getResources().getColor(R.color.selected));
        } else if (paymentMethod.equals("Zalo")) {
            btnZalo.setBackgroundColor(getResources().getColor(R.color.selected));
        } else if (paymentMethod.equals("Shopee")) {
            btnShopee.setBackgroundColor(getResources().getColor(R.color.selected));
        } else if (paymentMethod.equals("Visa")) {
            btnVisa.setBackgroundColor(getResources().getColor(R.color.selected));
        }

        // Lưu phương thức thanh toán đã chọn
        selectedPaymentMethod = paymentMethod;
    }

    private void resetPaymentButtons() {
        // Đặt lại màu sắc của tất cả các nút thanh toán
        btnMoney.setBackgroundColor(getResources().getColor(R.color.defaultColor));
        btnBpay.setBackgroundColor(getResources().getColor(R.color.defaultColor));
        btnMomo.setBackgroundColor(getResources().getColor(R.color.defaultColor));
        btnZalo.setBackgroundColor(getResources().getColor(R.color.defaultColor));
        btnShopee.setBackgroundColor(getResources().getColor(R.color.defaultColor));
        btnVisa.setBackgroundColor(getResources().getColor(R.color.defaultColor));
    }
}
