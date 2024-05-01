package com.example.thitracnghiem.database.Entity;

import androidx.room.Embedded;
import androidx.room.Relation;

public class ThiSinhAddTaiKhoan {
    @Embedded
    public TaiKhoan taiKhoang;

    @Relation(
            parentColumn = "TenDangNhap",
            entityColumn = "TenDangNhap"
    )
    public ThiSinh thiSinh;

}
