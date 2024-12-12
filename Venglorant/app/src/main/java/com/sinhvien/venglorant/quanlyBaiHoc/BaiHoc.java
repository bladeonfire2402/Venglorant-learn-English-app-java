package com.sinhvien.venglorant.quanlyBaiHoc;

import java.io.Serializable;

public class BaiHoc implements Serializable {
    private int ma;
    private String ten;
    private String unit;
    private byte[] hinh;

    public BaiHoc() {
    }

    public BaiHoc(int ma, String ten, String unit, byte[] hinh) {
        this.ma = ma;
        this.ten = ten;
        this.unit = unit;
        this.hinh = hinh;
    }

    public int getMa(int position) {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }
}
