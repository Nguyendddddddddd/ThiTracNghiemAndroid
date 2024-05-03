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
import com.example.thitracnghiem.database.Entity.Lop;
import com.example.thitracnghiem.database.Entity.MonHoc;

import java.util.List;

public class AdapterMonHoc extends ArrayAdapter<MonHoc> {
    private Activity Context;
    private int IdLayout;
    private List<MonHoc> LstMon;

    public IClickUpdate UpdateMon;

    public interface IClickUpdate {
        void clickUpdate(MonHoc mon);
        void clickDelete(MonHoc mon);
    }
    public AdapterMonHoc(Activity context, int idLayout, List<MonHoc> lstMon, IClickUpdate updateMon ) {
        super(context, idLayout, lstMon);
        this.UpdateMon = updateMon;
        this.Context = context;
        this.IdLayout = idLayout;
        this.LstMon = lstMon;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = Context.getLayoutInflater();
        convertView = inflater.inflate(IdLayout, null);
        MonHoc m = LstMon.get(position);
        TextView tvMaMon = convertView.findViewById(R.id.tvMaMon);
        TextView tvTenMon = convertView.findViewById(R.id.tvTenMon);
        Button btnCapNhatMon = convertView.findViewById(R.id.btnCapNhatMon);
        Button btnXoaMon = convertView.findViewById(R.id.btnXoaMon);
        btnCapNhatMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateMon.clickUpdate(m);
            }
        });
        btnXoaMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateMon.clickDelete(m);
            }
        });

        tvMaMon.setText(m.getMaMH());
        tvTenMon.setText(m.getTenMon());

        return convertView;
    }
}
