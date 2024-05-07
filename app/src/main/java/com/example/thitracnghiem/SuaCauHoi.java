package com.example.thitracnghiem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.thitracnghiem.database.DBThiTracNghiem;
import com.example.thitracnghiem.database.Entity.CauHoi;
import com.example.thitracnghiem.database.Entity.MonHoc;

public class SuaCauHoi extends AppCompatActivity {

    private Button btnSuaCauHoi;
    private TextView edtNoiDungSua;
    private CauHoi cauHoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sua_cau_hoi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSuaCauHoi = findViewById(R.id.btnSuaCauHoi);
        edtNoiDungSua = findViewById(R.id.edtNoiDungCauHoiSua);
        cauHoi =(CauHoi) getIntent().getExtras().get("Cau hoi");
        if(cauHoi!=null) {
            edtNoiDungSua.setText(cauHoi.getNoiDung());
        }
        btnSuaCauHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCauHoi();
            }
        });
    }

    private void updateCauHoi() {
        String noiDung = edtNoiDungSua.getText().toString();
        if (noiDung.trim().length() == 0) {
            Toast.makeText(this, "Chưa nhập nội dung cần sửa", Toast.LENGTH_LONG).show();
            return;
        }
        cauHoi.setNoiDung(noiDung);
        DBThiTracNghiem.getInstance(this).cauHoiDAO().update(cauHoi);
        Toast.makeText(this, "Sửa câu hỏi thành công!", Toast.LENGTH_LONG).show();
        Intent intent = getIntent();
        intent.putExtra("Ma mon",cauHoi.getMaMon());
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}