package com.sinhvien.venglorant;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    TextView loginPage;
    Button btnRegister;
    EditText edtNameRegister,edtPasswordRegister,edtEmailRegister,edtAdressRegister;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginPage=findViewById(R.id.login_page);
        btnRegister=findViewById(R.id.btnRegister);
        edtNameRegister=findViewById(R.id.edtName);
        edtPasswordRegister=findViewById(R.id.edtPassword);
        edtEmailRegister=findViewById(R.id.edtEmail);
        edtAdressRegister=findViewById(R.id.edtPhone);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtNameRegister.getText().toString().equals(null)&&edtPasswordRegister.getText().toString().equals(null)
                &&edtEmailRegister.getText().toString().equals(null)&&edtAdressRegister.getText().toString().equals(null)){
                    Toast.makeText(Register.this,"Vui lòng không điền hết Form đăng nhập",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Register.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Register.this,MainActivity.class);
                    startActivity(intent);
                }
            }

        });

        loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });


    }
}