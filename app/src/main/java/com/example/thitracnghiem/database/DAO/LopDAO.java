package com.example.thitracnghiem.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.thitracnghiem.database.Entity.Lop;
import com.example.thitracnghiem.database.Entity.TaiKhoan;
import com.example.thitracnghiem.database.Entity.ThiSinhAndLop;

import java.util.List;

@Dao
public interface LopDAO {

    @Query("select * from lop")
    List<Lop> selectAll();

    @Query("select * from lop where MaLop = :malop")
    Lop selectAllById(String malop);
    @Insert
    void insert(Lop lop);
    @Delete
    void delete(Lop lop);
    @Update
    void update(Lop lop);
    @Transaction
    @Query("Select * from Lop")
    List<ThiSinhAndLop> selectThiSinhAndLop();
}
