package com.example.thitracnghiem.database.Entity;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TaiKhoan {
    @PrimaryKey
    @NonNull
    private String TenDangNhap;
    private String MatKhau;

    private int Quyen;

    public TaiKhoan() {
    }

    public int getQuyen() {
        return Quyen;
    }


    public void setQuyen(int quyen) {
        Quyen = quyen;
    }

    public TaiKhoan(String tenDangNhap, String matKhau,int quyen) {
        TenDangNhap = tenDangNhap;
        MatKhau = matKhau;
        Quyen = quyen;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        TenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }
}
