package com.sinhvien.venglorant.quanlyBaiHoc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sinhvien.venglorant.R;

import java.util.ArrayList;
import com.squareup.picasso.Picasso;


public class BaiHocAdapter extends BaseAdapter {
    // khai bao bien
    Context context;
    int layout;
    ArrayList<BaiHoc> arrayListBaiHoc;

    // khai bao contructor
    public BaiHocAdapter(Context context, int layout, ArrayList<BaiHoc> arrayListBaiHoc) {
        this.context = context;
        this.layout = layout;
        this.arrayListBaiHoc = arrayListBaiHoc;
    }

    @Override
    public int getCount() {
        return arrayListBaiHoc.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
public class ViewHolder{
TextView txtUnitBH,txtTenBH;
ImageView ivHinhBH;
}
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            // tham chieu id layout_itembh -> holder
            holder.txtTenBH= convertView.findViewById(R.id.txtTenBH);
            holder.txtUnitBH= convertView.findViewById(R.id.txtUnitBH);
            holder.ivHinhBH= convertView.findViewById(R.id.ivHinhBH);
            convertView.setTag(holder);
        }
        else {
            holder= (ViewHolder) convertView.getTag();
        }
        //get data from baihoc -> holder
        BaiHoc bh = arrayListBaiHoc.get(position);
        holder.txtTenBH.setText(bh.getTen());
        holder.txtUnitBH.setText(bh.getUnit());
        byte[] hinh = bh.getHinh(); //lay hing ra (kieu byte)
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinh,0,hinh.length); //chuyen thanh kieu bitmap
        holder.ivHinhBH.setImageBitmap(bitmap); //set hinh len
        return convertView;
    }
}
