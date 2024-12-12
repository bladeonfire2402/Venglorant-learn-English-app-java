package com.sinhvien.venglorant.quanlyuser;

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

import com.sinhvien.venglorant.R;
import com.sinhvien.venglorant.quanlyBaiHoc.AddBaihocActivity;
import com.sinhvien.venglorant.quanlyBaiHoc.QLbaihocActivity;

import java.io.ByteArrayOutputStream;

public class AddUserActivity extends AppCompatActivity {

    EditText edtUserName, edtPassWord, edtTenU;
    ImageView ivHinhUser;
    Button btnUpHinhUser, btnThemUser;
    int REQUESTCODE_UPHINHUSER = 3;
    private SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        //database = openOrCreateDatabase(QLbaihocActivity.DATABASE_NAME, MODE_PRIVATE, null);

        addControls();
        addEventUploadHinh();
        addEventAddUser();
    }

    private void addEventAddUser() {
        btnThemUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hinh dang o BitmapDrawable -> Bitmap -> byte[] -> save
                BitmapDrawable bitmapDrawable = (BitmapDrawable) ivHinhUser.getDrawable(); //chuyen kieu du lieu cua ImageView ve BitmapDrawable
                Bitmap bitmap = bitmapDrawable.getBitmap(); // chuyen ve kieu bitmap
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream); // nen hinh ve duoi mong muon (vd: png,jpeg,...)
                byte[] hinh = byteArrayOutputStream.toByteArray(); // chuyen ve kieu byte[]
                // luu vo database
                QLuserActivity.databaseU.InsertRecordUser(
                        edtTenU.getText().toString().trim(),
                        edtUserName.getText().toString().trim(),
                        edtPassWord.getText().toString().trim(),
                        hinh
                );
                Toast.makeText(AddUserActivity.this,"ADD USER THANH CONG",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddUserActivity.this, QLuserActivity.class));
            }
        });
    }

    private void addEventUploadHinh() {
        btnUpHinhUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để chọn hình ảnh từ Gallery
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUESTCODE_UPHINHUSER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUESTCODE_UPHINHUSER && resultCode == RESULT_OK && data != null){
            try {
                // Lấy đường dẫn của hình ảnh được chọn từ Gallery
                Uri imageUri = data.getData();
                // Chuyển đổi đường dẫn thành Bitmap và hiển thị trên ImageView
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                ivHinhUser.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void addControls() {
        edtTenU = findViewById(R.id.edtTenU);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassWord = findViewById(R.id.edtPassWord);
        ivHinhUser = findViewById(R.id.ivHinhUser);
        btnThemUser = findViewById(R.id.btnThemUser);
        btnUpHinhUser = findViewById(R.id.btnUpHinhUser);
    }
}