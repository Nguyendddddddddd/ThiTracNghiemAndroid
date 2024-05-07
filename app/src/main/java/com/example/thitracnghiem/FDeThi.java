package com.example.thitracnghiem;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.thitracnghiem.Adapter.AdapterCauHoi;
import com.example.thitracnghiem.Adapter.AdapterDeThi;
import com.example.thitracnghiem.database.DBThiTracNghiem;
import com.example.thitracnghiem.database.Entity.CauHoi;
import com.example.thitracnghiem.database.Entity.DeThi;
import com.example.thitracnghiem.database.Entity.Lop;
import com.example.thitracnghiem.database.Entity.MonHoc;
import com.example.thitracnghiem.database.Entity.ThiSinh;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FDeThi#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FDeThi extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int RQ_CODE = 10;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FDeThi() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FDeThi.
     */
    // TODO: Rename and change types and number of parameters
    public static FDeThi newInstance(String param1, String param2) {
        FDeThi fragment = new FDeThi();
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

    private ListView lsvDeThi;
    private Button btnThemDeThi;
    private Spinner spnMonDeThi;
    private AdapterDeThi adapterDeThi;
    private List<DeThi> lstDeThi;
    private List<MonHoc> lstMon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f_de_thi, container, false);

        spnMonDeThi = view.findViewById(R.id.spnMonDeThi);
        btnThemDeThi = view.findViewById(R.id.btnThemDeThi);
        lsvDeThi = view.findViewById(R.id.lsvDeThi);
        lstDeThi = DBThiTracNghiem.getInstance(getContext()).deThiDAO().selectAll();
        lstMon = DBThiTracNghiem.getInstance(getContext()).monHocDAO().selectAll();
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item,getTenMons());
        spnMonDeThi.setAdapter(spnAdapter);
        adapterDeThi = new AdapterDeThi(getActivity(), R.layout.layout_item_lisview_dethi, lstDeThi, new AdapterDeThi.IClickUpdate() {
            @Override
            public void clickUpdate(DeThi deThi) {
                updateDeThi(deThi);
            }
            @Override
            public void clickDelete(DeThi deThi) {
                deleteDeThi(deThi);
            }
        });
        spnMonDeThi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MonHoc mh =  lstMon.get(position);
                loadDataTheoSpinner(mh.getMaMH());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lsvDeThi.setAdapter(adapterDeThi);
        btnThemDeThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ThemDeThiActivity.class);
                Bundle bundle = new Bundle();
                String maMon = lstMon.get(spnMonDeThi.getSelectedItemPosition()).getMaMH();
                bundle.putSerializable("Ma mon" ,maMon);
                intent.putExtras(bundle);
                startActivityForResult(intent,RQ_CODE);
            }
        });

        lsvDeThi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DeThi deThi = lstDeThi.get(position);
                Intent intent = new Intent(getActivity().getApplicationContext(), ChiTietDeThiActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("De thi",deThi);
                intent.putExtras(bundle);
                startActivityForResult(intent,RQ_CODE);
                return true;
            }
        });

        return view;
    }

    private List<String> getTenMons() {
        List<String> lstMaMon = new ArrayList<>();
        for (MonHoc l : lstMon) {
            lstMaMon.add(l.getTenMon());

        }
        return lstMaMon;
    }

    public void updateDeThi(DeThi deThi){
        Intent intent = new Intent(getActivity().getApplicationContext(),SuaDeThi.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("De thi" , deThi);
        intent.putExtras(bundle);
        startActivityForResult(intent,65);
    }
    public void deleteDeThi(DeThi deThi){
        showDialog("Bạn muốn xóa đề thi?",deThi).create().show();
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RQ_CODE && resultCode == Activity.RESULT_OK){
            if(data==null)
                return;
            String mm =  data.getStringExtra("Ma mon");
            loadDataTheoSpinner(mm);
        }

        if(requestCode == 65 && resultCode == Activity.RESULT_OK){
            if(data==null)
                return;
            String mm =  lstMon.get(spnMonDeThi.getSelectedItemPosition()).getMaMH();
            loadDataTheoSpinner(mm);
        }

    }
    public void loadDataTheoSpinner(String maMon){
        lstDeThi.clear();
        lstDeThi.addAll(DBThiTracNghiem.getInstance(getContext()).deThiDAO().selectAllByMaMH(maMon));
        adapterDeThi.notifyDataSetChanged();
    }
    public void loadData(){
        lstDeThi.clear();
        lstDeThi.addAll(DBThiTracNghiem.getInstance(getContext()).deThiDAO().selectAll());
        adapterDeThi.notifyDataSetChanged();
    }

    public AlertDialog.Builder showDialog(String mss, DeThi deThi){
        AlertDialog.Builder dl = new AlertDialog.Builder(getContext());
        dl.setTitle("Thông báo");
        dl.setMessage(mss);
        dl.setCancelable(true);
        dl.setPositiveButton("Yes",new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        DBThiTracNghiem.getInstance(getContext()).deThiDAO().delete(deThi);
                        String maMon = lstMon.get(spnMonDeThi.getSelectedItemPosition()).getMaMH();
                        loadDataTheoSpinner(maMon);
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