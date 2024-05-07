package com.example.thitracnghiem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.thitracnghiem.Adapter.AdapterChiTietDeThi;
import com.example.thitracnghiem.Adapter.AdapterMonHoc;
import com.example.thitracnghiem.database.DBThiTracNghiem;
import com.example.thitracnghiem.database.Entity.CauHoi;
import com.example.thitracnghiem.database.Entity.ChiTietDeThi;
import com.example.thitracnghiem.database.Entity.DeThi;
import com.example.thitracnghiem.database.Entity.DeThiWithCauHoi;
import com.example.thitracnghiem.database.Entity.MonHoc;

import java.util.List;

public class ChiTietDeThiActivity extends AppCompatActivity {

    private TextView tvMaDe,tvTenDe,tvMaMon,tvTGLamBai,tvTongCH;
    private Button btnThemCauHoi;
    private ListView lsvDSCauHoi;
    private DeThi deThi;
    private List<CauHoi> lstCauHoiTrongDe;
    private AdapterChiTietDeThi adapterChiTietDeThi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chi_tiet_de_thi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvMaDe = findViewById(R.id.tvMaDeThiCT);
        tvTenDe = findViewById(R.id.tvTenDeThiCT);
        tvMaMon = findViewById(R.id.tvMaMonCT);
        tvTGLamBai = findViewById(R.id.tvThoiGianLamBaiCT);
        tvTongCH = findViewById(R.id.tvTongSoCauCT);
        btnThemCauHoi = findViewById(R.id.btnThemCauHoiVaoDe);
        lsvDSCauHoi = findViewById(R.id.lsvDSCauHoiDeThi);
        deThi = (DeThi) getIntent().getExtras().get("De thi");
        lstCauHoiTrongDe = DBThiTracNghiem.getInstance(this).chiTietDeThiDAO().getDeThiWithCauHois(deThi.getMaDe()).cauHois;
        adapterChiTietDeThi = new AdapterChiTietDeThi(this, R.layout.layout_item_listview_cauhoitrongdethi, lstCauHoiTrongDe, new AdapterChiTietDeThi.IClickDelete(){
            @Override
            public void clickDelete(CauHoi cauHoi) {
                deleteChiTietDeThi(cauHoi);
            }
        });

        lsvDSCauHoi.setAdapter(adapterChiTietDeThi);

        if(deThi!=null){
            tvMaDe.setText("Mã đề thi: "+deThi.getMaDe());
            tvTenDe.setText("Tên đề thi: "+deThi.getTenDe());
            tvMaMon.setText("Mã môn học: "+deThi.getMaMon());
            tvTGLamBai.setText("Thời gian làm bài: "+deThi.getTGLamBai()+" phút");
            tvTongCH.setText("Tổng số câu hỏi: "+deThi.getTongSoCau()+" câu");
        }
        btnThemCauHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietDeThiActivity.this, ThemCauHoiVaoDeThiActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("De thi",deThi);
                intent.putExtras(bundle);
                /*startActivity(intent);*/
                startActivityForResult(intent,20);
            }
        });
    }

    public void deleteChiTietDeThi(CauHoi cauHoi){
        ChiTietDeThi chiTietDeThi = DBThiTracNghiem.getInstance(this).chiTietDeThiDAO().SelectByMaDeandMaCauHoi(deThi.getMaDe(),cauHoi.getMaCauHoi());
        DBThiTracNghiem.getInstance(this).chiTietDeThiDAO().delete(chiTietDeThi);
        Toast.makeText(this, "Xóa câu hỏi khỏi đề thành công", Toast.LENGTH_LONG).show();
        lstCauHoiTrongDe.clear();
        lstCauHoiTrongDe.addAll(DBThiTracNghiem.getInstance(this).chiTietDeThiDAO().getDeThiWithCauHois(deThi.getMaDe()).cauHois);
        adapterChiTietDeThi.notifyDataSetChanged();
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 20 && resultCode == Activity.RESULT_OK) {
            lstCauHoiTrongDe.clear();
            lstCauHoiTrongDe.addAll(DBThiTracNghiem.getInstance(this).chiTietDeThiDAO().getDeThiWithCauHois(deThi.getMaDe()).cauHois);
            adapterChiTietDeThi.notifyDataSetChanged();
        }
    }
}














