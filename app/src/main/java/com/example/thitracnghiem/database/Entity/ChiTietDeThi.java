package com.example.thitracnghiem.database.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"MaCauHoi", "MaDe"})
public class ChiTietDeThi {
    @NonNull
    private int MaCauHoi;
    @NonNull
    private String MaDe;

    public ChiTietDeThi(@NonNull int maCauHoi, @NonNull String maDe) {
        MaCauHoi = maCauHoi;
        MaDe = maDe;
    }
    public ChiTietDeThi() {
    }

    @NonNull
    public int getMaCauHoi() {
        return MaCauHoi;
    }

    public void setMaCauHoi(@NonNull int maCauHoi) {
        MaCauHoi = maCauHoi;
    }

    @NonNull
    public String getMaDe() {
        return MaDe;
    }

    public void setMaDe(@NonNull String maDe) {
        MaDe = maDe;
    }
}
