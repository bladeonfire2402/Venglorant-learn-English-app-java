package com.sinhvien.venglorant.quanlyuser;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinhvien.venglorant.R;
import com.sinhvien.venglorant.quanlyBaiHoc.BaiHoc;
import com.sinhvien.venglorant.quanlyBaiHoc.BaiHocAdapter;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<User> arrayListUser;
    @Override
    public int getCount() {
        return arrayListUser.size();
    }

    // khai bao contructor
    public UserAdapter(Context context, int layout, ArrayList<User> arrayListUser) {
        this.context = context;
        this.layout = layout;
        this.arrayListUser = arrayListUser;
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
        TextView txtUserName,txtPassWord, txtTenU;
        ImageView ivHinhUserItem;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        UserAdapter.ViewHolder holder;
        if(convertView == null){
            holder = new UserAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            // tham chieu id layout_itembh -> holder
            holder.txtTenU = convertView.findViewById(R.id.txtTenU);
            holder.txtUserName= convertView.findViewById(R.id.txtUserName);
            holder.txtPassWord= convertView.findViewById(R.id.txtPassWord);
            holder.ivHinhUserItem= convertView.findViewById(R.id.ivHinhUserItem);
            convertView.setTag(holder);
        }
        else {
            holder= (UserAdapter.ViewHolder) convertView.getTag();
        }
        //get data from baihoc -> holder
        User user = arrayListUser.get(position);
        holder.txtTenU.setText(user.getTenU());
        holder.txtUserName.setText(user.getUsername());
        holder.txtPassWord.setText(user.getPassword());
        byte[] hinh = user.getHinhU(); //lay hing ra (kieu byte)
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinh,0,hinh.length); //chuyen thanh kieu bitmap
        holder.ivHinhUserItem.setImageBitmap(bitmap); //set hinh len
        return convertView;
    }
}
