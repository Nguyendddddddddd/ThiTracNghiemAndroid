package com.example.thitracnghiem.database.Entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class MonHocAndCauHoi {
    @Embedded
    public MonHoc monHoc;
    @Relation(
            parentColumn = "MaMH",
            entityColumn = "MaMon"
    )
    public List<CauHoi> CauHois;
}
