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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thitracnghiem.Adapter.AdapterLopHoc;
import com.example.thitracnghiem.Adapter.AdapterMonHoc;
import com.example.thitracnghiem.database.DBThiTracNghiem;
import com.example.thitracnghiem.database.Entity.DeThi;
import com.example.thitracnghiem.database.Entity.Lop;
import com.example.thitracnghiem.database.Entity.MonHoc;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FMonHoc#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FMonHoc extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final int  RQ_CODE = 10;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FMonHoc() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FMonHoc.
     */

    Button btnThemMon;
    EditText edtMaMon, edtTenMon;
    ListView lsvMonHoc;
    List<MonHoc> lstMon;
    AdapterMonHoc adtMonHoc;
    // TODO: Rename and change types and number of parameters
    public static FMonHoc newInstance(String param1, String param2) {
        FMonHoc fragment = new FMonHoc();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f_mon_hoc, container, false);
        btnThemMon = view.findViewById(R.id.btnThemMon);
        edtTenMon = view.findViewById(R.id.edtTenMon);
        edtMaMon = view.findViewById(R.id.edtMaMon);
        lsvMonHoc = view.findViewById(R.id.lvMonHoc);
        lstMon = DBThiTracNghiem.getInstance(getContext()).monHocDAO().selectAll();

        adtMonHoc = new AdapterMonHoc(getActivity(), R.layout.layout_item_listview_monhoc, lstMon, new AdapterMonHoc.IClickUpdate() {
            @Override
            public void clickUpdate(MonHoc mon) {
                UpdateMonHoc(mon);
            }

            @Override
            public void clickDelete(MonHoc mon) {
                DeleteMonHoc(mon);
            }
        });

        lsvMonHoc.setAdapter(adtMonHoc);
        btnThemMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemMonHoc();
            }
        });

        lsvMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lstMon.remove(position);
                adtMonHoc.notifyDataSetChanged();
            }
        });

        return view;
    }

    private void DeleteMonHoc(MonHoc mon) {
        showDialog("Bạn muốn xóa môn?",mon).create().show();
    }

    public void loadData(){
        lstMon.clear();
        lstMon.addAll(DBThiTracNghiem.getInstance(getContext()).monHocDAO().selectAll());
        adtMonHoc.notifyDataSetChanged();
    }

    public void ThemMonHoc() {
        String maMon = edtMaMon.getText().toString();
        String tenMon = edtTenMon.getText().toString();
        if (maMon.trim().length() == 0 || tenMon.trim().length() == 0) {
            Toast.makeText(getContext(), "Nhập thiếu thông tin!", Toast.LENGTH_LONG).show();
            return;
        }
        DBThiTracNghiem db = DBThiTracNghiem.getInstance(getContext());
        if (db.monHocDAO().selectAllById(maMon) != null) {
            Toast.makeText(getContext(), "Mã môn đã tồn tại!", Toast.LENGTH_LONG).show();
            return;
        }
        MonHoc m = new MonHoc(maMon, tenMon);
        db.monHocDAO().insert(m);
        loadData();
        edtMaMon.setText("");
        edtTenMon.setText("");
        Toast.makeText(getContext(), "Thêm môn học thành công", Toast.LENGTH_LONG).show();
    }

    public void UpdateMonHoc(MonHoc mon){
        Intent intent = new Intent(getActivity().getApplicationContext(),SuaMonHoc.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Mon hoc" , mon);
        intent.putExtras(bundle);
        startActivityForResult(intent,RQ_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RQ_CODE && resultCode == Activity.RESULT_OK){
            loadData();
        }
    }

    public AlertDialog.Builder showDialog(String mss, MonHoc mon){
        AlertDialog.Builder dl = new AlertDialog.Builder(getContext());
        dl.setTitle("Thông báo");
        dl.setMessage(mss);
        dl.setCancelable(true);
        dl.setPositiveButton("Yes",new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        DBThiTracNghiem.getInstance(getContext()).monHocDAO().delete(mon);
                        loadData();
                        Toast.makeText(getContext(), "Xóa môn học thành công", Toast.LENGTH_LONG).show();
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