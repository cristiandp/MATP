package com.example.cristian.mamaandroidthermalpos.tabCajas;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.cristian.mamaandroidthermalpos.FragmentAbrirCaja;
import com.example.cristian.mamaandroidthermalpos.FragmentCierreCaja;

/**
 * Created by jessi on 30/12/2016.
 */

public class CajaPageAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private String[] tabTitles = new String[]{"Abrir Caja","Cerrar caja"};
    Fragment fragment;
    Context context;

    public CajaPageAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new FragmentAbrirCaja();
        }else{
            return new FragmentCierreCaja();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
