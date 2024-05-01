package com.example.thitracnghiem.database.Entity;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class CauHoiWithDeThi {
    @Embedded
    public CauHoi cauHoi;

    @Relation(
            parentColumn = "MaCauHoi",
            entityColumn = "MaDe",
            associateBy = @Junction(ChiTietDeThi.class)
    )
    public List<DeThi> deThis;
}
