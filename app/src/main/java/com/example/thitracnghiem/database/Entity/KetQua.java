package com.example.thitracnghiem.database.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(primaryKeys = {"MaTS","MaDe"})
public class KetQua {

    @NonNull
    private String MaTS;
    @NonNull
    private String MaDe;
    private Date NgayThi;
    private float Diem;

    public KetQua(String maTS, String maDe, Date ngayThi, float diem) {
        MaTS = maTS;
        MaDe = maDe;
        NgayThi = ngayThi;
        Diem = diem;
    }
    public KetQua(@NonNull String maTS, @NonNull String maDe) {
        MaTS = maTS;
        MaDe = maDe;
    }
    public KetQua() {

    }


    public String getMaTS() {
        return MaTS;
    }

    public void setMaTS(String maTS) {
        MaTS = maTS;
    }

    public String getMaDe() {
        return MaDe;
    }

    public void setMaDe(String maDe) {
        MaDe = maDe;
    }

    public Date getNgayThi() {
        return NgayThi;
    }

    public void setNgayThi(Date ngayThi) {
        NgayThi = ngayThi;
    }

    public float getDiem() {
        return Diem;
    }

    public void setDiem(float diem) {
        Diem = diem;
    }
}
