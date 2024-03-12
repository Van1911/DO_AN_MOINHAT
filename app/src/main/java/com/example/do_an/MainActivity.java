package com.example.do_an;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
FrameLayout frameLayout;
Button btnDangKi1;
ListView lstView;
BottomNavigationView bottomNavigationView;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.activity_main));
setContentView(R.layout.activity_main);
setFragment(new HomeFragment());
bottomNavigationView = findViewById(R.id.bottomNavigationView);

bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.mnHome) {
            setFragment(new HomeFragment());

        }
        else if (item.getItemId()==R.id.mnTicket){
            setFragment(new VeCuaToiFragment());

        }
        else if (item.getItemId()==R.id.mnAccount){
            setFragment(new TaiKhoanFragment());

        }
        return true;
    }


}
);
}




    void setFragment(Fragment fragment){

        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout,fragment);
        transaction.addToBackStack(null);
        transaction.commit();

}


}