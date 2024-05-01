package com.example.thitracnghiem.Adapter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thitracnghiem.LopHoc;
import com.example.thitracnghiem.R;
import com.example.thitracnghiem.database.Entity.Lop;

import java.util.List;

public class AdapterLopHoc extends ArrayAdapter<Lop> {
    private Activity Context;
    private int IdLayout;
    private List<Lop> LstLop;
    public IClickUpdate UpdateLop;
    public interface IClickUpdate {
        void clickUpdate(Lop lop);
        void clickDelete(Lop lop);
    }

    public AdapterLopHoc(Activity context, int idLayout, List<Lop> lstLop,IClickUpdate updateLop ) {
        super(context, idLayout, lstLop);
        this.UpdateLop = updateLop;
        this.Context = context;
        this.IdLayout = idLayout;
        this.LstLop = lstLop;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = Context.getLayoutInflater();
        convertView = inflater.inflate(IdLayout, null);
        Lop l = LstLop.get(position);
        TextView tvMaLop = convertView.findViewById(R.id.tvMaLop);
        TextView tvTenLop = convertView.findViewById(R.id.tvTenLop);
        Button btnCapNhat = convertView.findViewById(R.id.btnCapNhatLop);
        Button btnXoa = convertView.findViewById(R.id.btnXoa);
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateLop.clickUpdate(l);
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateLop.clickDelete(l);
            }
        });

        tvMaLop.setText(l.getMaLop());
        tvTenLop.setText(l.getTenLop());

        return convertView;
    }

}
