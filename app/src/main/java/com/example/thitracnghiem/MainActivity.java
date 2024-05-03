package com.example.thitracnghiem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.thitracnghiem.database.DAO.TaiKhoanDAO;
import com.example.thitracnghiem.database.DBThiTracNghiem;
import com.example.thitracnghiem.database.Entity.CauHoi;
import com.example.thitracnghiem.database.Entity.CauHoiAndDapAn;
import com.example.thitracnghiem.database.Entity.ChiTietDeThi;
import com.example.thitracnghiem.database.Entity.DapAn;
import com.example.thitracnghiem.database.Entity.DeThi;
import com.example.thitracnghiem.database.Entity.DeThiWithCauHoi;
import com.example.thitracnghiem.database.Entity.KetQua;
import com.example.thitracnghiem.database.Entity.MonHoc;
import com.example.thitracnghiem.database.Entity.TaiKhoan;
import com.example.thitracnghiem.database.Entity.ThiSinh;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int RQ_CODE = 10;
    Button btnDangNhap,btnTest;

    EditText edtTenDangNhap, edtMatKhau;
    DBThiTracNghiem db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initUi();
        db = DBThiTracNghiem.getInstance(this);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangNhap();
            }
        });
        btnTest.setOnClickListener(v -> {
            fakeData();
        });
    }


    private void initUi() {
        btnDangNhap = findViewById(R.id.btnDangNhap);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        edtTenDangNhap = findViewById(R.id.edtTenDangNhap);
        btnTest = findViewById(R.id.btnTest);
    }

    private void DangNhap() {
       /* TaiKhoan tkAdmin = new TaiKhoan("1","1",0);
        TaiKhoan tkTS = new TaiKhoan("TS01","1",1);
        db.taiKhoanDAO().insert(tkTS);
        db.taiKhoanDAO().insert(tkAdmin);*/
        fakeData();
        String tenDangNhap = edtTenDangNhap.getText().toString();
        String matKhau = edtMatKhau.getText().toString();
        TaiKhoan tk = db.taiKhoanDAO().selectByTenDangNhap(tenDangNhap);
        if (tk != null && tk.getMatKhau().equals(matKhau))
            if (tk.getQuyen() == 0) {
                Intent intent = new Intent(this, AdminActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, ThiSinhActivity.class);
                Bundle bundle = new Bundle();
                ThiSinh tsDangNhap = db.thiSinhDAO().selectAllById(tenDangNhap);
                if (tsDangNhap != null) {
                    Log.i("Thong tin","Khac null");
                    bundle.putSerializable("Thi sinh",tsDangNhap);
                    intent.putExtras(bundle);
                    startActivityForResult(intent,RQ_CODE);
                }

            }

    }

    private void fakeData() {
     /*   MonHoc mToan = new MonHoc("TOAN","Toán");
        db.monHocDAO().insert(mToan);
        DeThi dt01 = new DeThi("DT01","Kiểm tra 15 phút","TOAN",15,10);
        db.deThiDAO().insert(dt01);
        for(int i = 0;i<10;i++){
            db.cauHoiDAO().insert(new CauHoi(0,"1+1=?","TOAN"));
        }
        for(CauHoi ch:db.cauHoiDAO().selectAll()){
            db.dapAnDAO().insert(new DapAn(0,"1",false,ch.getMaCauHoi()));
            db.dapAnDAO().insert(new DapAn(0,"2",true,ch.getMaCauHoi()));
            db.dapAnDAO().insert(new DapAn(0,"3",false,ch.getMaCauHoi()));
            db.dapAnDAO().insert(new DapAn(0,"4",false,ch.getMaCauHoi()));
        }
        for (CauHoi ch:db.cauHoiDAO().selectAll()){
            db.chiTietDeThiDAO().insert(new ChiTietDeThi(ch.getMaCauHoi(),"DT01"));
        }

*/

      /*  for(DeThiWithCauHoi deThi:db.chiTietDeThiDAO().getDeThiWithCauHois()){
            Log.i("Log",deThi.deThi.getTenDe());
            for (CauHoi ch : deThi.cauHois){
               Log.i("Log",ch.getNoiDung());
              CauHoiAndDapAn chada = db.cauHoiDAO().selectCauHoiAndDapAn(ch.getMaCauHoi());
                for(DapAn da:chada.dapAns){
                    Log.i("Log",da.getNoiDung());
                }
            }
        }*/

      /*  KetQua kq = new KetQua("TS02","DT01");
        db.ketQuaDAO().insert(kq);*/
        /*List<KetQua> kq = db.ketQuaDAO().selectAll();

        kq.forEach(k->{
            Log.i("Thông tin",k.getDiem()+"");
            Log.i("Thông tin",k.getNgayThi()==null?"null":"co thi");

        });*/

//        TaiKhoan tk = new TaiKhoan("tk","1",0);
//        db.taiKhoanDAO().insert(tk);

    }


}