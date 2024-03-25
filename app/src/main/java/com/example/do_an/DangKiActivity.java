package com.example.do_an;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DangKiActivity extends AppCompatActivity {

    TextInputEditText edtUserName,edtPassDK;
    Button btnDangKi;
    FirebaseAuth mAuth;
    TextInputEditText cf_pass;
    MaterialToolbar backDK;
    TextView reDirect;
    Button forgot_pass;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(getApplicationContext(), DangNhapActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        edtUserName=findViewById(R.id.edtUserNameDK);
        edtPassDK=findViewById(R.id.edtPassDK);
        cf_pass=findViewById(R.id.edt_cf_pass);
        mAuth=FirebaseAuth.getInstance();
        backDK=findViewById(R.id.backDK);
        reDirect=findViewById(R.id.reDirect);
        btnDangKi=findViewById(R.id.btnDangKi);
        forgot_pass=findViewById(R.id.ForgotPassDK);
        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DangKiActivity.this,ForgotPassActivity.class);
                startActivity(intent);
            }
        });
        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,pass;
                email = String.valueOf(edtUserName.getText().toString());
                pass = String.valueOf(edtPassDK.getText().toString());
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(DangKiActivity.this, "Enter email", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(DangKiActivity.this, "Enter password", Toast.LENGTH_LONG).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(DangKiActivity.this, "Đăng kí tài khoản thành công", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(getApplicationContext(), DangNhapActivity.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    Toast.makeText(DangKiActivity.this, "Đăng kí tài khoản thất bại", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        reDirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DangKiActivity.this,DangNhapActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}