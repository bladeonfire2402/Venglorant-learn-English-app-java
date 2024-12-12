package com.sinhvien.venglorant.quanlyBaiHoc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.sinhvien.venglorant.R;

import java.io.ByteArrayOutputStream;

public class CapNhatBHActivity extends AppCompatActivity {
    EditText edtUnitBHCN, edtTenBHCN;
    ImageView ivHinhUPCN;
    Button btnUpHinhCN, btnCapNhatBH, btnXoaBH;
    int REQUESTCODE_UPHINHCN = 2;
    int maBH;

    @Override
    protected void onResume() {
        xulyCapNhat();

        xulyXoa();
        super.onResume();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_bhactivity);
        addControls();
        if (maBH == -1){
            Toast.makeText(CapNhatBHActivity.this,"null",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(CapNhatBHActivity.this,"KHONG NULL",Toast.LENGTH_SHORT).show();
        }
        addEventUploadHinhCN();
    }

    private void xulyXoa() {
        btnXoaBH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int kq = QLbaihocActivity.databasebh.deleteBaiHoc(maBH);
                Toast.makeText(CapNhatBHActivity.this,"XOA BAI HOC THANH CONG",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CapNhatBHActivity.this,QLbaihocActivity.class));
            }
        });
    }

    private void xulyCapNhat() {
        btnCapNhatBH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ các trường EditText và ImageView
                String tenBH = edtTenBHCN.getText().toString();
                String unitBH = edtUnitBHCN.getText().toString();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) ivHinhUPCN.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();

                // Chuyển đổi Bitmap thành byte[]
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                byte[] hinhBH = outputStream.toByteArray();

                // Tạo đối tượng Intent để trả kết quả về
                ContentValues values = new ContentValues();
                values.put("tenBH",tenBH);
                values.put("unitBH",unitBH);
                values.put("hinhBH",hinhBH);

                // Đặt kết quả thành thành công và gửi Intent
                int kq = (int) QLbaihocActivity.databasebh.updateBaiHoc(maBH,values);
                if (kq != -1) {
                    Toast.makeText(CapNhatBHActivity.this, "Cập nhật dữ liệu thành công", Toast.LENGTH_SHORT).show();
                    // Khi cập nhật xong, chuyển về màn hình trước
                    finish();
                } else {
                    Toast.makeText(CapNhatBHActivity.this, "Cập nhật dữ liệu thất bại", Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(CapNhatBHActivity.this,QLbaihocActivity.class));
                // Kết thúc Activity
            }
        });
    }

    private void addEventUploadHinhCN() {
        btnUpHinhCN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUESTCODE_UPHINHCN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUESTCODE_UPHINHCN && resultCode == RESULT_OK && data != null){
            try {
                Uri imageUri = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                ivHinhUPCN.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void addControls() {
        edtTenBHCN = findViewById(R.id.edtTenBHCN);
        edtUnitBHCN = findViewById(R.id.edtUnitBHCN);
        ivHinhUPCN = findViewById(R.id.ivHinhUPCN);
        btnUpHinhCN = findViewById(R.id.btnUpHinhCN);
        btnCapNhatBH = findViewById(R.id.btnCapNhatBH);
        btnXoaBH = findViewById(R.id.btnXoaBH);

        // Lấy ID của bài học từ Intent và gán cho biến instance maBH
        maBH = getIntent().getIntExtra("maBH", -1);
    }
}
