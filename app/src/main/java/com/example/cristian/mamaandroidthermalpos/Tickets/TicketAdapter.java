package com.example.cristian.mamaandroidthermalpos.Tickets;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cristian.mamaandroidthermalpos.FragmentTickets;
import com.example.cristian.mamaandroidthermalpos.R;

import java.util.List;

/**
 * Created by Cristian on 16/12/2016.
 */

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder>{
    private List<Ticket> items;
    private FragmentActivity activity;

    public static class TicketViewHolder extends RecyclerView.ViewHolder{

        public Button btnTicket;
        public TicketViewHolder(View v){
            super(v);
            btnTicket = (Button) v.findViewById(R.id.btnTicket);
        }
    }


    public TicketAdapter(List<Ticket> items){
        this.items = items;
    }

    public TicketAdapter(List<Ticket> items, FragmentActivity activity){this.items = items;this.activity = activity;}




    @Override
    public TicketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tickets_card_layout,parent,false);
        return new TicketViewHolder(view);
    }




    @Override
    public void onBindViewHolder(TicketViewHolder holder, final int position) {
        String texto = "Ticket Ref: "+items.get(position).getnRef();
        holder.btnTicket.setText(texto);
        holder.btnTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new FragmentTickets();
                Bundle bundle = new Bundle();
                bundle.putString("REF",items.get(position).getnRef());
                fragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment).addToBackStack(null)
                        .commit();
            }
        });


//        holder.btnCantidadMas.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                items.get(position).setCantidad(items.get(position).getCantidad() +1 );
//                notifyItemChanged(position);
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

}
