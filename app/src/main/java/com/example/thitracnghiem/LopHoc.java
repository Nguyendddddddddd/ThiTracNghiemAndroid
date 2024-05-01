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
import com.example.thitracnghiem.database.DBThiTracNghiem;
import com.example.thitracnghiem.database.Entity.Lop;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LopHoc#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LopHoc extends Fragment {



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int  RQ_CODE = 10;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public LopHoc() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LopHoc.
     */
    // TODO: Rename and change types and number of parameters

    Button btnThemLop;
    EditText edtMaLop, edtTenLop;
    ListView lvLopHoc;
    List<Lop> lstLop;
    AdapterLopHoc adtLopHoc;

    public static LopHoc newInstance(String param1, String param2) {
        LopHoc fragment = new LopHoc();
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
        View view = inflater.inflate(R.layout.fragment_lop, container, false);
        btnThemLop = view.findViewById(R.id.btnThemLop);
        edtTenLop = view.findViewById(R.id.edtTenLop);
        edtMaLop = view.findViewById(R.id.edtMaLop);
        lvLopHoc = view.findViewById(R.id.lvLopHoc);
        lstLop = DBThiTracNghiem.getInstance(getContext()).lopDAO().selectAll();

        adtLopHoc = new AdapterLopHoc(getActivity(), R.layout.layout_item_listview_lophoc, lstLop, new AdapterLopHoc.IClickUpdate() {
            @Override
            public void clickUpdate(Lop lop) {
                UpdateLopHoc(lop);
            }

            @Override
            public void clickDelete(Lop lop) {
                DeleteLopHoc(lop);
            }
        });
        lvLopHoc.setAdapter(adtLopHoc);
        btnThemLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemLopHoc();
            }
        });
        lvLopHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lstLop.remove(position);
                adtLopHoc.notifyDataSetChanged();
            }
        });
        return view;
    }
    private void DeleteLopHoc(Lop lop) {
        showDialog("Bạn muốn xóa lớp?",lop).create().show();
    }

    public void ThemLopHoc() {
        String maLop = edtMaLop.getText().toString();
        String tenLop = edtTenLop.getText().toString();
        if (maLop.trim().length() == 0 || tenLop.trim().length() == 0) {
            Toast.makeText(getContext(), "Nhập thiếu thông tin!", Toast.LENGTH_LONG).show();
            return;
        }
        DBThiTracNghiem db = DBThiTracNghiem.getInstance(getContext());
        if (db.lopDAO().selectAllById(maLop) != null) {
            Toast.makeText(getContext(), "Mã lớp đã tồn tại!", Toast.LENGTH_LONG).show();
            return;
        }
        Lop l = new Lop(maLop, tenLop);
        db.lopDAO().insert(l);

       loadData();
    }
    public void UpdateLopHoc(Lop lop){
        Intent intent = new Intent(getActivity().getApplicationContext(),SuaLopHoc.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Lop hoc" , lop);
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

    public void loadData(){
        lstLop.clear();
        lstLop.addAll(DBThiTracNghiem.getInstance(getContext()).lopDAO().selectAll());
        adtLopHoc.notifyDataSetChanged();
    }

    public AlertDialog.Builder showDialog(String mss,Lop lop){
        AlertDialog.Builder dl = new AlertDialog.Builder(getContext());
        dl.setTitle("Thông báo");
        dl.setMessage(mss);
        dl.setCancelable(true);
        dl.setPositiveButton("Yes",new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        DBThiTracNghiem.getInstance(getContext()).lopDAO().delete(lop);
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
