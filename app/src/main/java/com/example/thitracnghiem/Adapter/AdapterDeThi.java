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
import com.example.thitracnghiem.database.Entity.DeThi;
import com.example.thitracnghiem.database.Entity.ThiSinh;

import java.util.List;


public class AdapterDeThi extends ArrayAdapter<DeThi> {
    private Activity Context;
    private int IdLayout;
    private List<DeThi> LstDeThi;
    public IClickUpdate UpdateDeThi;
    public interface IClickUpdate {
        void clickUpdate(DeThi deThi);

        void clickDelete(DeThi deThi);
    }
    public AdapterDeThi(Activity context, int idLayout, List<DeThi> lstDeThi, AdapterDeThi.IClickUpdate updateDeThi) {
        super(context, idLayout, lstDeThi);
        this.UpdateDeThi = updateDeThi;
        this.Context = context;
        this.IdLayout = idLayout;
        this.LstDeThi = lstDeThi;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = Context.getLayoutInflater();
        convertView = inflater.inflate(IdLayout, null);
        DeThi deThi = LstDeThi.get(position);
        TextView tvTenDe = convertView.findViewById(R.id.tvTenDe);
        TextView tvThoiGianLamBai = convertView.findViewById(R.id.tvThoiGianLamBai);
        TextView tvTongSoCau = convertView.findViewById(R.id.tvTongSoCau);
        Button btnCapNhatDeThi = convertView.findViewById(R.id.btnCapNhatDeThi);
        Button btnXoaDethi = convertView.findViewById(R.id.btnXoaDeThi);
        btnCapNhatDeThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDeThi.clickUpdate(deThi);
            }
        });
        btnXoaDethi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDeThi.clickDelete(deThi);
            }
        });

        tvTenDe.setText("Tên đề: "+deThi.getTenDe());
        tvTongSoCau.setText("Tổng số câu: "+deThi.getTongSoCau());
        tvThoiGianLamBai.setText("Thời gian: "+deThi.getTGLamBai()+" phút");
        return convertView;
    }
}
