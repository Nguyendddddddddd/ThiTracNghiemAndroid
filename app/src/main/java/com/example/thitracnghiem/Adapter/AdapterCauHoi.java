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
import com.example.thitracnghiem.database.Entity.ThiSinh;


import java.util.List;

public class AdapterCauHoi extends ArrayAdapter<CauHoi> {
    private Activity Context;
    private int IdLayout;
    private List<CauHoi> LstCauHoi;
    public IClickUpdate UpdateCauHoi;

    public interface IClickUpdate {
        void clickUpdate(CauHoi cauHoi);

        void clickDelete(CauHoi cauHoi);
    }

    public AdapterCauHoi(Activity context, int idLayout, List<CauHoi> lstCauHoi, AdapterCauHoi.IClickUpdate updateCauHoi) {
        super(context, idLayout, lstCauHoi);
        this.UpdateCauHoi = updateCauHoi;
        this.Context = context;
        this.IdLayout = idLayout;
        this.LstCauHoi = lstCauHoi;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = Context.getLayoutInflater();
        convertView = inflater.inflate(IdLayout, null);
        CauHoi cauHoi = LstCauHoi.get(position);
        TextView tvNoiDungCH = convertView.findViewById(R.id.tvNoiDungCauHoi);
        Button btnCapNhatCH = convertView.findViewById(R.id.btnCapNhatCauHoi);
        Button btnXoaCH = convertView.findViewById(R.id.btnXoaCauHoi);
        btnCapNhatCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateCauHoi.clickUpdate(cauHoi);
            }
        });
        btnXoaCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateCauHoi.clickDelete(cauHoi);
            }
        });

        tvNoiDungCH.setText(cauHoi.getNoiDung());

        return convertView;
    }


}
