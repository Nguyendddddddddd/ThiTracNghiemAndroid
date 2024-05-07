package com.example.thitracnghiem.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.thitracnghiem.database.Entity.CauHoi;
import com.example.thitracnghiem.database.Entity.DeThi;
import com.example.thitracnghiem.database.Entity.ThiSinh;

import java.util.List;

@Dao
public interface DeThiDAO {
    @Query("select * from dethi")
    List<DeThi> selectAll();

    @Query("Select * from DeThi where MaMon = :maMon")
    List<DeThi> selectAllByMaMH(String maMon);
    @Query("Select * from DeThi where MaDe = :maDT")
    DeThi selectAllById(String maDT);
    @Insert
    void insert(DeThi deThi);

    @Update
    void Update(DeThi deThi);
    @Delete
    void delete(DeThi deThi);
}
