package com.example.do_an;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
Button btnTimKiem;
ImageButton btnTaiKhoan;
Button btnNoiDi;
Button btnNoiDen;
BottomNavigationView nav;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

setContentView(R.layout.activity_main);
btnTimKiem=findViewById(R.id.btnTimKiem);
btnTaiKhoan=findViewById(R.id.btnTaiKhoan);
        btnNoiDi=findViewById(R.id.btnNoiDi);
        btnNoiDen=findViewById(R.id.btnNoiDen);

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


btnNoiDi.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
Intent i=new Intent(MainActivity.this,PlaceActivity.class);
i.putExtra("","");
startActivity(i);
    }
});
        btnNoiDen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,PlaceActivity.class);
                i.putExtra("","");
                startActivity(i);
            }
        });

    }




}