package com.example.thitracnghiem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.thitracnghiem.database.DBThiTracNghiem;
import com.example.thitracnghiem.database.Entity.Lop;
import com.example.thitracnghiem.database.Entity.ThiSinh;

import java.util.ArrayList;
import java.util.List;

public class SuaThiSinh extends AppCompatActivity {
    ThiSinh thiSinh;
    EditText edtMaThiSinh,edtTenThiSinh;
    Spinner spnMaLop;
    Button btnSua;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sua_thi_sinh);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtMaThiSinh = findViewById(R.id.edtMaThiSinh);
        edtTenThiSinh = findViewById(R.id.edtTenThiSinh);
        spnMaLop = findViewById(R.id.spnLop);
        btnSua = findViewById(R.id.btnSua);
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,getMaLops());
        spnMaLop.setAdapter(spnAdapter);
        thiSinh = (ThiSinh) getIntent().getExtras().get("Thi sinh");
        edtTenThiSinh.setText(thiSinh.getHoTen());
        edtMaThiSinh.setText(thiSinh.getMaTS());
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateThiSinh();
            }
        });
    }

    private void updateThiSinh() {
        String maTS = edtMaThiSinh.getText().toString();
        String tenThiSinh = edtTenThiSinh.getText().toString();
        String maLop = spnMaLop.getSelectedItem().toString();

        if (maTS.trim().length() == 0 || tenThiSinh.trim().length() == 0) {
            Toast.makeText(this, "Nhập thiếu thông tin!", Toast.LENGTH_LONG).show();
            return;
        }
        thiSinh.setHoTen(tenThiSinh);
        thiSinh.setMaLop(maLop);
        DBThiTracNghiem.getInstance(this).thiSinhDAO().Update(thiSinh);
        Toast.makeText(this, "Sửa thí sinh thành công", Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK,intent);
        finish();
    }

    private List<String> getMaLops(){
        List<String> lstMaLop = new ArrayList<>();
        List<Lop> lstLop = DBThiTracNghiem.getInstance(this).lopDAO().selectAll();

        for (Lop l: lstLop) {
            lstMaLop.add(l.getMaLop());

        }
        return lstMaLop;
    }
}