package com.example.thitracnghiem.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.thitracnghiem.database.Entity.Lop;
import com.example.thitracnghiem.database.Entity.TaiKhoan;
import com.example.thitracnghiem.database.Entity.ThiSinh;
import com.example.thitracnghiem.database.Entity.ThiSinhAddTaiKhoan;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ThiSinhDAO {
    @Query("Select * from ThiSinh")
    List<ThiSinh> selectAll();
    @Query("Select * from ThiSinh where MaTS = :maTS")
    ThiSinh selectAllById(String maTS);
    @Insert
    void insert(ThiSinh thiSinh);

    @Delete
    void delete(ThiSinh thiSinh);
    @Update
    void Update(ThiSinh thiSinh);
    @Transaction
    @Query("select * from TaiKhoan")
    List<ThiSinhAddTaiKhoan> selectThiSinhAndTaiKhoang();

}
