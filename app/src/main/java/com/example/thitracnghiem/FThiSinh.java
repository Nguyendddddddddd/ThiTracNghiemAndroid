package com.example.thitracnghiem;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.thitracnghiem.Adapter.AdapterThiSinh;
import com.example.thitracnghiem.database.DAO.ThiSinhDAO;
import com.example.thitracnghiem.database.DBThiTracNghiem;
import com.example.thitracnghiem.database.Entity.Lop;
import com.example.thitracnghiem.database.Entity.TaiKhoan;
import com.example.thitracnghiem.database.Entity.ThiSinh;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FThiSinh#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FThiSinh extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int RQ_CODE = 10;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Spinner spinner;
    public FThiSinh() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FThiSinh.
     */
    // TODO: Rename and change types and number of parameters
    public static FThiSinh newInstance(String param1, String param2) {
        FThiSinh fragment = new FThiSinh();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private Spinner spnLop;
    private EditText edtMaThiSinh;
    private EditText edtTenThiSinh;
    private ListView lvThiSinh;
    private Button btnThem;
    private AdapterThiSinh adapter;
    private List<ThiSinh> lstThiSinh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thi_sinh, container, false);
        btnThem = view.findViewById(R.id.btnThemThiSinh);
        spnLop = view.findViewById(R.id.spnLop);
        edtMaThiSinh = view.findViewById(R.id.edtMaThiSinh);
        edtTenThiSinh = view.findViewById(R.id.edtTenThiSinh);
        lvThiSinh = view.findViewById(R.id.lvThiSinh);
        lstThiSinh = DBThiTracNghiem.getInstance(getContext()).thiSinhDAO().selectAll();
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item,getMaLops());
        spnLop.setAdapter(spnAdapter);
        adapter = new AdapterThiSinh(getActivity(), R.layout.layout_item_listview_thisinh, lstThiSinh, new AdapterThiSinh.IClickUpdate() {
            @Override
            public void clickUpdate(ThiSinh thiSinh) {
                updateThiSinh(thiSinh);
            }

            @Override
            public void clickDelete(ThiSinh thiSinh) {
                deleteThiSinh(thiSinh);

            }
        });
        lvThiSinh.setAdapter(adapter);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themThiSinh();
            }
        });
        return view;
    }

    private void deleteThiSinh(ThiSinh thiSinh) {
        showDialog("Bạn muốn xóa thí thí sinh?",thiSinh).create().show();
        Toast.makeText(getContext(), "Xóa thí sinh thành công", Toast.LENGTH_LONG).show();
    }

    private void updateThiSinh(ThiSinh thiSinh) {
        Intent intent = new Intent(getActivity(),SuaThiSinh.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Thi sinh",thiSinh);
        intent.putExtras(bundle);
        startActivityForResult(intent,RQ_CODE);
    }

    private void themThiSinh() {
        String maTS = edtMaThiSinh.getText().toString();
        String tenThiSinh = edtTenThiSinh.getText().toString();
        String maLop = spnLop.getSelectedItem().toString();
        if (maTS.trim().length() == 0 || tenThiSinh.trim().length() == 0) {
            Toast.makeText(getContext(), "Nhập thiếu thông tin!", Toast.LENGTH_LONG).show();
            return;
        }
        DBThiTracNghiem db = DBThiTracNghiem.getInstance(getContext());
        if (db.thiSinhDAO().selectAllById(maTS) != null) {
            Toast.makeText(getContext(), "Mã thí sinh đã tồn tại!", Toast.LENGTH_LONG).show();
            return;
        }
        TaiKhoan tk = new TaiKhoan(maTS,"1234",1);
        ThiSinh ts = new ThiSinh(maTS,tenThiSinh,maLop,maTS);
        db.taiKhoanDAO().insert(tk);
        db.thiSinhDAO().insert(ts);
        Toast.makeText(getContext(), "Thêm thí sinh thành công!", Toast.LENGTH_LONG).show();
        loadData();

    }
    private void loadData(){
        lstThiSinh.clear();
        lstThiSinh.addAll(DBThiTracNghiem.getInstance(getContext()).thiSinhDAO().selectAll());
        adapter.notifyDataSetChanged();
    }
    private List<String> getMaLops(){
        List<String> lstMaLop = new ArrayList<>();
        List<Lop> lstLop = DBThiTracNghiem.getInstance(getContext()).lopDAO().selectAll();

        for (Lop l: lstLop) {
            lstMaLop.add(l.getMaLop());

        }
        return lstMaLop;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RQ_CODE && resultCode == Activity.RESULT_OK){
            loadData();
        }
    }
    public AlertDialog.Builder showDialog(String mss, ThiSinh thiSinh){
        AlertDialog.Builder dl = new AlertDialog.Builder(getContext());
        dl.setTitle("Thông báo");
        dl.setMessage(mss);
        dl.setCancelable(true);
        dl.setPositiveButton("Yes",new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        DBThiTracNghiem.getInstance(getContext()).thiSinhDAO().delete(thiSinh);
                        loadData();
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