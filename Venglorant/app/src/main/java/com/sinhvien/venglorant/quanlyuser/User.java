package com.sinhvien.venglorant.quanlyuser;

import java.io.Serializable;

public class User implements Serializable {
    private int maU;
    private String tenU;
    private String username;
    private String password;
    private byte[] hinhU;

    public User() {
    }

    public User(int maU, String tenU, String username, String password, byte[] hinhU) {
        this.maU = maU;
        this.tenU = tenU;
        this.username = username;
        this.password = password;
        this.hinhU = hinhU;
    }

    public int getMaU(int position) {
        return maU;
    }

    public void setMau(int maU) {
        this.maU = maU;
    }

    public String getTenU() {
        return tenU;
    }

    public void setTenU(String tenU) {
        this.tenU = tenU;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getHinhU() {
        return hinhU;
    }

    public void setHinhU(byte[] hinhU) {
        this.hinhU = hinhU;
    }
}
