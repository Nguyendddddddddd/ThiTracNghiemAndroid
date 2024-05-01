package com.example.thitracnghiem.database.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.thitracnghiem.database.Entity.CauHoi;
import com.example.thitracnghiem.database.Entity.MonHoc;
import com.example.thitracnghiem.database.Entity.MonHocAndCauHoi;

import java.util.List;

@Dao
public interface MonHocDAO {
    @Query("select * from MonHoc")
    List<MonHoc> selectAll();
    @Query("select * from MonHoc where MaMH= :mamh")
    MonHoc selectByID(String mamh);
    @Insert
    void insert(MonHoc monHoc);
    @Transaction
    @Query("select * from monhoc")
    List<MonHocAndCauHoi> selectMonHocAndCauHoi();
}
