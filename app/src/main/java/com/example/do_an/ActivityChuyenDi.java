package com.example.do_an;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ActivityChuyenDi extends AppCompatActivity {
    ImageButton backHome;
    RecyclerView rcvTicket;

    SearchView searchNoidi,searchNoiden;
    private TicketAdapter2 ticketAdapter;
    private List<Ticket> mListTickets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuyen_di);

        searchNoidi = findViewById(R.id.src_noidi);
        searchNoidi.clearFocus();
        searchNoiden = findViewById(R.id.src_noiden);
        searchNoiden.clearFocus();


        backHome=findViewById(R.id.backHome);
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rcvTicket=findViewById(R.id.rcvVe);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvTicket.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rcvTicket.addItemDecoration(dividerItemDecoration);
        mListTickets  = new ArrayList<>();
        ticketAdapter = new TicketAdapter2(mListTickets);
        rcvTicket.setAdapter(ticketAdapter);
        getListTicketFromRealtimeDatabase();


        searchNoidi.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchListNoidi(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchListNoidi(newText);

                return true;
            }
        });
        searchNoiden.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchListNoiden(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchListNoiden(newText);

                return true;
            }
        });
    }
    private void getListTicketFromRealtimeDatabase(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference myRef = db.getReference("Tickets");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Ticket ticket = dataSnapshot.getValue(Ticket.class);
                    mListTickets.add(ticket);
                }
                ticketAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ActivityChuyenDi.this,"Không có chuyến đi nào!",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void searchListNoidi(String bencangdi){
        ArrayList<Ticket> searchlist = new ArrayList<>();
        for (Ticket ticket : mListTickets){
            if (ticket.getNoiDi().toLowerCase().contains(bencangdi.toLowerCase())){
                searchlist.add(ticket);
            }
        }
        ticketAdapter.SearchNoidi(searchlist);
    }
    public void searchListNoiden(String bencangden){
        ArrayList<Ticket> searchlist = new ArrayList<>();
        for (Ticket ticket : mListTickets){
            if (ticket.getNoiDen().toLowerCase().contains(bencangden.toLowerCase())){
                searchlist.add(ticket);
            }
        }
        ticketAdapter.SearchNoiden(searchlist);
    }
}
