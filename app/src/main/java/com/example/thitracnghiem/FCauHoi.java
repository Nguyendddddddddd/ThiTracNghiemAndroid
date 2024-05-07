package com.example.thitracnghiem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.thitracnghiem.Adapter.AdapterCauHoi;
import com.example.thitracnghiem.Adapter.AdapterMonHoc;
import com.example.thitracnghiem.Adapter.AdapterThiSinh;
import com.example.thitracnghiem.database.DBThiTracNghiem;
import com.example.thitracnghiem.database.Entity.CauHoi;
import com.example.thitracnghiem.database.Entity.Lop;
import com.example.thitracnghiem.database.Entity.MonHoc;
import com.example.thitracnghiem.database.Entity.ThiSinh;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FCauHoi#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FCauHoi extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int RQ_CODE = 10;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FCauHoi() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FCauHoi.
     */
    // TODO: Rename and change types and number of parameters
    public static FCauHoi newInstance(String param1, String param2) {
        FCauHoi fragment = new FCauHoi();
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

    private Spinner spnMon;
    private ListView lvCauHoi;
    private Button btnThemCauHoi;
    private AdapterCauHoi adapter;
    private List<CauHoi> lstCauHoi;
    private List<MonHoc> lstMon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f_cau_hoi, container, false);
        btnThemCauHoi = view.findViewById(R.id.btnThemCauHoi);
        spnMon = view.findViewById(R.id.spnMon);
        lvCauHoi = view.findViewById(R.id.lsvCauHoi);
        lstCauHoi = DBThiTracNghiem.getInstance(getContext()).cauHoiDAO().selectAll();
        lstMon = DBThiTracNghiem.getInstance(getContext()).monHocDAO().selectAll();
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, getMaMons());
        spnMon.setAdapter(spnAdapter);
        adapter = new AdapterCauHoi(getActivity(), R.layout.layout_item_listview_cauhoi, lstCauHoi, new AdapterCauHoi.IClickUpdate() {
            @Override
            public void clickUpdate(CauHoi cauHoi) {
                updateCauHoi(cauHoi);
            }

            @Override
            public void clickDelete(CauHoi cauHoi) {
                deleteCauHoi(cauHoi);
            }
        });
        spnMon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MonHoc mh =  lstMon.get(position);
                loadDataTheoSpinner(mh.getMaMH());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        lvCauHoi.setAdapter(adapter);
        btnThemCauHoi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ThemCauHoiActivity.class);
                Bundle bundle = new Bundle();
                String maMon = lstMon.get(spnMon.getSelectedItemPosition()).getMaMH();
                bundle.putSerializable("Ma mon" ,maMon);
                intent.putExtras(bundle);
                startActivityForResult(intent,100);
            }
        });

        lvCauHoi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                CauHoi cauHoi = lstCauHoi.get(position);
                Intent intent = new Intent(getActivity().getApplicationContext(), ThemDapAnActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Cau hoi",cauHoi);
                intent.putExtras(bundle);
                startActivityForResult(intent,RQ_CODE);
                return true;
            }
        });

        return view;
    }

    private List<String> getMaMons() {
        List<String> lstMaMon = new ArrayList<>();
        for (MonHoc l : lstMon) {
            lstMaMon.add(l.getTenMon());

        }
        return lstMaMon;
    }

    private void updateCauHoi(CauHoi cauHoi) {
        Intent intent = new Intent(getActivity().getApplicationContext(),SuaCauHoi.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Cau hoi" , cauHoi);
        intent.putExtras(bundle);
        startActivityForResult(intent,150);
    }

    private void deleteCauHoi(CauHoi cauHoi) {
        showDialog("Bạn muốn xóa câu hỏi?",cauHoi).create().show();
        //Toast.makeText(getContext(), "Xóa câu hỏi thành công", Toast.LENGTH_LONG).show();
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == Activity.RESULT_OK){
            if(data==null)
                return;
            String mm =  data.getStringExtra("Ma mon");
            loadDataTheoSpinner(mm);
        }

        if(requestCode == 150 && resultCode == Activity.RESULT_OK){
            if(data==null)
                return;
            String maMon =  data.getStringExtra("Ma mon");
            loadDataTheoSpinner(maMon);
        }
    }


    public void loadData(){
        lstCauHoi.clear();
        lstCauHoi.addAll(DBThiTracNghiem.getInstance(getContext()).cauHoiDAO().selectAll());
        adapter.notifyDataSetChanged();
    }

    public void loadDataTheoSpinner(String maMon){
        lstCauHoi.clear();
        lstCauHoi.addAll(DBThiTracNghiem.getInstance(getContext()).cauHoiDAO().selectAllByMaMH(maMon));
        adapter.notifyDataSetChanged();
    }

    public AlertDialog.Builder showDialog(String mss, CauHoi cauHoi){
        AlertDialog.Builder dl = new AlertDialog.Builder(getContext());
        dl.setTitle("Thông báo");
        dl.setMessage(mss);
        dl.setCancelable(true);
        dl.setPositiveButton("Yes",new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        DBThiTracNghiem.getInstance(getContext()).cauHoiDAO().delete(cauHoi);
                        String maMon = lstMon.get(spnMon.getSelectedItemPosition()).getMaMH();
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