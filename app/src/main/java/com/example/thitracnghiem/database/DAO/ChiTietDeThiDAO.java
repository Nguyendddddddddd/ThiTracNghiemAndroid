package com.example.thitracnghiem.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.thitracnghiem.database.Entity.CauHoi;
import com.example.thitracnghiem.database.Entity.CauHoiWithDeThi;
import com.example.thitracnghiem.database.Entity.ChiTietDeThi;
import com.example.thitracnghiem.database.Entity.DeThi;
import com.example.thitracnghiem.database.Entity.DeThiWithCauHoi;
import com.example.thitracnghiem.database.Entity.DeThiWithThiSinhs;
import com.example.thitracnghiem.database.Entity.ThiSinhWithDeThis;

import java.util.List;

@Dao
public interface ChiTietDeThiDAO {
    @Transaction
    @Query("Select * from DeThi")
    List<DeThiWithCauHoi> getDeThiWithCauHois();

    @Query("select * from chitietdethi")
    List<ChiTietDeThi> selectAll();

    @Transaction
    @Query("Select * from DeThi where MaDe =:MaDe")
    DeThiWithCauHoi getDeThiWithCauHois(String MaDe);


    @Query("Select * from ChiTietDeThi where MaDe =:maDe and MaCauHoi =:maCauHoi")
    ChiTietDeThi SelectByMaDeandMaCauHoi(String maDe,int maCauHoi);

    @Insert
    void insert(ChiTietDeThi chiTietDeThi);

    @Delete
    void delete(ChiTietDeThi chiTietDeThi);

    @Transaction
    @Query("Select * from cauhoi")
    List<CauHoiWithDeThi> getCauHoiWithDeThis();

    @Transaction
    @Query("Select * from cauhoi where MaCauHoi = :maCauHoi")
    CauHoiWithDeThi getCauHoiWithDeThis(String maCauHoi);
}
