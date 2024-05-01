package com.example.thitracnghiem.database.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.thitracnghiem.database.Entity.CauHoi;
import com.example.thitracnghiem.database.Entity.DeThiWithThiSinhs;
import com.example.thitracnghiem.database.Entity.KetQua;
import com.example.thitracnghiem.database.Entity.ThiSinhWithDeThis;

import java.util.List;

@Dao
public interface KetQuaDAO {
    @Query("select * from KetQua")
    List<KetQua> selectAll();
    @Insert
    void insert(KetQua ketQua);

    @Transaction
    @Query("Select * from DeThi")
    List<DeThiWithThiSinhs> getDeThiWithThiSinhs();

    @Transaction
    @Query("Select * from ThiSinh")
    List<ThiSinhWithDeThis> getThiSinhWithDeThis();

    @Transaction
    @Query("Select * from ThiSinh where MaTS=:maTS")
    ThiSinhWithDeThis getThiSinhWithDeThis(String maTS);
}
