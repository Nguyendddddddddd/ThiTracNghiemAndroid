package com.example.thitracnghiem.database.Entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.thitracnghiem.database.Entity.Lop;
import com.example.thitracnghiem.database.Entity.ThiSinh;

import java.util.List;

public class ThiSinhAndLop {
   @Embedded
   public Lop lop;
    @Relation(
            parentColumn = "MaLop",
            entityColumn = "MaLop"
    )
    public List<ThiSinh> thiSinhs;
}
