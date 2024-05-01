package com.example.thitracnghiem.database.Entity;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.Date;
import java.util.List;

public class DeThiWithCauHoi {
    @Embedded
    public DeThi deThi;

    @Relation(
            parentColumn = "MaDe",
            entityColumn = "MaCauHoi",
            associateBy = @Junction(ChiTietDeThi.class)
    )
    public List<CauHoi> cauHois;
}
