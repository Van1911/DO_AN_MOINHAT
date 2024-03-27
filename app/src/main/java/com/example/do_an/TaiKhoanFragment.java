package com.example.do_an;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TaiKhoanFragment extends Fragment {


    Button btnSetting;
    TextView btnSignin;
    FirebaseAuth auth;
    FirebaseUser user;
    TextView txtUser;
    String s;
    public TaiKhoanFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_tai_khoan, container, false);

        btnSetting=view.findViewById(R.id.btnChangePass);
        btnSignin =view.findViewById(R.id.btnSignin);
        txtUser=view.findViewById(R.id.txtUser);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        s = presentlyUserName();
        s = txtUser.getText().toString();
        btnSignin.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(user == null){
                    Intent i = new Intent(getActivity(),DangNhapActivity.class);
                    startActivity(i);
                }
                else {

                    Intent i = new Intent(getActivity(),TaiKhoanActivity.class);
                    startActivity(i);

                }
            }
        }));
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),SettingActivity.class);

                startActivity(i);
            }
        });
        return view;
    }
    public String presentlyUserName(){
        auth=FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        String s="";
        if (user == null){
            txtUser.setText("Hãy bắt đầu chuyến đi của riêng mình");
            s = txtUser.getText().toString();
            return s;
        }else{
            txtUser.setText(user.getEmail());
            s = txtUser.getText().toString();
            return s;
        }
    }
}