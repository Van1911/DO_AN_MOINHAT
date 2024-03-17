package com.example.do_an;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
    EditText edtidVe,edtNoiDi,edtNoiDen,edtgiaVe;
    ListView lstDSve;
    Button btnThem,btnXoa,btnSua,btnXem;
    ArrayList<String>mylist;
    ArrayAdapter<String>myAdapter;
    SQLiteDatabase database;
    Button btnbackhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        edtidVe=findViewById(R.id.edtthemIDve);
        edtNoiDen=findViewById(R.id.edtThemNoiDen);
        edtNoiDi=findViewById(R.id.edtThemNoiDI);
        lstDSve=findViewById(R.id.lstDSve);
        btnThem=findViewById(R.id.btnThem);
        btnXoa=findViewById(R.id.btnXoa);
        btnSua=findViewById(R.id.btnSua);
        btnXem=findViewById(R.id.btnXem);
        edtgiaVe=findViewById(R.id.edtThemGiaVe);
        mylist=new ArrayList<>();
        myAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,mylist);
        lstDSve.setAdapter(myAdapter);
        database=openOrCreateDatabase("waterbus.db",MODE_PRIVATE,null);
        try{
            String sql="CREATE TABLE tbVe(idVe TEXT primary key,noiDi TEXT, noiDen TEXT, giaTien TEXT)";
            database.execSQL(sql);
        }
        catch(Exception ex){
            Log.e("Error","Table đã tồn tại");

        }
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idVe= edtidVe.getText().toString();
                String noiDi=edtNoiDi.getText().toString();
                String noiDen=edtNoiDen.getText().toString();
                String giatien=edtgiaVe.getText().toString();
                ContentValues values=new ContentValues();
                values.put("idVe",idVe);
                values.put("noiDi",noiDi);
                values.put("noiDen",noiDen);
                values.put("giatien",giatien);
                String msg="";
                if(database.insert("tbVe",null,values)==-1){
                    msg="Fail to Insert Record";

                }
                else {
                    msg="Insert record Sucessfully";
                }
                Toast.makeText(AdminActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idVe= edtidVe.getText().toString();
                int n = database.delete("tbVe","idVe = ?",new String[]{idVe});
                String msg="";
                if(n==0){
                    msg="No record to Delete";

                }
                else {
                    msg=n+"record is deleted";
                }
                Toast.makeText(AdminActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
btnSua.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String giaTien=edtgiaVe.getText().toString();
        String idVe=edtidVe.getText().toString();
        ContentValues values=new ContentValues();
        values.put("giaTien",giaTien);
        int n = database.update("tbVe",values,"idVe = ?",new String[]{idVe});
        String msg="";
        if(n == 0){
            msg="No record to Update";
        }
        else {
            msg=n+"Record is updated";
        }
        Toast.makeText(AdminActivity.this,msg,Toast.LENGTH_SHORT).show();
    }
});
btnXem.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
//        lstDSve.clearFocus();
        Cursor c= database.query("tbVe",null,null,null,null,null,null);
        c.moveToNext();
        String data;
        while (c.isAfterLast()==false){
            data=c.getString(0)+" - " + c.getString(1) + " - " + c.getString(2) + " - " + c.getString(3);
            c.moveToNext();
            mylist.add(data);
        }
        c.close();
        myAdapter.notifyDataSetChanged();
    }

});
btnbackhome = findViewById(R.id.btnBackHome);
btnbackhome.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        finish();
    }
});

    }
}