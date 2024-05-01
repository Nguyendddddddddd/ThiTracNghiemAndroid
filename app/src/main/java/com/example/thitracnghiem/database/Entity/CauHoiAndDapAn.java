package com.example.thitracnghiem.database.Entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CauHoiAndDapAn {
    @Embedded
    public CauHoi cauHoi;
    @Relation(parentColumn = "MaCauHoi", entityColumn = "MaCauHoi")
    public List<DapAn> dapAns;
}
