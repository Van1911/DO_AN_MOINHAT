package com.example.do_an;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.do_an.databinding.FragmentVeCuaToiBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class TicketAdapter2 extends RecyclerView.Adapter<TicketAdapter2.TicketViewHolder> {

    private List<Ticket> mListTicket;

    View v;
    FirebaseUser user;
    FirebaseAuth mAuth;


    public TicketAdapter2(List<Ticket> mListTicket) {
        this.mListTicket = mListTicket;
    }
    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_item_place,parent,false);

        return new TicketViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        Ticket ticket = mListTicket.get(position);
        if (ticket == null) {
            return;
        }
//        holder.tvid.setText("ID: " );
        holder.tvnoidi.setText("Nơi đi: " + ticket.getNoiDi());
        holder.tvnoiden.setText("Nơi đến: " + ticket.getNoiDen());
        holder.tvgiatien.setText("Giá tiền: " + ticket.getGiaTien());

        holder.btnDatve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(v.getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.my_ticket);
                Window window =dialog.getWindow();
                if(window == null){
                    return;
                }
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                WindowManager.LayoutParams windownAttribute = window.getAttributes();
                windownAttribute.gravity = Gravity.CENTER;
                window.setAttributes(windownAttribute);
                if(Gravity.CENTER == Gravity.CENTER){
                    dialog.setCancelable(true);
                }
                EditText edtHoten = dialog.findViewById(R.id.edtHoten);
                EditText edtxac_nhan = dialog.findViewById(R.id.edt_xac_nhan);
                edtHoten.setText(ticket.getNoiDi());
                edtxac_nhan.setText(ticket.getNoiDen());

                Button quay_lai = dialog.findViewById(R.id.btn_quay_lai);
                Button gui = dialog.findViewById(R.id.btn_send);
                gui.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                quay_lai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
            }
    @Override
    public int getItemCount() {

        if (mListTicket != null){
            return  mListTicket.size();
        }

        return 0;
    }

    public class TicketViewHolder extends RecyclerView.ViewHolder{

        public TextView tvid,tvnoidi,tvnoiden, tvgiatien;
        private Button btnDatve;
        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            tvnoidi = itemView.findViewById(R.id.tVnoidi);
            tvid = itemView.findViewById(R.id.tVid);
            tvgiatien = itemView.findViewById(R.id.tVgiatien);
            tvnoiden = itemView.findViewById(R.id.tVnoiden);
            btnDatve = itemView.findViewById(R.id.btn_datve);

        }
    }
    public void SearchNoidi(ArrayList<Ticket> searchList){
        mListTicket = searchList;
        notifyDataSetChanged();
    }
    public void SearchNoiden(ArrayList<Ticket> searchList){
        mListTicket = searchList;
        notifyDataSetChanged();
    }
}
