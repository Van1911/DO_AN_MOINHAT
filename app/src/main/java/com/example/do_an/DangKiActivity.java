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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DangKiActivity extends AppCompatActivity {
    EditText edtUserNameDK;
    EditText edtpassDK;
    Button btnDangKi;
    FirebaseAuth mAuth;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        edtUserNameDK = findViewById(R.id.edtuserNameDK);
        edtpassDK = findViewById(R.id.edtPassDK);
        btnDangKi = findViewById(R.id.btnDangKi);
        mAuth=FirebaseAuth.getInstance();
        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, pass;
                email = String.valueOf(edtUserNameDK.getText().toString());
                pass = String.valueOf(edtpassDK.getText().toString());

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(DangKiActivity.this, "Enter email", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(DangKiActivity.this, "Enter password", Toast.LENGTH_LONG).show();
                    return;
                }


                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(DangKiActivity.this, "Đăng kí thành công",
                                            Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(DangKiActivity.this,DangNhapActivity.class);
                                    startActivity(i);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(DangKiActivity.this, "Đăng kí thất bại",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}