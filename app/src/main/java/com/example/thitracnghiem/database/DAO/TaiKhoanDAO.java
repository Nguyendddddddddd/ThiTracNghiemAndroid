package com.example.thitracnghiem.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.thitracnghiem.database.Entity.Lop;
import com.example.thitracnghiem.database.Entity.TaiKhoan;

import java.util.List;

@Dao
public interface TaiKhoanDAO {
    @Query("select * from taikhoan")
    List<TaiKhoan> selectAll();
    @Query("select * from taikhoan where TenDangNhap = :tenDangNhap")
    TaiKhoan selectByTenDangNhap(String tenDangNhap);

    @Insert
    void insert(TaiKhoan tk);

    @Delete
    void delete(TaiKhoan taiKhoan);
}
