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
import android.widget.Adapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;


public class HomeFragment extends Fragment {
    Button btnTimKiem;
    EditText btnNoiDi;
    EditText btnNoiDen;
    Button btnNgayKH;
    Button btnAdmin;
    FirebaseUser user;
    FirebaseAuth mAuth;


    private DatePickerDialog.OnDateSetListener dateSetListener;
Context context;
    @SuppressLint("NonConstantResourceId")

    public HomeFragment() {
        // Required empty public constructor
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
        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            if(user.getEmail().equals("admin@gmail.com")){
                btnAdmin.setVisibility(View.VISIBLE);
            }
            else btnAdmin.setVisibility(View.GONE);
        } else {
            btnAdmin.setVisibility(View.GONE);
        }
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
//        btnNoiDi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), PlaceActivity.class);
//                i.putExtra("", "");
//                startActivity(i);
//            }
//        });
//        btnNoiDen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), PlaceActivity.class);
//                i.putExtra("", "");
//                startActivity(i);
//            }
//        });
        return  view;
    }

}




