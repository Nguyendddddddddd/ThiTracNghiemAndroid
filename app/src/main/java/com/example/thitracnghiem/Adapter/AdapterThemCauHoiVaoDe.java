package com.example.thitracnghiem.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thitracnghiem.R;
import com.example.thitracnghiem.database.Entity.CauHoi;

import java.util.List;

public class AdapterThemCauHoiVaoDe extends ArrayAdapter<CauHoi> {
    private Activity Context;
    private int IdLayout;
    private List<CauHoi> LstCauHoi;
    public IClickInsert InsertCauHoi;
    public interface IClickInsert {
        void clickInsert(CauHoi cauHoi);
    }

    public AdapterThemCauHoiVaoDe(Activity context, int idLayout, List<CauHoi> lstCauHoi, AdapterThemCauHoiVaoDe.IClickInsert insertCauHoi) {
        super(context, idLayout, lstCauHoi);
        this.InsertCauHoi = insertCauHoi;
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
        TextView tvMaCauHoi = convertView.findViewById(R.id.tvMaCauHoiCT);
        TextView tvNoiDungCT = convertView.findViewById(R.id.tvNoiDungCT);
        Button btnThemCauHoiVaoDe = convertView.findViewById(R.id.btnThemCauHoiVaoDeThi);
        btnThemCauHoiVaoDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertCauHoi.clickInsert(cauHoi);
            }
        });

        tvMaCauHoi.setText("Mã câu hỏi: "+cauHoi.getMaCauHoi());
        tvNoiDungCT.setText("Nội dung: "+cauHoi.getNoiDung());

        return convertView;
    }

}
