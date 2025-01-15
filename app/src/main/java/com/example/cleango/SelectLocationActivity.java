package com.example.cleango;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SelectLocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private EditText searchBar;
    private Button selectButton;
    private LatLng selectedLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location);

        searchBar = findViewById(R.id.search_bar);
        selectButton = findViewById(R.id.select_button);

        // Kiểm tra và yêu cầu quyền
        checkPermissions();

        // Khởi tạo bản đồ
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Xử lý tìm kiếm địa chỉ
        searchBar.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH || event.getAction() == KeyEvent.ACTION_DOWN) {
                String query = searchBar.getText().toString();
                searchLocation(query);
                return true;
            }
            return false;
        });

        // Xử lý chọn vị trí
        selectButton.setOnClickListener(v -> {
            if (selectedLatLng != null) {
                Toast.makeText(this, "Vị trí đã chọn: " + searchBar.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        LatLng defaultLocation = new LatLng(21.028511, 105.804817); // Hà Nội
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 15));

        mMap.setOnMapClickListener(latLng -> {
            selectedLatLng = latLng;
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(latLng).title("Vị trí đã chọn"));

            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                if (addresses != null && !addresses.isEmpty()) {
                    String address = addresses.get(0).getAddressLine(0);
                    searchBar.setText(address);
                    selectButton.setEnabled(true);
                } else {
                    searchBar.setText("Không thể lấy địa chỉ");
                    selectButton.setEnabled(false);
                }
            } catch (IOException e) {
                e.printStackTrace();
                searchBar.setText("Lỗi khi lấy địa chỉ");
                selectButton.setEnabled(false);
            }
        });
    }

    private void searchLocation(String location) {
        if (location == null || location.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập địa chỉ!", Toast.LENGTH_SHORT).show();
            return;
        }

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addressList = geocoder.getFromLocationName(location, 1);
            if (addressList != null && !addressList.isEmpty()) {
                Address address = addressList.get(0);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

                searchBar.setText(address.getAddressLine(0));
                selectedLatLng = latLng;
                selectButton.setEnabled(true);
            } else {
                Toast.makeText(this, "Không tìm thấy địa chỉ!", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi khi tìm kiếm địa chỉ!", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults); // Gọi lớp cha

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Kiểm tra lại quyền trước khi bật vị trí người dùng
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    if (mMap != null) {
                        try {
                            mMap.setMyLocationEnabled(true);
                        } catch (SecurityException e) {
                            e.printStackTrace();
                            Toast.makeText(this, "Không thể bật định vị do lỗi bảo mật!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Quyền bị từ chối!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
