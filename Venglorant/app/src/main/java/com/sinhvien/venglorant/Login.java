package com.sinhvien.venglorant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButon;
    TextView ResgisterPage;
    ImageView FacebookLogin;
    ImageView TelegramLogin;
    ImageView GoogleLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=findViewById(R.id.username_input);
        password=findViewById(R.id.password_input);
        loginButon=findViewById(R.id.login_btn);
        ResgisterPage=findViewById(R.id.resgister_page);
        FacebookLogin=findViewById(R.id.facebook_login);
        TelegramLogin=findViewById(R.id.telegram_login);
        GoogleLogin=findViewById(R.id.google_login);


        loginButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("admin")&&password.getText().toString().equals("1234")){
                    Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Login.this,AdminActivity.class);
                    startActivity(intent);
                }
                if(username.getText().toString().equals("user")&&password.getText().toString().equals("1234")){
                    Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                }
                else if (username.getText().toString().equals(null)||password.getText().toString().equals(null)){
                    Toast.makeText(Login.this,"Tên đăng nhập hoặc mật khẩu chưa điền",Toast.LENGTH_SHORT).show();
                }
                else if(username.getText().toString()!=("user")||password.getText().toString().equals("1234")){
                    Toast.makeText(Login.this,"Sai tên đăng nhập hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                }
                else if(username.getText().toString()!=("admin")||password.getText().toString().equals("1234")){
                    Toast.makeText(Login.this,"Sai tên đăng nhập hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                }
            }
        });
        ResgisterPage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                Intent ResgisterIntent = new Intent();
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);

            }
        });
    }
}