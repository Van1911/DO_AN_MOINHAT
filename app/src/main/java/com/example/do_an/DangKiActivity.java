package com.example.do_an;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.do_an.DBHelper.DangKiHelper;

public class DangKiActivity extends AppCompatActivity {
    EditText edtUserNameDK;
    EditText edtpassDK;
    EditText edtpassAgainDK;
    Button btnDangKi;
    DangKiHelper dangKiHelper;
    Button btnbackDK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        btnbackDK = findViewById(R.id.btnbackDK);
        edtUserNameDK = findViewById(R.id.edtuserNameDK);
        edtpassDK = findViewById(R.id.edtPassDK);
        edtpassAgainDK = findViewById(R.id.edtPassAgainDK);
        btnDangKi = findViewById(R.id.btnDangKi);
        dangKiHelper=new DangKiHelper(this);
        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username, password, rePassword;
                username = edtUserNameDK.getText().toString();
                password = edtpassDK.getText().toString();
                rePassword = edtpassAgainDK.getText().toString();
                if (username.equals("") || password.equals("") || rePassword.equals("")) {
                    Toast.makeText(DangKiActivity.this, "Vui lòng nhập đầy đủ", Toast.LENGTH_LONG).show();
                } else {
                    if (password.equals(rePassword)) {
                        if (dangKiHelper.checkUserName(username)) {
                            Toast.makeText(DangKiActivity.this, "Tên đăng nhập bị trùng", Toast.LENGTH_LONG).show();
                            return;
                        }
                        boolean dangkithanhcong = dangKiHelper.insertData(username, password);
                        if (dangkithanhcong) {

                            Toast.makeText(DangKiActivity.this, "Đăng kí thành công", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(DangKiActivity.this,DangNhapActivity.class);
                            startActivity(i);
                        } else
                            Toast.makeText(DangKiActivity.this, "Đăng kí thất bại", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(DangKiActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}