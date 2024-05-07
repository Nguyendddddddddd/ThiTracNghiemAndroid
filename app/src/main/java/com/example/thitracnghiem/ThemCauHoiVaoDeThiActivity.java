package com.example.thitracnghiem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.thitracnghiem.Adapter.AdapterCauHoi;
import com.example.thitracnghiem.Adapter.AdapterThemCauHoiVaoDe;
import com.example.thitracnghiem.database.DBThiTracNghiem;
import com.example.thitracnghiem.database.Entity.CauHoi;
import com.example.thitracnghiem.database.Entity.ChiTietDeThi;
import com.example.thitracnghiem.database.Entity.DeThi;

import java.io.Serializable;
import java.util.List;

public class ThemCauHoiVaoDeThiActivity extends AppCompatActivity{

    private ListView lsvDSCauHoiDeThem;
    private AdapterThemCauHoiVaoDe adapter;
    private List<CauHoi> lstCauHoi;
    private DeThi deThi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_them_cau_hoi_vao_de_thi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lsvDSCauHoiDeThem = findViewById(R.id.lsvDSCauHoiDeThiDeThem);
        lstCauHoi = DBThiTracNghiem.getInstance(this).cauHoiDAO().selectAll();
        deThi = (DeThi) getIntent().getExtras().get("De thi");

        adapter = new AdapterThemCauHoiVaoDe(this, R.layout.layout_item_listview_themcauhoivaodethi, lstCauHoi, new AdapterThemCauHoiVaoDe.IClickInsert() {
            @Override
            public void clickInsert(CauHoi cauHoi) {
                insertCauHoiVaoDe(cauHoi);
            }
        });

        lsvDSCauHoiDeThem.setAdapter(adapter);
    }
    public void insertCauHoiVaoDe(CauHoi cauHoi){
        String maDe = deThi.getMaDe().trim();
        List<CauHoi> lsCHKiemTra = DBThiTracNghiem.getInstance(this).chiTietDeThiDAO().getDeThiWithCauHois(deThi.getMaDe()).cauHois;
        if(lsCHKiemTra.size()>=deThi.getTongSoCau()){
            Toast.makeText(this, "Đề thi đã đủ câu hỏi", Toast.LENGTH_LONG).show();
            return;
        }
        int maCauHoi = cauHoi.getMaCauHoi();
        ChiTietDeThi chiTietDeThi = new ChiTietDeThi(maCauHoi,maDe);
        DBThiTracNghiem db = DBThiTracNghiem.getInstance(this);
        db.chiTietDeThiDAO().insert(chiTietDeThi);
        Toast.makeText(this, "Thêm câu hỏi vào đề thành công", Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK,intent);
        finish();;
    }

}