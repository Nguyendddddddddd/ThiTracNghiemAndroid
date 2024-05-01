package com.example.thitracnghiem.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.thitracnghiem.database.DAO.CauHoiDAO;
import com.example.thitracnghiem.database.DAO.ChiTietDeThiDAO;
import com.example.thitracnghiem.database.DAO.DapAnDAO;
import com.example.thitracnghiem.database.DAO.DeThiDAO;
import com.example.thitracnghiem.database.DAO.KetQuaDAO;
import com.example.thitracnghiem.database.DAO.LopDAO;
import com.example.thitracnghiem.database.DAO.MonHocDAO;
import com.example.thitracnghiem.database.DAO.TaiKhoanDAO;
import com.example.thitracnghiem.database.DAO.ThiSinhDAO;
import com.example.thitracnghiem.database.Entity.CauHoi;
import com.example.thitracnghiem.database.Entity.ChiTietDeThi;
import com.example.thitracnghiem.database.Entity.Converters;
import com.example.thitracnghiem.database.Entity.DapAn;
import com.example.thitracnghiem.database.Entity.DeThi;
import com.example.thitracnghiem.database.Entity.KetQua;
import com.example.thitracnghiem.database.Entity.Lop;
import com.example.thitracnghiem.database.Entity.MonHoc;
import com.example.thitracnghiem.database.Entity.TaiKhoan;
import com.example.thitracnghiem.database.Entity.ThiSinh;
import com.example.thitracnghiem.database.Entity.ThiSinhAndLop;

import java.util.Date;

@Database(entities =        {ThiSinh.class,
                            Lop.class,
                            TaiKhoan.class,
                            MonHoc.class,
                            CauHoi.class,
                            DapAn.class,
                            DeThi.class,
                            KetQua.class,
                            ChiTietDeThi.class},
        version = 1)
@TypeConverters({Converters.class})
public abstract class DBThiTracNghiem  extends RoomDatabase{
    private static DBThiTracNghiem instance;
    private static String DB_NAME = "ThiTracNghiem.db";

    public static DBThiTracNghiem getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),DBThiTracNghiem.class,DB_NAME)
                       .allowMainThreadQueries()
                       .build();
        }
        return instance;
    }
    public abstract TaiKhoanDAO taiKhoanDAO();
    public abstract ThiSinhDAO thiSinhDAO();
    public abstract LopDAO lopDAO();
    public abstract CauHoiDAO cauHoiDAO();
    public abstract KetQuaDAO ketQuaDAO();
    public abstract DeThiDAO deThiDAO();
    public abstract MonHocDAO monHocDAO();
    public abstract DapAnDAO dapAnDAO();
    public abstract ChiTietDeThiDAO chiTietDeThiDAO();
}
