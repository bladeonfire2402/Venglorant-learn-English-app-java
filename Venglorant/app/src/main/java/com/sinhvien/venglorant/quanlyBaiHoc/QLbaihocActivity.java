package com.sinhvien.venglorant.quanlyBaiHoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.sinhvien.venglorant.R;

import java.util.ArrayList;

public class QLbaihocActivity extends AppCompatActivity {

    ListView lvBaiHoc;
    ImageView ivAddBH;
    ArrayList<BaiHoc> arrayListBH;
    BaiHocAdapter adapterBaiHoc;
    public static DBAppHocTA databasebh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlbaihoc);
        addControls();
        addEventAddBH();
        // tao database
        databasebh = new DBAppHocTA(this,"csdl.db",null,1);
        //tao bang
        databasebh.QueryData("create table if not exists tblBaihoc (maBH integer primary key autoincrement, tenBH text, unitBH text, hinhBH blob)"); //autoincrement la tu dong tang

        Cursor cursor = databasebh.GetData("select * from tblBaihoc");
        while (cursor.moveToNext()){
            arrayListBH.add(new BaiHoc(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getBlob(3)
            ));
        }
        adapterBaiHoc.notifyDataSetChanged();

        xulyCapNhat();
    }

    private void xulyCapNhat() {
        lvBaiHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaiHoc baiHoc = arrayListBH.get(position);
                Intent intent = new Intent(QLbaihocActivity.this,CapNhatBHActivity.class);
                intent.putExtra("maBH",baiHoc.getMa(position));
                System.out.println("maBH: " + baiHoc.getMa(position));
                startActivity(intent);
            }
        });
    }


    private void addEventAddBH() {
        ivAddBH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QLbaihocActivity.this, AddBaihocActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        lvBaiHoc = findViewById(R.id.lvBaiHoc);
        ivAddBH = findViewById(R.id.ivAddBH);

        // Khởi tạo ArrayList trước khi sử dụng
        arrayListBH = new ArrayList<>();
        adapterBaiHoc = new BaiHocAdapter(this,R.layout.layout_itembh,arrayListBH);
        lvBaiHoc.setAdapter(adapterBaiHoc);
    }

}
