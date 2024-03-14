package com.example.do_an;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
FrameLayout frameLayout;
Button btnDangKi1;
ListView lstView;
BottomNavigationView bottomNavigationView;
public String DATABASE_NAME="waterbus2";
public String DB_SUFFIX_PATH="database";
public static SQLiteDatabase database=null;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.activity_main));
        proccessCopy();
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

    private void proccessCopy() {
        try{
            File file=getDatabasePath((DATABASE_NAME));
            if(!file.exists())
                copyDatabaseFromAsset();
            Toast.makeText(this,"Copy Database Successful",Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex){
            Toast.makeText(this,"Copy Database Fail",Toast.LENGTH_SHORT).show();
        }
    }

    private void copyDatabaseFromAsset() {
        try{
            InputStream inputFile=getAssets().open(DATABASE_NAME);
            String outputFileName=getDatabasePath();
            File file = new File(getApplicationInfo().dataDir+DB_SUFFIX_PATH);
            if(!file.exists())
                file.mkdir();
            OutputStream outFile=new FileOutputStream(outputFileName);
            byte[]buffer=new byte[1024];
            int length;
            while ((length=inputFile.read(buffer))>0 )outFile.write(buffer,0,length);
            outFile.flush();
            outFile.close();
            inputFile.close();
        }
        catch (Exception ex){
            Log.e("Error",ex.toString());
        }
    }

    private String getDatabasePath() {
        return getApplicationInfo().dataDir+DB_SUFFIX_PATH+DATABASE_NAME;
    }


    void setFragment(Fragment fragment){

        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout,fragment);
        transaction.addToBackStack(null);
        transaction.commit();

}


}