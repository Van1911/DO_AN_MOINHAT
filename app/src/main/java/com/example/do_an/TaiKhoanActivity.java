package com.example.do_an;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class TaiKhoanActivity extends AppCompatActivity {
    TextView nameProfile;
    TextView passProfile;
    TextView btnLogout;
    TextView txtback1;
    Button change_pass;
    EditText edtPassLogin;
    FirebaseAuth auth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan);
        btnLogout=findViewById(R.id.btnLogout);
        nameProfile=findViewById(R.id.nameProfile);
        passProfile=findViewById(R.id.passProfile);
        txtback1=findViewById(R.id.txtback1);
        change_pass=findViewById(R.id.btnChangePass);
        txtback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(TaiKhoanActivity.this,DangNhapActivity.class);
                startActivity(i);
            }
        });
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        nameProfile.setText(user.getEmail());


    }
}