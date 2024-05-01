package com.example.thitracnghiem.database.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.thitracnghiem.database.Entity.CauHoi;
import com.example.thitracnghiem.database.Entity.DeThi;

import java.util.List;

@Dao
public interface DeThiDAO {
    @Query("select * from dethi")
    List<DeThi> selectAll();
    @Insert
    void insert(DeThi deThi);
}
