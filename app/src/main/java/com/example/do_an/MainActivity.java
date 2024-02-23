package com.example.do_an;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
Button btnTimKiem;
ImageButton btnTaiKhoan;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

setContentView(R.layout.activity_main);
btnTimKiem=findViewById(R.id.btnTimKiem);
btnTaiKhoan=findViewById(R.id.btnTaiKhoan);
btnTaiKhoan.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(MainActivity.this,TaiKhoan_Activity.class);
        i.putExtra("","");
        startActivity(i);
    }

});
btnTimKiem.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(MainActivity.this,ActivityChuyenDi.class);
        i.putExtra("","");
        startActivity(i);
    }
});


    }




}