package com.sinhvien.venglorant;

import java.io.Serializable;

public class User implements Serializable {
    private String maU;
    private String tenU;
    private String UserName;
    private String PassWord;
    private String phoneU;
    private String hinhU;

    public User() {
    }

    public User(String maU, String tenU, String userName, String passWord, String phoneU, String hinhU) {
        this.maU = maU;
        this.tenU = tenU;
        UserName = userName;
        PassWord = passWord;
        this.phoneU = phoneU;
        this.hinhU = hinhU;
    }

    public String getMaU() {
        return maU;
    }

    public void setMaU(String maU) {
        this.maU = maU;
    }

    public String getTenU() {
        return tenU;
    }

    public void setTenU(String tenU) {
        this.tenU = tenU;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public String getPhoneU() {
        return phoneU;
    }

    public void setPhoneU(String phoneU) {
        this.phoneU = phoneU;
    }

    public String getHinhU() {
        return hinhU;
    }

    public void setHinhU(String hinhU) {
        this.hinhU = hinhU;
    }
}
