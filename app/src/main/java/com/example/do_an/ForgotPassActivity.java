package com.example.do_an;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassActivity extends AppCompatActivity {
EditText fg_pass;
Button btnForgotPass;
Button btnback_fg_pass;
    String str_fg_pass;
FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        fg_pass=findViewById(R.id.edtForgotPass);
        btnForgotPass=findViewById(R.id.btnForgotPass);
        btnback_fg_pass=findViewById(R.id.btnBack_fg_pass);
        mAuth=FirebaseAuth.getInstance();
        btnForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_fg_pass = fg_pass.getText().toString().trim();
                if(!TextUtils.isEmpty(str_fg_pass)){
                    ResetPassword();
                }else{
                    fg_pass.setError("Vui lòng điền email!");
                }
            }
        });
        btnback_fg_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void ResetPassword() {
        btnForgotPass.setVisibility(View.INVISIBLE);
        mAuth.sendPasswordResetEmail(str_fg_pass).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(ForgotPassActivity.this,"Vui lòng kiểm tra tin nhắn email để reset mật khẩu",Toast.LENGTH_LONG).show();
                Intent i = new Intent(ForgotPassActivity.this,DangNhapActivity.class);
                startActivity(i);
                finish();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ForgotPassActivity.this,"Erorr :- " + e.getMessage(),Toast.LENGTH_LONG).show();
                        btnForgotPass.setVisibility(View.VISIBLE);
                    }
                });
        }

}