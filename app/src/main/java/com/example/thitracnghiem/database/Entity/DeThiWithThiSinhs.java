package com.example.thitracnghiem.database.Entity;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class DeThiWithThiSinhs {
    @Embedded
    public DeThi deThi;

    @Relation(
            parentColumn = "MaDe",
            entityColumn = "MaTS",
            associateBy = @Junction(KetQua.class)
    )
    public List<ThiSinh> ThiSinhs;
}
