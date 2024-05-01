package com.example.thitracnghiem.database.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.thitracnghiem.database.Entity.CauHoi;
import com.example.thitracnghiem.database.Entity.DapAn;

import java.util.List;

@Dao
public interface DapAnDAO {
    @Query("select * from cauhoi")
    List<CauHoi> selectAll();
    @Insert
    void insert(DapAn dapAn);
    @Insert
    void insert(DapAn ...dapAn);
}
