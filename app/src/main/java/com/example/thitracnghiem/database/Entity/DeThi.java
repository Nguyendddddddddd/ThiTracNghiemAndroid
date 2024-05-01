package com.example.thitracnghiem.database.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity
public class DeThi {
    @PrimaryKey
    @NonNull
    private String MaDe;
    private String TenDe;
    private String MaMon;
    private int TGLamBai;
    private int TongSoCau;

    public DeThi(String maDe, String tenDe, String maMon, int TGLamBai, int tongSoCau) {
        MaDe = maDe;
        TenDe = tenDe;
        MaMon = maMon;
        this.TGLamBai = TGLamBai;
        TongSoCau = tongSoCau;
    }
    public DeThi() {

    }
    public String getMaDe() {
        return MaDe;
    }

    public void setMaDe(String maDe) {
        MaDe = maDe;
    }

    public String getTenDe() {
        return TenDe;
    }

    public void setTenDe(String tenDe) {
        TenDe = tenDe;
    }

    public String getMaMon() {
        return MaMon;
    }

    public void setMaMon(String maMon) {
        MaMon = maMon;
    }

    public int getTGLamBai() {
        return TGLamBai;
    }

    public void setTGLamBai(int TGLamBai) {
        this.TGLamBai = TGLamBai;
    }

    public int getTongSoCau() {
        return TongSoCau;
    }

    public void setTongSoCau(int tongSoCau) {
        TongSoCau = tongSoCau;
    }
}
