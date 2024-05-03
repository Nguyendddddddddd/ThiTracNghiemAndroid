package com.example.thitracnghiem;

import static com.example.thitracnghiem.R.id.edtTenMon;

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
import com.example.thitracnghiem.database.Entity.MonHoc;

public class SuaMonHoc extends AppCompatActivity {
    private Button btnSuaMonHoc;
    private TextView edtMaMon;
    private TextView edtTenMon;
    private MonHoc mon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sua_mon_hoc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtMaMon = findViewById(R.id.edtMaMon);
        edtTenMon = findViewById(R.id.edtTenMon);
        btnSuaMonHoc = findViewById(R.id.btnSuaMon);
        mon =(MonHoc) getIntent().getExtras().get("Mon hoc");
        if(mon!=null) {
            edtTenMon.setText(mon.getTenMon());
            edtMaMon.setText(mon.getMaMH());
        }
        btnSuaMonHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMonHoc();
            }
        });
    }

    private void updateMonHoc() {
        String maMon = edtMaMon.getText().toString();
        String tenMon = edtTenMon.getText().toString();
        if (maMon.trim().length() == 0 || tenMon.trim().length() == 0) {
            Toast.makeText(this, "Nhập thiếu thông tin!", Toast.LENGTH_LONG).show();
            return;
        }
        if (!maMon.trim().equals(mon.getMaMH().trim())) {
            Toast.makeText(this, "Không được sửa mã môn", Toast.LENGTH_LONG).show();
            edtMaMon.setText(mon.getMaMH());
            return;
        }

        mon.setMaMH(maMon);
        mon.setTenMon(tenMon);
        DBThiTracNghiem.getInstance(this).monHocDAO().update(mon);
        Toast.makeText(this, "Sửa môn học thành công!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK,intent);
        finish();
    }

}