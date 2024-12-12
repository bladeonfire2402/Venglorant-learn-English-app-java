package com.sinhvien.venglorant.quanlyuser;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
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
import com.sinhvien.venglorant.quanlyBaiHoc.CapNhatBHActivity;
import com.sinhvien.venglorant.quanlyBaiHoc.QLbaihocActivity;

import java.io.ByteArrayOutputStream;

public class CapNhatUserActivity extends AppCompatActivity {
    EditText edtTenUCN, edtUserNameCN, edtPassWordCN;
    ImageView ivHinhUserCN;
    Button btnUpHinhUserCN, btnCapNhatUser, btnXoaUser;
    int REQUESTCODE_UPHINHUSERCN = 4;
    int maU;

    protected void onResume() {
        xulyCapNhat();

        xulyXoa();
        super.onResume();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_user);
        addControls();
        addEventUploadHinhCN();
    }


    private void xulyXoa() {
        btnXoaUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int kq = QLuserActivity.databaseU.deleteUser(maU);
                Toast.makeText(CapNhatUserActivity.this,"XOA USER THANH CONG",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CapNhatUserActivity.this,QLuserActivity.class));
            }
        });
    }

    private void xulyCapNhat() {
        btnCapNhatUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ các trường EditText và ImageView
                String tenU = edtTenUCN.getText().toString();
                String username = edtUserNameCN.getText().toString();
                String password = edtPassWordCN.getText().toString();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) ivHinhUserCN.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();

                // Chuyển đổi Bitmap thành byte[]
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                byte[] hinhU = outputStream.toByteArray();

                // Tạo đối tượng Intent để trả kết quả về
                ContentValues values = new ContentValues();
                values.put("tenU",tenU);
                values.put("userName",username);
                values.put("passWord",password);
                values.put("hinhU",hinhU);

                // Đặt kết quả thành thành công và gửi Intent
                int kq = (int) QLuserActivity.databaseU.updateUser(maU,values);
                if (kq != -1) {
                    Toast.makeText(CapNhatUserActivity.this, "Cập nhật dữ liệu thành công", Toast.LENGTH_SHORT).show();
                    // Khi cập nhật xong, chuyển về màn hình trước
                    finish();
                } else {
                    Toast.makeText(CapNhatUserActivity.this, "Cập nhật dữ liệu thất bại", Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(CapNhatUserActivity.this,QLuserActivity.class));
                // Kết thúc Activity
            }
        });
    }

    private void addEventUploadHinhCN() {
        btnUpHinhUserCN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUESTCODE_UPHINHUSERCN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUESTCODE_UPHINHUSERCN && resultCode == RESULT_OK && data != null){
            try {
                Uri imageUri = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                ivHinhUserCN.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void addControls() {
        edtTenUCN = findViewById(R.id.edtTenUCN);
        edtUserNameCN = findViewById(R.id.edtUserNameCN);
        edtPassWordCN = findViewById(R.id.edtPassWordCN);
        ivHinhUserCN = findViewById(R.id.ivHinhUserCN);
        btnCapNhatUser = findViewById(R.id.btnCapNhatUser);
        btnXoaUser = findViewById(R.id.btnXoaUser);
        btnUpHinhUserCN = findViewById(R.id.btnUpHinhUserCN);

        // Lấy ID của bài học từ Intent và gán cho biến instance maBH
        maU = getIntent().getIntExtra("maU", -1);
    }
}