package com.example.do_an;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;


import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    Button btnTimKiem;
    Button btnNoiDi;
    Button btnNoiDen;
    Button btnNgayKH;
    Button btnAdmin;
    private DatePickerDialog.OnDateSetListener dateSetListener;
Context context;
    @SuppressLint("NonConstantResourceId")

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        btnTimKiem = view.findViewById(R.id.btnTimKiem);
        btnNoiDi = view.findViewById(R.id.btnNoiDi);
        btnNoiDen = view.findViewById(R.id.btnNoiDen);
        btnNgayKH=view.findViewById(R.id.btnNgayKH);
        btnAdmin=view.findViewById(R.id.btnAdmin);
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),AdminActivity.class);
                startActivity(i);
            }
        });
        btnNgayKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Calendar kal=Calendar.getInstance();
int year=kal.get(Calendar.YEAR);
int month=kal.get(Calendar.MONTH);
int day=kal.get(Calendar.DAY_OF_MONTH);
DatePickerDialog dialog =new DatePickerDialog(getContext(),android.R.style.Theme_DeviceDefault_Dialog,dateSetListener,year,month,day);
                dialog.show();
            }
        });
dateSetListener=new DatePickerDialog.OnDateSetListener() {
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month=month+1;
        Log.d(TAG, "onDateSet: mm/dd/yyyy" + dayOfMonth + "/" + month + "/" + year);
        String date="Ngày khởi hành: "+ dayOfMonth + "/" + month + "/" + year;
        btnNgayKH.setText(date);
    }
};
        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ActivityChuyenDi.class);
                i.putExtra("", "");
                startActivity(i);
            }
        });


        btnNoiDi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PlaceActivity.class);
                i.putExtra("", "");
                startActivity(i);
            }
        });
        btnNoiDen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PlaceActivity.class);
                i.putExtra("", "");
                startActivity(i);
            }
        });
        return  view;
    }

}




