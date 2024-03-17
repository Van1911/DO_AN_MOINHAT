package com.example.do_an;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TaiKhoanActivity extends AppCompatActivity {
    TextView nameProfile;
    TextView passProfile;
    TextView btnLogout;
    FirebaseAuth auth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan);
        btnLogout=findViewById(R.id.btnLogout);
        nameProfile=findViewById(R.id.nameProfile);
        passProfile=findViewById(R.id.passProfile);
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
        passProfile.setText("**********");

    }
}