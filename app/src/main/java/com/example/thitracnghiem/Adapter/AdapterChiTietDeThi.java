package com.example.thitracnghiem.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thitracnghiem.R;
import com.example.thitracnghiem.database.Entity.CauHoi;
import com.example.thitracnghiem.database.Entity.ChiTietDeThi;

import java.util.List;

public class AdapterChiTietDeThi extends ArrayAdapter<CauHoi> {
    private Activity Context;
    private int IdLayout;
    private List<CauHoi> lstChiTietDeThi;
    public AdapterChiTietDeThi.IClickDelete deleteCauHoi;
    public interface IClickDelete {
        void clickDelete(CauHoi cauHoi);
    }

    public AdapterChiTietDeThi(Activity context, int idLayout, List<CauHoi> lstChiTietDeThi, AdapterChiTietDeThi.IClickDelete deleteCauHoi) {
        super(context, idLayout, lstChiTietDeThi);
        this.deleteCauHoi = deleteCauHoi;
        this.Context = context;
        this.IdLayout = idLayout;
        this.lstChiTietDeThi = lstChiTietDeThi;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = Context.getLayoutInflater();
        convertView = inflater.inflate(IdLayout, null);
        CauHoi cauHoi = lstChiTietDeThi.get(position);
        TextView tvMaDeTrongDT = convertView.findViewById(R.id.tvMaDeThiTrongDT);
        TextView tvMaCauHoi = convertView.findViewById(R.id.tvMaCauHoiTrongDT);
        Button btnXoaCHTrongDT = convertView.findViewById(R.id.btnXoaCauHoiTrongDT);
        btnXoaCHTrongDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCauHoi.clickDelete(cauHoi);
            }
        });

        tvMaDeTrongDT.setText("Nội dung: "+cauHoi.getNoiDung());
        tvMaCauHoi.setText("Mã câu hỏi: "+cauHoi.getMaCauHoi()+"");

        return convertView;
    }

}
