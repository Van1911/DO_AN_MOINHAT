package com.example.do_an;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {
    private FirebaseDatabase db;
    private DatabaseReference ref;

    EditText edtidVe,edtNoiDi,edtNoiDen,edtgiaVe,edtngaydi;
    ListView lstDSve;
    Button btnThem,btnXoa,btnSua,btnXem;
    ArrayList<String> mylist;
    ArrayAdapter<String> myAdapter;
    Button btnbackhome;

    RecyclerView rcvTicket;
    TicketAdapter mTicketAdapter;
    List<Ticket> mListTickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        edtidVe=findViewById(R.id.edtthemIDve);
        edtNoiDen=findViewById(R.id.edtThemNoiDen);
        edtNoiDi=findViewById(R.id.edtThemNoiDI);
        /*edtngaydi = findViewById(R.id.edtngaydi);*/
        btnThem=findViewById(R.id.btnThem);
//        btnXoa=findViewById(R.id.btnXoa);
        /*btnSua=findViewById(R.id.btnSua);*/
        edtgiaVe=findViewById(R.id.edtThemGiaVe);

        db = FirebaseDatabase.getInstance();
        ref = db.getReference();

        rcvTicket = findViewById(R.id.lstDSve);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvTicket.setLayoutManager(linearLayoutManager);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rcvTicket.addItemDecoration(dividerItemDecoration);


        mListTickets  = new ArrayList<>();
        mTicketAdapter = new TicketAdapter(mListTickets, new TicketAdapter.IClickListener() {
            @Override
            public void onClickUpdateItem(Ticket ticket) {
                openDialogUpdateItem(ticket);
            }

            @Override
            public void onClickDeleteItem(Ticket ticket) {
                onClickDeleteData(ticket);
            }
        });
        rcvTicket.setAdapter(mTicketAdapter);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(edtidVe.getText().toString().trim());
                String noiDi = edtNoiDi.getText().toString().trim();
                String noiDen = edtNoiDen.getText().toString().trim();
                /*String ngayDi = edtngaydi.getText().toString().trim();*/
                String giaTien = edtgiaVe.getText().toString().trim();
                Ticket ticket = new Ticket(id,noiDi,noiDen,giaTien);

                onClickAddTickets(ticket);
            }
        });



        btnbackhome = findViewById(R.id.btnBackHome);
        btnbackhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getListTicketsFromRealTimeDB();

    }
    private void onClickAddTickets(Ticket ticket){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference myRef = db.getReference("Tickets");

        String pathObject = String.valueOf(ticket.getId());
        myRef.child(pathObject).setValue(ticket, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(AdminActivity.this,"Add ticket successfull", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void getListTicketsFromRealTimeDB(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference myRef = db.getReference("Tickets");

        myRef.addChildEventListener(new ChildEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Ticket ticket = snapshot.getValue(Ticket.class);
                if (ticket != null){
                    mListTickets.add(ticket);
                    mTicketAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Ticket ticket = snapshot.getValue(Ticket.class);
                if (ticket == null || mListTickets == null || mListTickets.isEmpty()){
                    return;
                }
                for (int i = 0;i<mListTickets.size();i++){
                    if (ticket.getId()==mListTickets.get(i).getId()){
                        mListTickets.set(i,ticket);
                        break;
                    }
                }

                mTicketAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Ticket ticket = snapshot.getValue(Ticket.class);
                if (ticket == null || mListTickets == null || mListTickets.isEmpty()){
                    return;
                }
                for (int i = 0;i<mListTickets.size();i++){
                    if (ticket.getId()==mListTickets.get(i).getId()){
                        mListTickets.remove(mListTickets.get(i));
                        break;
                    }
                }
                mTicketAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void openDialogUpdateItem(Ticket ticket){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_update);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        EditText edtUpdatenoiden = dialog.findViewById(R.id.edtUpdateNoiden);
        EditText edtUpdatenoidi = dialog.findViewById(R.id.edtUpdateNoidi);
        EditText edtUpdategiatien = dialog.findViewById(R.id.edtUpdateGiatien);

        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        Button btnUpdate = dialog.findViewById(R.id.btnUpdate);

        edtUpdatenoidi.setText(ticket.getNoiDi());
        edtUpdatenoiden.setText(ticket.getNoiDen());
        edtUpdategiatien.setText(ticket.getGiaTien());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference myRef = db.getReference("Tickets");

                String newNoidi =  edtUpdatenoidi.getText().toString().trim();
                ticket.setNoiDi(newNoidi);
                String newNoiden =  edtUpdatenoiden.getText().toString().trim();
                ticket.setNoiDen(newNoiden);
                String newGiatien = edtUpdategiatien.getText().toString().trim();
                ticket.setGiaTien(newGiatien);


                myRef.child(String.valueOf(ticket.getId())).updateChildren(ticket.toMap(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(AdminActivity.this,"Update data success",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }
        });


        dialog.show();
    }
    private void onClickDeleteData(Ticket ticket){
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.app_name))
                .setMessage("Bạn có chắc chắn muốn xóa bản ghi này không?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        FirebaseDatabase db = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = db.getReference("Tickets");

                        myRef.child(String.valueOf(ticket.getId())).removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(AdminActivity.this,"Xóa bản ghi thành công!",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .setNegativeButton("Cancel",null)
                .show();
    }
}