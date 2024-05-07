package com.example.thitracnghiem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.thitracnghiem.database.DBThiTracNghiem;
import com.example.thitracnghiem.database.Entity.CauHoi;
import com.example.thitracnghiem.database.Entity.DeThi;

public class ThemDeThiActivity extends AppCompatActivity {
    private EditText edtMaDeThi,edtTenDeThi,edtThoiGianLamBai,edtTongSoCau;
    private Button btnThemDeThi;
    private String maMon;
    private DBThiTracNghiem db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_them_de_thi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = DBThiTracNghiem.getInstance(this);
        btnThemDeThi = findViewById(R.id.btnThemDeThi);
        edtMaDeThi = findViewById(R.id.edtMaDeThi);
        edtTenDeThi = findViewById(R.id.edtTenDeThi);
        edtThoiGianLamBai = findViewById(R.id.edtThoiGianLamBai);
        edtTongSoCau = findViewById(R.id.edtTongSoCauHoi);
        btnThemDeThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themDeThi();
            }
        });
    }

    public void themDeThi(){
        if(!isNumeric(edtThoiGianLamBai.getText().toString().trim())||!isNumeric(edtTongSoCau.getText().toString().trim())){
            Toast.makeText(this, "Tổng số câu hoặc thời gian làm bài vui lòng nhập số", Toast.LENGTH_LONG).show();
            return;
        }

        String ndMaDeThi = edtMaDeThi.getText().toString().trim();
        String ndTenDeThi = edtTenDeThi.getText().toString().trim();
        int ndThoiGianLamBai = Integer.parseInt(edtThoiGianLamBai.getText().toString());
        int ndTongSoCau = Integer.parseInt(edtTongSoCau.getText().toString());
        maMon = getIntent().getExtras().get("Ma mon").toString().trim();
        if (db.deThiDAO().selectAllById(ndMaDeThi) != null) {
            Toast.makeText(this, "Mã đề thi đã tồn tại!", Toast.LENGTH_LONG).show();
            return;
        }
        if(ndMaDeThi.trim().length()==0||ndTenDeThi.trim().length()==0||edtTongSoCau.length()==0||edtThoiGianLamBai.length()==0){
            Toast.makeText(this, "Chưa nhập câu hỏi", Toast.LENGTH_LONG).show();
            return;
        }
        DeThi deThi = new DeThi(ndMaDeThi,ndTenDeThi,maMon,ndThoiGianLamBai,ndTongSoCau);
        db.deThiDAO().insert(deThi);
        Toast.makeText(this, "Thêm đề thi thành công", Toast.LENGTH_LONG).show();
        Intent intent = getIntent();
        intent.putExtra("Ma mon",maMon);
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