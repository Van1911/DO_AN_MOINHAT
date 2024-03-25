package com.example.do_an;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {

    private List<Ticket> mListTicket;

    private IClickListener mIClickListener;

    public interface IClickListener{
        void onClickUpdateItem(Ticket ticket);
        void onClickDeleteItem(Ticket ticket);
    }

    public TicketAdapter(List<Ticket> mListTicket,IClickListener listener) {
        this.mListTicket = mListTicket;
        this.mIClickListener = listener;
    }
    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket,parent,false);

        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        Ticket ticket = mListTicket.get(position);
        if (ticket == null){
            return;
        }
        holder.tvid.setText("ID: " + ticket.getId());
        holder.tvnoidi.setText("Nơi đi: " + ticket.getNoiDi());
        holder.tvnoiden.setText("Nơi đến: " + ticket.getNoiDen());
        holder.tvgiatien.setText("Giá tiền: " + ticket.getGiaTien());

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mIClickListener.onClickUpdateItem(ticket);

            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIClickListener.onClickDeleteItem(ticket);
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
        private Button btnUpdate,btnDelete;


        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            tvnoidi = itemView.findViewById(R.id.tVnoidi);
            tvid = itemView.findViewById(R.id.tVid);
            tvgiatien = itemView.findViewById(R.id.tVgiatien);
            tvnoiden = itemView.findViewById(R.id.tVnoiden);
            btnUpdate = itemView.findViewById(R.id.btn_Update);
            btnDelete = itemView.findViewById(R.id.btn_Delete);
        }
    }
}