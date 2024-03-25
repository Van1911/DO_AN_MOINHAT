package com.example.do_an;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DangNhapActivity extends AppCompatActivity {
    Button btnDK;
    Button btnDangNhap;
    TextInputEditText edtUserName,edtPassword;
    FirebaseAuth mAuth;
    Button btnForgotPass;
    Button admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        btnDK=findViewById(R.id.btnTaoTK);
        edtUserName = findViewById(R.id.edtEmailLogin);
        edtPassword = findViewById(R.id.edtPassLogin);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDK = findViewById(R.id.btnTaoTK);
        btnForgotPass=findViewById(R.id.buttonForgotPasswordDN);
        mAuth=FirebaseAuth.getInstance();
        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DangNhapActivity.this,DangKiActivity.class);
                startActivity(i);
                finish();
            }
        });
        btnForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DangNhapActivity.this,ForgotPassActivity.class);
                startActivity(intent);
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, pass ;
                email = String.valueOf(edtUserName.getText().toString());
                pass = String.valueOf(edtPassword.getText().toString());
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(DangNhapActivity.this, "Enter email", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(DangNhapActivity.this, "Enter password", Toast.LENGTH_LONG).show();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(email.equals("admin@gmail.com") && pass.equals("admin111")){
                                    Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_LONG).show();
                                    Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Toast.makeText(DangNhapActivity.this, "Đăng nhập thất bại",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
