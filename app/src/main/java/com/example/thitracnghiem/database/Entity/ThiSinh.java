package com.example.thitracnghiem.database.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity
public class ThiSinh implements Serializable {
    @PrimaryKey()
    @NonNull
    private String MaTS;
    private String HoTen;
    private String MaLop;
    private String TenDangNhap;

    public ThiSinh() {
    }

    public ThiSinh(String maTS, String hoTen, String maLop, String tenDangNhap) {
        MaTS = maTS;
        HoTen = hoTen;
        MaLop = maLop;
        TenDangNhap = tenDangNhap;
    }

    public String getMaTS() {
        return MaTS;
    }

    public String getHoTen() {
        return HoTen;
    }


    public String getMaLop() {
        return MaLop;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setMaTS(String maTS) {
        MaTS = maTS;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }


    public void setMaLop(String maLop) {
        MaLop = maLop;
    }

    public void setTenDangNhap(String tenDangNhap) {
        TenDangNhap = tenDangNhap;
    }
}
