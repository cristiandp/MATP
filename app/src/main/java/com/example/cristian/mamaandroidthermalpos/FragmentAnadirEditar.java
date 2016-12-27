package com.example.cristian.mamaandroidthermalpos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jessi on 27/12/2016.
 */

public class FragmentAnadirEditar extends Fragment{

    View vAnadirEditar;

    TextView txtInfo;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        vAnadirEditar = inflater.inflate(R.layout.fragment_anadir_editar, container, false);

        txtInfo = (TextView)vAnadirEditar.findViewById(R.id.txtInformativo);

        Bundle bundle = this.getArguments();

        if(bundle != null){

            String info = bundle.getString("info");

            txtInfo.setText(info);
        }

        return vAnadirEditar;


    }
}
