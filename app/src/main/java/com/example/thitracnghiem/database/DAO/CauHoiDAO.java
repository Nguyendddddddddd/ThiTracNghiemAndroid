package com.example.thitracnghiem.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.thitracnghiem.database.Entity.CauHoi;
import com.example.thitracnghiem.database.Entity.CauHoiAndDapAn;
import com.example.thitracnghiem.database.Entity.MonHoc;
import com.example.thitracnghiem.database.Entity.ThiSinh;

import java.util.List;

@Dao
public interface CauHoiDAO {
    @Query("select * from cauhoi")
    List<CauHoi> selectAll();

    @Query("Select * from CauHoi where MaCauHoi = :maCH")
    CauHoi selectAllById(String maCH);

    @Query("Select * from CauHoi where MaMon = :maMH")
    List<CauHoi> selectAllByMaMH(String maMH);

    @Insert
    void insert(CauHoi cauHoi);
    @Insert
        void insert(CauHoi ...cauHoi);

    @Update
    void update(CauHoi cauHoi);

    @Delete
    void delete(CauHoi cauHoi);
    @Transaction
    @Query("select * from cauhoi")
    List<CauHoiAndDapAn> selectCauHoiAndDapAn();

    @Transaction
    @Query("select * from cauhoi where MaCauHoi =:maCauHoi")
    CauHoiAndDapAn selectCauHoiAndDapAn(int maCauHoi);

}
