package com.example.cristian.mamaandroidthermalpos;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cristian.mamaandroidthermalpos.Tickets.TicketAdapter;
import com.example.cristian.mamaandroidthermalpos.Tickets.Ticket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cristian on 15/12/2016.
 */

public class FragmentDevoluciones extends android.support.v4.app.Fragment {

    View view;

    List items = new ArrayList();

    public FragmentDevoluciones(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_devoluciones, container, false);

        items.add(new Ticket("0000001"));
        items.add(new Ticket("0000002"));
        items.add(new Ticket("0000003"));
        items.add(new Ticket("0000004"));
        items.add(new Ticket("0000005"));
        items.add(new Ticket("0000006"));

// Obtener el Recycler
        RecyclerView recycler = (RecyclerView) view.findViewById(R.id.rcvDevoluciones);
        recycler.setHasFixedSize(true);

// Usar un administrador para LinearLayout
        RecyclerView.LayoutManager lManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(lManager);

// Crear un nuevo adaptador
        RecyclerView.Adapter adapter = new TicketAdapter(items,getActivity());
        recycler.setAdapter(adapter);


        return view;

    }
}
