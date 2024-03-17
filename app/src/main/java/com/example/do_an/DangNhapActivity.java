package com.example.do_an;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DangNhapActivity extends AppCompatActivity {
    Button btnDangKi;
    Button btnDangNhap;
    EditText edtUserName,edtPassword;
    FirebaseAuth mAuth;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(getApplicationContext(),HomeFragment.class);
            startActivity(intent);
            finish();
        }
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dang_nhap);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPass);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        mAuth=FirebaseAuth.getInstance();

//        userManagement = new UserManagement(this);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, pass;
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
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_LONG).show();
                                    Intent intent=new Intent(getApplicationContext(),TaiKhoanActivity.class);
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
        btnDangKi = findViewById(R.id.btnDangKi);
        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DangNhapActivity.this, DangKiActivity.class);
                i.putExtra("", "");
                startActivity(i);
                finish();
            }
        });
    }

}
