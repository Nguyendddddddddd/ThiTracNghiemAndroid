package com.example.thitracnghiem.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.thitracnghiem.database.Entity.CauHoi;
import com.example.thitracnghiem.database.Entity.DapAn;

import java.util.List;

@Dao
public interface DapAnDAO {

    @Query("select * from dapan where MaCauHoi = :maCH")
    List<DapAn> selectById(int maCH);

    @Insert
    void insert(DapAn dapAn);
    @Insert
    void insert(DapAn ...dapAn);

    @Update
    void update(List<DapAn> lstDapAn);

    @Delete
    void delete(List<DapAn> lsDapAn);

}
