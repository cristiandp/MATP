package com.example.cristian.mamaandroidthermalpos;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by jessi on 30/12/2016.
 */

public class CajaPageAdapter extends FragmentPagerAdapter {

    Fragment fragment;


    public CajaPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0){

            fragment = new FragmentAbrirCaja();

        }else if(position == 1){
            fragment = new FragmentCierreCaja();
        }


        return fragment;


    }

    @Override
    public int getCount() {
        return 2;
    }
}
