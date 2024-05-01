package com.example.thitracnghiem.database.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Lop implements Serializable {
    @PrimaryKey
    @NonNull
    private String MaLop;
    private String TenLop;

    public Lop() {
    }

    public Lop(String maLop, String tenLop) {
        MaLop = maLop;
        TenLop = tenLop;
    }

    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String maLop) {
        MaLop = maLop;
    }

    public String getTenLop() {
        return TenLop;
    }

    public void setTenLop(String tenLop) {
        TenLop = tenLop;
    }
}
