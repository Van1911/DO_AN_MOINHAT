package com.example.do_an;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class TicketAdapter2 extends RecyclerView.Adapter<TicketAdapter2.TicketViewHolder> {

    private List<Ticket> mListTicket;

    private IClickListener mIClickListener;

    public interface IClickListener{
        void onClickUpdateItem(Ticket ticket);
        void onClickDeleteItem(Ticket ticket);
    }

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
        if (ticket == null){
            return;
        }
//        holder.tvid.setText("ID: " + ticket.getId());
        holder.tvnoidi.setText("Nơi đi: " + ticket.getNoiDi());
        holder.tvnoiden.setText("Nơi đến: " + ticket.getNoiDen());
        holder.tvgiatien.setText("Giá tiền: " + ticket.getGiaTien());
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
        private Button btnDelete;


        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            tvnoidi = itemView.findViewById(R.id.tVnoidi);
            tvid = itemView.findViewById(R.id.tVid);
            tvgiatien = itemView.findViewById(R.id.tVgiatien);
            tvnoiden = itemView.findViewById(R.id.tVnoiden);
            btnDelete = itemView.findViewById(R.id.btn_Delete);
        }
    }
}