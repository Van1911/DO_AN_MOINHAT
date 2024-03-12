package com.example.do_an;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.do_an.DBHelper.DangKiHelper;

public class DangNhapActivity extends AppCompatActivity {
    Button btnDangKi;
    Button btnbackDN;
    Button btnDangNhap;
    EditText edtUserName;
    EditText edtPassword;
DangKiHelper dangkiHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dang_nhap);
dangkiHelper =new DangKiHelper(this);
        btnbackDN=findViewById(R.id.btnbackdn);
        edtUserName=findViewById(R.id.edtUserName);
        edtPassword=findViewById(R.id.edtPass);
        btnDangNhap=findViewById(R.id.btnDangNhap);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isLoggedID=dangkiHelper.checkUser(edtUserName.getText().toString(),edtPassword.getText().toString());
                if(isLoggedID){
                    Intent i = new Intent(DangNhapActivity.this,MainActivity.class);
                    startActivity(i);
                }
                else Toast.makeText(DangNhapActivity.this,"Đăng nhập thất bại",Toast.LENGTH_LONG).show();
            }
        });
btnDangKi=findViewById(R.id.btnDangKi);
btnDangKi.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(DangNhapActivity.this, DangKiActivity.class);
        i.putExtra("", "");
        startActivity(i);
    }
});
        btnbackDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



}