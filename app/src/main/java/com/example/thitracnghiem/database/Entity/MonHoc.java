package com.example.thitracnghiem.database.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class MonHoc implements Serializable {
    @PrimaryKey
    @NonNull
    private String MaMH;
    private String TenMon;

    public MonHoc() {
    }
    public MonHoc(String maMH, String tenMon) {
        MaMH = maMH;
        TenMon = tenMon;
    }

    public String getMaMH() {
        return MaMH;
    }

    public void setMaMH(String maMH) {
        MaMH = maMH;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String tenMon) {
        TenMon = tenMon;
    }
}
