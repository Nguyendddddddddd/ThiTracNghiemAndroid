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
import com.example.thitracnghiem.database.Entity.Lop;

import java.util.ArrayList;
import java.util.List;

public class SuaLopHoc extends AppCompatActivity {


    private Button btnSuaLopHoc;
    private TextView edtMaLop;

    private TextView edtTenLop;
    private Lop lop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sua_lop_hoc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtTenLop = findViewById(R.id.edtTenLop);
        edtMaLop = findViewById(R.id.edtMaLop);
        btnSuaLopHoc = findViewById(R.id.btnSua);
        lop =(Lop) getIntent().getExtras().get("Lop hoc");
        if(lop!=null) {
            edtTenLop.setText(lop.getTenLop());
            edtMaLop.setText(lop.getMaLop());
        }
        btnSuaLopHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateLopHoc();         
            }
        });
    }

    private void updateLopHoc() {
        String maLop = edtMaLop.getText().toString();
        String tenLop = edtTenLop.getText().toString();
        if (maLop.trim().length() == 0 || tenLop.trim().length() == 0) {
            Toast.makeText(this, "Nhập thiếu thông tin!", Toast.LENGTH_LONG).show();
            return;
        }
        if (!maLop.trim().equals(lop.getMaLop().trim())) {
            Toast.makeText(this, "Không được sửa mã lớp", Toast.LENGTH_LONG).show();
            edtMaLop.setText(lop.getMaLop());
            return;
        }

        lop.setMaLop(maLop);
        lop.setTenLop(tenLop);
        DBThiTracNghiem.getInstance(this).lopDAO().update(lop);
        Toast.makeText(this, "Sửa lớp thành công!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK,intent);
        finish();
    }


}