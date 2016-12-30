package com.example.cristian.mamaandroidthermalpos;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cristian.mamaandroidthermalpos.tabCajas.CajaPageAdapter;

import static android.support.design.widget.TabLayout.*;

/**
 * Created by Cristian on 15/12/2016.
 */

public class FragmentCaja extends android.support.v4.app.Fragment{

    View view;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_caja, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new CajaPageAdapter(getFragmentManager(),getContext()));

        tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }



}
