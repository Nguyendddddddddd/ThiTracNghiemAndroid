package com.example.thitracnghiem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.thitracnghiem.database.DBThiTracNghiem;
import com.example.thitracnghiem.database.Entity.CauHoi;
import com.example.thitracnghiem.database.Entity.DapAn;

public class ThemCauHoiActivity extends AppCompatActivity{

    private Button btnThemCH;
    private String maMon;
    private EditText edtNoiDung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_them_cau_hoi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtNoiDung = findViewById(R.id.edtNhapCauHoi);
        btnThemCH = findViewById(R.id.btnThemCauHoi);
        btnThemCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themCauHoi();
            }
        });
    }
    public void themCauHoi(){
        String noiDungCH = edtNoiDung.getText().toString().trim();
        maMon = getIntent().getExtras().get("Ma mon").toString().trim();
        if(edtNoiDung.length()==0){
            Toast.makeText(this, "Chưa nhập câu hỏi", Toast.LENGTH_LONG).show();
            return;
        }
        DBThiTracNghiem db = DBThiTracNghiem.getInstance(this);
        CauHoi cauHoi = new CauHoi(0,noiDungCH,maMon);
        db.cauHoiDAO().insert(cauHoi);
        Toast.makeText(this, "Thêm câu hỏi thành công", Toast.LENGTH_LONG).show();
        Intent intent1 = getIntent();
        intent1.putExtra("Ma mon",maMon);
        setResult(Activity.RESULT_OK,intent1);
        finish();
    }

}

















