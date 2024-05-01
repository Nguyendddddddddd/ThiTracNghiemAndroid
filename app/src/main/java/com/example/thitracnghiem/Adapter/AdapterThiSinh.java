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
import com.example.thitracnghiem.database.Entity.Lop;
import com.example.thitracnghiem.database.Entity.ThiSinh;

import java.util.List;

public class AdapterThiSinh extends ArrayAdapter<ThiSinh> {
    private Activity Context;
    private int IdLayout;
    private List<ThiSinh> LstThiSinh;
    public IClickUpdate UpdateThiSinh;

    public interface IClickUpdate {
        void clickUpdate(ThiSinh thiSinh);

        void clickDelete(ThiSinh thiSinh);
    }

    public AdapterThiSinh(Activity context, int idLayout, List<ThiSinh> lstThiSinh, IClickUpdate updateThiSinh) {
        super(context, idLayout, lstThiSinh);
        this.UpdateThiSinh = updateThiSinh;
        this.Context = context;
        this.IdLayout = idLayout;
        this.LstThiSinh = lstThiSinh;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = Context.getLayoutInflater();
        convertView = inflater.inflate(IdLayout, null);
        ThiSinh thiSinh = LstThiSinh.get(position);
        TextView tvMaThiSinh = convertView.findViewById(R.id.tvMaThiSinh);
        TextView tvTenThiSinh = convertView.findViewById(R.id.tvTenThiSinh);
        TextView tvMaLop = convertView.findViewById(R.id.tvMaLop);
        Button btnCapNhat = convertView.findViewById(R.id.btnCapNhat);
        Button btnXoa = convertView.findViewById(R.id.btnXoa);
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateThiSinh.clickUpdate(thiSinh);
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateThiSinh.clickDelete(thiSinh);
            }
        });

        tvMaLop.setText(thiSinh.getMaLop());
        tvTenThiSinh.setText(thiSinh.getHoTen());
        tvMaThiSinh.setText(thiSinh.getMaTS());

        return convertView;
    }

}
