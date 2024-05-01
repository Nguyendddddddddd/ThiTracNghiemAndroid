package com.example.thitracnghiem.database.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.thitracnghiem.database.Entity.CauHoi;
import com.example.thitracnghiem.database.Entity.CauHoiAndDapAn;

import java.util.List;

@Dao
public interface CauHoiDAO {
    @Query("select * from cauhoi")
    List<CauHoi> selectAll();
    @Insert
    void insert(CauHoi cauHoi);
    @Insert
        void insert(CauHoi ...cauHoi);
    @Transaction
    @Query("select * from cauhoi")
    List<CauHoiAndDapAn> selectCauHoiAndDapAn();

    @Transaction
    @Query("select * from cauhoi where MaCauHoi =:maCauHoi")
    CauHoiAndDapAn selectCauHoiAndDapAn(int maCauHoi);

}
