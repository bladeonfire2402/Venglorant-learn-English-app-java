package com.sinhvien.venglorant.quanlyBaiHoc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DBAppHocTA extends SQLiteOpenHelper {
    public DBAppHocTA(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    // ham xoa bai hoc
    public int deleteBaiHoc(int maBH) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete("tblBaihoc", "maBH = ?", new String[]{String.valueOf(maBH)});
        db.close();
        return rowsDeleted;
    }
    // ham cap nhat bai hoc
    public long updateBaiHoc(int maBH, ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.update("tblBaihoc", values, "maBH = ?", new String[]{String.valueOf(maBH)});
    }


    public long updateUser(int maU, ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.update("tblUser", values, "maU = ?", new String[]{String.valueOf(maU)});
    }
    public int deleteUser(int maU) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete("tblUser", "maU = ?", new String[]{String.valueOf(maU)});
        db.close();
        return rowsDeleted;
    }
    // tao ham truy van create, delete, update
    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    // tao ham truy van select
    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    // tao ham insert record -> tao dong moi trong bang database
    public void InsertRecordBH(String ten, String unit, byte[]hinh){
        try{
            // hinh trong ImageView la Bitmap
            // chuyen bitmap ve byte[]
            // tien hanh luu

            SQLiteDatabase database = getWritableDatabase();
            String sql = "insert into tblBaihoc values (null,?,?,?)"; // maBH ko lam viec toi nen de la null
            SQLiteStatement statement = database.compileStatement(sql);
            statement.clearBindings();
            statement.bindString(1,ten); //maBH o cot 0 nen la luu tenBH o cot 1
            statement.bindString(2,unit);
            statement.bindBlob(3,hinh);
            statement.executeInsert();
            // xu ly hinh Bitmap -> byte[] -> save
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void InsertRecordUser(String ten, String username, String password, byte[]hinh){
        try{
            // hinh trong ImageView la Bitmap
            // chuyen bitmap ve byte[]
            // tien hanh luu

            SQLiteDatabase database = getWritableDatabase();
            String sql = "insert into tblUser values (null,?,?,?,?)"; // maBH ko lam viec toi nen de la null
            SQLiteStatement statement = database.compileStatement(sql);
            statement.clearBindings();
            statement.bindString(1,ten); //maBH o cot 0 nen la luu tenBH o cot 1
            statement.bindString(2,username);
            statement.bindString(3,password);
            statement.bindBlob(4,hinh);
            statement.executeInsert();
            // xu ly hinh Bitmap -> byte[] -> save
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
