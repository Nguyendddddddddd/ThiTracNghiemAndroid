package com.example.thitracnghiem.database.Entity;

import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class CauHoi implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int MaCauHoi;
    private String NoiDung;
    private String MaMon;

    public CauHoi(int maCauHoi, String noiDung, String maMon) {
        MaCauHoi = maCauHoi;
        NoiDung = noiDung;
        MaMon = maMon;
    }

    public CauHoi(String noiDung, String maMon) {
        NoiDung = noiDung;
        MaMon = maMon;
    }

    public CauHoi() {

    }

    public int getMaCauHoi() {
        return MaCauHoi;
    }

    public void setMaCauHoi(int maCauHoi) {
        MaCauHoi = maCauHoi;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getMaMon() {
        return MaMon;
    }

    public void setMaMon(String maMon) {
        MaMon = maMon;
    }
}
