package com.sinhvien.venglorant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.sinhvien.venglorant.quanlyBaiHoc.QLbaihocActivity;
import com.sinhvien.venglorant.quanlyuser.QLuserActivity;

public class AdminActivity extends AppCompatActivity {
ImageView ivAdminBH, ivAdminUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        addControls();
        ivAdminBH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminActivity.this, QLbaihocActivity.class);
                startActivity(intent);
            }
        });
        ivAdminUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminActivity.this, QLuserActivity.class);
                startActivity(intent);
            }
        });

    }

    private void addControls() {
        ivAdminBH = findViewById(R.id.ivAdminBH);
        ivAdminUser = findViewById(R.id.ivAdminUser);
    }
}