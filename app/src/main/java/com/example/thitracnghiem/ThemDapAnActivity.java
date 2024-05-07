package com.example.thitracnghiem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.thitracnghiem.database.Entity.DapAn;
import com.example.thitracnghiem.database.Entity.MonHoc;

import java.util.List;

public class ThemDapAnActivity extends AppCompatActivity {

    private EditText edtDapAnA,edtDapAnB,edtDapAnC,edtDapAnDung;
    private Button btnThem,btnXoa,btnSua;
    private  CauHoi cauHoi;
    private List<DapAn> lstDapAn;
    private  DBThiTracNghiem db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_them_dap_an);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtDapAnA = findViewById(R.id.edtDapAna);
        edtDapAnB = findViewById(R.id.edtDapAnb);
        edtDapAnC = findViewById(R.id.edtDapAnc);
        edtDapAnDung=findViewById(R.id.edtDapAnDung);
        btnThem = findViewById(R.id.btnThemDapAn);
        btnSua = findViewById(R.id.btnSuaDapAn);
        btnXoa = findViewById(R.id.btnXoaDapAn);
        cauHoi = (CauHoi) getIntent().getExtras().get("Cau hoi");
        db = DBThiTracNghiem.getInstance(this);
        lstDapAn = db.dapAnDAO().selectById(cauHoi.getMaCauHoi());
        if(lstDapAn.size()!=0){
            btnThem.setEnabled(false);
            edtDapAnA.setText(lstDapAn.get(0).getNoiDung().toString());
        edtDapAnB.setText(lstDapAn.get(1).getNoiDung().toString());
        edtDapAnC.setText(lstDapAn.get(2).getNoiDung().toString());
        edtDapAnDung.setText(lstDapAn.get(3).getNoiDung().toString());
        }else{
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);
        }

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themDapAn();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDapAn();
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("Bạn muốn xóa đáp án?").create().show();
            }
        });
    }
    public void themDapAn(){
        String noiDungDapAn_a = edtDapAnA.getText().toString().trim();
        String noiDungDapAn_b = edtDapAnB.getText().toString().trim();
        String noiDungDapAn_c = edtDapAnC.getText().toString().trim();
        String noiDungDapAn_dung = edtDapAnDung.getText().toString().trim();

        if(lstDapAn.size() == 4){
            Toast.makeText(this, "Số lượng đáp án quá 4 câu", Toast.LENGTH_SHORT).show();
            return;
        }
        if(edtDapAnA.length()==0||edtDapAnB.length()==0||edtDapAnC.length()==0||edtDapAnDung.length()==0){
            Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        DapAn dapAna = new DapAn(0,noiDungDapAn_a,false,cauHoi.getMaCauHoi());
        DapAn dapAnb = new DapAn(0,noiDungDapAn_b,false,cauHoi.getMaCauHoi());
        DapAn dapAnc = new DapAn(0,noiDungDapAn_c,false,cauHoi.getMaCauHoi());
        DapAn dapAndung = new DapAn(0,noiDungDapAn_dung,true,cauHoi.getMaCauHoi());
        db.dapAnDAO().insert(dapAna,dapAnb,dapAnc,dapAndung);
        Toast.makeText(this, "Thêm đáp án thành công", Toast.LENGTH_LONG).show();
        finish();
    }
    public void updateDapAn(){
        String noiDunga = edtDapAnA.getText().toString().trim();
        String noiDungb = edtDapAnB.getText().toString().trim();
        String noiDungc = edtDapAnC.getText().toString().trim();
        String noiDungdung = edtDapAnDung.getText().toString().trim();
        if (noiDunga.length() == 0||noiDungb.length() == 0||noiDungc.length() == 0||noiDungdung.length() == 0) {
            Toast.makeText(this, "Chưa nhập nội dung cần sửa", Toast.LENGTH_LONG).show();
            return;
        }
        lstDapAn.get(0).setNoiDung(noiDunga);
        lstDapAn.get(1).setNoiDung(noiDungb);
        lstDapAn.get(2).setNoiDung(noiDungc);
        lstDapAn.get(3).setNoiDung(noiDungdung);
        DBThiTracNghiem.getInstance(this).dapAnDAO().update(lstDapAn);
        Toast.makeText(this, "Sửa đáp án thành công!", Toast.LENGTH_LONG).show();
        finish();
    }
    public AlertDialog.Builder showDialog(String mss){
        AlertDialog.Builder dl = new AlertDialog.Builder(this);
        dl.setTitle("Thông báo");
        dl.setMessage(mss);
        dl.setCancelable(true);
        dl.setPositiveButton("Yes",new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        DBThiTracNghiem.getInstance(ThemDapAnActivity.this).dapAnDAO().delete(lstDapAn);
                        Toast.makeText(ThemDapAnActivity.this, "Xóa đáp án thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    }});
        dl.setNegativeButton("No",new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        arg0.cancel();
                    }});
        return  dl;
    }

}