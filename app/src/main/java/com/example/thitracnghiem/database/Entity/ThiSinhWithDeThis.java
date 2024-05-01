package com.example.thitracnghiem.database.Entity;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class ThiSinhWithDeThis {
    @Embedded
    public ThiSinh thiSinh;

    @Relation(
            parentColumn = "MaTS",
            entityColumn = "MaDe",
            associateBy = @Junction(KetQua.class)
    )
    public List<DeThi> deThis;
}
