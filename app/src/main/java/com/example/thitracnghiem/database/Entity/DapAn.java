package com.example.thitracnghiem.database.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DapAn {
    @PrimaryKey(autoGenerate = true)
    private int MaDA;
    private  String noiDung;
    private boolean DungSai;
    private int MaCauHoi;

    public DapAn( int maDA, String noiDung, boolean dungSai, int maCauHoi) {
        MaDA = maDA;
        this.noiDung = noiDung;
        DungSai = dungSai;
        MaCauHoi = maCauHoi;
    }
    public DapAn(String noiDung, boolean dungSai, int maCauHoi) {
        this.noiDung = noiDung;
        DungSai = dungSai;
        MaCauHoi = maCauHoi;
    }

    public DapAn() {
    }

    public int getMaDA() {
        return MaDA;
    }

    public void setMaDA(int maDA) {
        MaDA = maDA;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public boolean isDungSai() {
        return DungSai;
    }

    public void setDungSai(boolean dungSai) {
        DungSai = dungSai;
    }

    public int getMaCauHoi() {
        return MaCauHoi;
    }

    public void setMaCauHoi(int maCauHoi) {
        MaCauHoi = maCauHoi;
    }


}
