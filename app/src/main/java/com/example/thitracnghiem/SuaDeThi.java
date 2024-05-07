package com.example.thitracnghiem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.thitracnghiem.database.DBThiTracNghiem;
import com.example.thitracnghiem.database.Entity.CauHoi;
import com.example.thitracnghiem.database.Entity.DeThi;
import com.example.thitracnghiem.database.Entity.Lop;
import com.example.thitracnghiem.database.Entity.MonHoc;

import java.util.ArrayList;
import java.util.List;

public class SuaDeThi extends AppCompatActivity {

    private Button btnSuaDeThi;
    private TextView edtMaDeThiSua,edtTenDeThiSua,edtThoiGianLamBaiSua,edtTongSoCauSua;
    private Spinner spnMonSua;
    private DeThi deThi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sua_de_thi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtMaDeThiSua = findViewById(R.id.edtMaDeThiSua);
        edtTenDeThiSua = findViewById(R.id.edtTenDeThiSua);
        edtThoiGianLamBaiSua = findViewById(R.id.edtThoiGianLamBaiSua);
        edtTongSoCauSua = findViewById(R.id.edtTongSoCauHoiSua);
        btnSuaDeThi = findViewById(R.id.btnSuaDeThiSua);
        spnMonSua = findViewById(R.id.spnMonSua);
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,getMaMons());
        spnMonSua.setAdapter(spnAdapter);
        deThi = (DeThi) getIntent().getExtras().get("De thi");
        if(deThi!=null) {
            edtMaDeThiSua.setText(deThi.getMaDe());
            edtTenDeThiSua.setText(deThi.getTenDe());
            edtThoiGianLamBaiSua.setText(deThi.getTGLamBai()+"");
            edtTongSoCauSua.setText(deThi.getTongSoCau()+"");
        }

        btnSuaDeThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDeThi();
            }
        });
    }

    private List<String> getMaMons(){
        List<String> lstMaMon = new ArrayList<>();
        List<MonHoc> lstMon = DBThiTracNghiem.getInstance(this).monHocDAO().selectAll();

        for (MonHoc m: lstMon) {
            lstMaMon.add(m.getMaMH());

        }
        return lstMaMon;
    }

    public void updateDeThi(){
        if(!isNumeric(edtThoiGianLamBaiSua.getText().toString().trim())||!isNumeric(edtTongSoCauSua.getText().toString().trim())){
            Toast.makeText(this, "Tổng số câu hoặc thời gian làm bài vui lòng nhập số", Toast.LENGTH_LONG).show();
            return;
        }
        String maDT = edtMaDeThiSua.getText().toString().trim();
        String tenDT = edtTenDeThiSua.getText().toString().trim();
        int TGLamBai = Integer.parseInt(edtThoiGianLamBaiSua.getText().toString());
        int tongSoCH = Integer.parseInt(edtTongSoCauSua.getText().toString());
        String maMon = spnMonSua.getSelectedItem().toString();
        if (maDT.length()==0||tenDT.length()==0||edtThoiGianLamBaiSua.getText().toString().trim().length()==0||edtTongSoCauSua.getText().toString().trim().length()==0) {
            Toast.makeText(this, "Nhập thiếu thông tin!", Toast.LENGTH_LONG).show();
            return;
        }
        deThi.setMaDe(maDT);
        deThi.setTenDe(tenDT);
        deThi.setTGLamBai(TGLamBai);
        deThi.setTongSoCau(tongSoCH);
        deThi.setMaMon(maMon);
        DBThiTracNghiem.getInstance(this).deThiDAO().Update(deThi);
        Toast.makeText(this, "Sửa đề thi thành công", Toast.LENGTH_LONG).show();
        Intent intent = getIntent();
        intent.putExtra("Ma mon",deThi.getMaMon());
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
    public boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}