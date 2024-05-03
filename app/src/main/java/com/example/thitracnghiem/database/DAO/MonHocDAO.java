package com.example.thitracnghiem.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.thitracnghiem.database.Entity.CauHoi;
import com.example.thitracnghiem.database.Entity.Lop;
import com.example.thitracnghiem.database.Entity.MonHoc;
import com.example.thitracnghiem.database.Entity.MonHocAndCauHoi;

import java.util.List;

@Dao
public interface MonHocDAO {
    @Query("select * from MonHoc")
    List<MonHoc> selectAll();
    @Query("select * from MonHoc where MaMH= :mamh")
    MonHoc selectByID(String mamh);
    @Query("select * from MonHoc where MaMH = :maMon")
    MonHoc selectAllById(String maMon);
    @Insert
    void insert(MonHoc monHoc);

    @Update
    void update(MonHoc mon);

    @Delete
    void delete(MonHoc mon);
    @Transaction
    @Query("select * from monhoc")
    List<MonHocAndCauHoi> selectMonHocAndCauHoi();
}
