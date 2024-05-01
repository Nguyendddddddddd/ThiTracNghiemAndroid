package com.example.thitracnghiem.database.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.thitracnghiem.database.Entity.CauHoi;
import com.example.thitracnghiem.database.Entity.CauHoiWithDeThi;
import com.example.thitracnghiem.database.Entity.ChiTietDeThi;
import com.example.thitracnghiem.database.Entity.DeThiWithCauHoi;
import com.example.thitracnghiem.database.Entity.DeThiWithThiSinhs;
import com.example.thitracnghiem.database.Entity.ThiSinhWithDeThis;

import java.util.List;

@Dao
public interface ChiTietDeThiDAO {
    @Transaction
    @Query("Select * from DeThi")
    List<DeThiWithCauHoi> getDeThiWithCauHois();

    @Transaction
    @Query("Select * from DeThi where MaDe =:MaDe")
    DeThiWithCauHoi getDeThiWithCauHois(String MaDe);
    @Insert
    void insert(ChiTietDeThi chiTietDeThi);

    @Transaction
    @Query("Select * from cauhoi")
    List<CauHoiWithDeThi> getCauHoiWithDeThis();

    @Transaction
    @Query("Select * from cauhoi where MaCauHoi = :maCauHoi")
    CauHoiWithDeThi getCauHoiWithDeThis(String maCauHoi);
}
