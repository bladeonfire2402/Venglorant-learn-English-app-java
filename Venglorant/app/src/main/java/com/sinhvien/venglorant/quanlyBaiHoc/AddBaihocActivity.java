package com.sinhvien.venglorant.quanlyBaiHoc;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.sinhvien.venglorant.MainActivity;
import com.sinhvien.venglorant.R;

import java.io.ByteArrayOutputStream;

public class AddBaihocActivity extends AppCompatActivity {
    EditText edtUnitBH, edtTenBH;
    ImageView ivHinhUp;
    Button btnUpHinh, btnThemBH;
    int REQUESTCODE_UPHINH = 1;
    private SQLiteDatabase databasebh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_baihoc);
        //database = openOrCreateDatabase(QLbaihocActivity.DATABASE_NAME, MODE_PRIVATE, null);

        addControls();
        addEventUploadHinh();
        addEventAddBH();
    }

    private void addEventAddBH() {
        btnThemBH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hinh dang o BitmapDrawable -> Bitmap -> byte[] -> save
                BitmapDrawable bitmapDrawable = (BitmapDrawable) ivHinhUp.getDrawable(); //chuyen kieu du lieu cua ImageView ve BitmapDrawable
                Bitmap bitmap = bitmapDrawable.getBitmap(); // chuyen ve kieu bitmap
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream); // nen hinh ve duoi mong muon (vd: png,jpeg,...)
                byte[] hinh = byteArrayOutputStream.toByteArray(); // chuyen ve kieu byte[]
                // luu vo database
                QLbaihocActivity.databasebh.InsertRecordBH(
                        edtTenBH.getText().toString().trim(),
                        edtUnitBH.getText().toString().trim(),
                        hinh
                );
                Toast.makeText(AddBaihocActivity.this,"ADD BAI HOC THANH CONG",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddBaihocActivity.this, QLbaihocActivity.class));
            }
        });
    }

    private void addEventUploadHinh() {
        btnUpHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để chọn hình ảnh từ Gallery
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUESTCODE_UPHINH);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUESTCODE_UPHINH && resultCode == RESULT_OK && data != null){
            try {
                // Lấy đường dẫn của hình ảnh được chọn từ Gallery
                Uri imageUri = data.getData();
                // Chuyển đổi đường dẫn thành Bitmap và hiển thị trên ImageView
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                ivHinhUp.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void addControls() {
        edtUnitBH = findViewById(R.id.edtUnitBH);
        edtTenBH = findViewById(R.id.edtTenBH);
        ivHinhUp = findViewById(R.id.ivHinhUP);
        btnThemBH = findViewById(R.id.btnThemBH);
        btnUpHinh = findViewById(R.id.btnUpHinh);
    }
}
