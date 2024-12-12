package com.sinhvien.venglorant.quanlyuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.sinhvien.venglorant.R;
import com.sinhvien.venglorant.quanlyBaiHoc.AddBaihocActivity;
import com.sinhvien.venglorant.quanlyBaiHoc.BaiHoc;
import com.sinhvien.venglorant.quanlyBaiHoc.BaiHocAdapter;
import com.sinhvien.venglorant.quanlyBaiHoc.CapNhatBHActivity;
import com.sinhvien.venglorant.quanlyBaiHoc.DBAppHocTA;
import com.sinhvien.venglorant.quanlyBaiHoc.QLbaihocActivity;

import java.util.ArrayList;

public class QLuserActivity extends AppCompatActivity {
    ListView lvUser;
    ImageView ivAddUser;
    ArrayList<User> arrayListUser;
    UserAdapter adapterUser;
    public static DBAppHocTA databaseU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qluser);
        addControls();
        addEventAddUser();
        // tao database
        databaseU = new DBAppHocTA(this,"csdl.db",null,1);
        //tao bang
        databaseU.QueryData("create table if not exists tblUser (maU integer primary key autoincrement, tenU text, userName text, passWord text, hinhU blob)"); //autoincrement la tu dong tang

        Cursor cursor = databaseU.GetData("select * from tblUser");
        while (cursor.moveToNext()){
            arrayListUser.add(new User(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getBlob(4)
            ));
        }
        adapterUser.notifyDataSetChanged();

        xulyCapNhat();
    }


    private void xulyCapNhat() {
        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = arrayListUser.get(position);
                Intent intent = new Intent(QLuserActivity.this, CapNhatUserActivity.class);
                intent.putExtra("maU",user.getMaU(position));
                System.out.println("maU: " + user.getMaU(position));
                startActivity(intent);
            }
        });
    }


    private void addEventAddUser() {
        ivAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QLuserActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        lvUser = findViewById(R.id.lvUser);
        ivAddUser = findViewById(R.id.ivAddUser);

        // Khởi tạo ArrayList trước khi sử dụng
        arrayListUser = new ArrayList<>();
        adapterUser = new UserAdapter(this,R.layout.layout_itemuser,arrayListUser);
        lvUser.setAdapter(adapterUser);
    }
}