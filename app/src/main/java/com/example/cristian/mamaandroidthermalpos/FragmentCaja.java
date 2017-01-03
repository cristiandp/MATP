package com.example.cristian.mamaandroidthermalpos;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cristian.mamaandroidthermalpos.tabCajas.CajaPageAdapter;

import static android.support.design.widget.TabLayout.*;

/**
 * Created by Cristian on 15/12/2016.
 */

public class FragmentCaja extends android.support.v4.app.Fragment implements View.OnClickListener{

    View view;
    ViewPager viewPager;
    TabLayout tabLayout;
    FloatingActionButton fabCaja;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_caja, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new CajaPageAdapter(getChildFragmentManager()));

        tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        fabCaja = (FloatingActionButton) view.findViewById(R.id.fabCaja);
        fabCaja.setOnClickListener(this);

       /* TabHost tabHost = getTabHost();

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            public void onTabChanged(String str) {

            }
        });*/


        return view;


    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.fabCaja){
            Toast.makeText(getContext(),"SE HA DADO CLICK AL BOTON",Toast.LENGTH_SHORT).show();
            if(!MainActivity.sCaja){

                MainActivity.sCaja = true;
            }
            else{
                //Caja cerrada


            }
        }
    }
}
