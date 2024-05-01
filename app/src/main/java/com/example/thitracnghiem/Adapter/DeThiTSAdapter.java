package com.example.thitracnghiem.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thitracnghiem.R;
import com.example.thitracnghiem.database.DBThiTracNghiem;
import com.example.thitracnghiem.database.Entity.DeThi;
import com.example.thitracnghiem.database.Entity.Lop;
import com.example.thitracnghiem.database.Entity.MonHoc;
import com.example.thitracnghiem.database.Entity.ThiSinh;

import java.util.List;

public class DeThiTSAdapter extends ArrayAdapter<DeThi> {
    private Activity Context;
    private int IdLayout;
    private List<DeThi> lstDeThi;
    private IClick iclick;
    public interface IClick{
        void vaoThi(DeThi deThi);
    }


    public DeThiTSAdapter(Activity context, int idLayout, List<DeThi> lstDeThi,IClick clickVaoThi) {
        super(context, idLayout, lstDeThi);
        this.iclick =clickVaoThi;
        this.Context = context;
        this.IdLayout = idLayout;
        this.lstDeThi = lstDeThi;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = Context.getLayoutInflater();
        convertView = inflater.inflate(IdLayout, null);
        DeThi dt = lstDeThi.get(position);

        TextView tvMaDe,tvTenDe,tvMonHoc;
        Button btnVaoThi;

        tvMaDe = convertView.findViewById(R.id.tvMaDe);
        tvTenDe = convertView.findViewById(R.id.tvTenDe);
        tvMonHoc = convertView.findViewById(R.id.tvMonHoc);
        btnVaoThi = convertView.findViewById(R.id.btnVaoThi);

        tvMaDe.setText(dt.getMaDe());
        tvTenDe.setText(dt.getTenDe());
        MonHoc mh = DBThiTracNghiem.getInstance(Context).monHocDAO().selectByID(dt.getMaMon());
        tvMonHoc.setText(mh.getMaMH());

        btnVaoThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iclick.vaoThi(dt);
            }
        });

        return convertView;
    }
}
