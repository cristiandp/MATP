package com.example.cristian.mamaandroidthermalpos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jessi on 30/12/2016.
 */

public class FragmentCierreCaja extends Fragment{

    View view;
    EditText txtFechaActual;
    TextView txtFechayHora;
    LinearLayout lyByM;
    Button btnByM;

    EditText edTextSaldoFinal;

    ArrayList<EditText> saldo = new ArrayList<>();

    boolean activado = false;

    Float total = 0f;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cerrar_caja, container, false);


        txtFechayHora = (TextView) view.findViewById(R.id.txtFechayHora);

        String fechaConHora = DateFormat.getDateTimeInstance().format(new Date());

        txtFechayHora.setText(fechaConHora);


        txtFechaActual = (EditText) view.findViewById(R.id.txtFechaActual);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        txtFechaActual.setText(sdf.format(new Date()));

        lyByM = (LinearLayout) view.findViewById(R.id.lyByM);

        btnByM = (Button) view.findViewById(R.id.btnByM);


        btnByM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activarByM();
            }
        });
        saldo.add((EditText) view.findViewById(R.id.edTxtCant001));
        saldo.add((EditText) view.findViewById(R.id.edTxtCant002));
        saldo.add((EditText) view.findViewById(R.id.edTxtCant005));
        saldo.add((EditText) view.findViewById(R.id.edTxtCant010));
        saldo.add((EditText) view.findViewById(R.id.edTxtCant020));
        saldo.add((EditText) view.findViewById(R.id.edTxtCant050));
        saldo.add((EditText) view.findViewById(R.id.edTxtCant1));
        saldo.add((EditText) view.findViewById(R.id.edTxtCant2));
        saldo.add((EditText) view.findViewById(R.id.edTxtCant5));
        saldo.add((EditText) view.findViewById(R.id.edTxtCant10));
        saldo.add((EditText) view.findViewById(R.id.edTxtCant20));
        saldo.add((EditText) view.findViewById(R.id.edTxtCant50));
        saldo.add((EditText) view.findViewById(R.id.edTxtCant100));
        saldo.add((EditText) view.findViewById(R.id.edTxtCant200));
        saldo.add((EditText) view.findViewById(R.id.edTxtCant500));

        edTextSaldoFinal = (EditText) view.findViewById(R.id.edTxtsaldoFinal);

        for(int i = 0 ; i < saldo.size();i++){
            saldo.get(i).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    edTextSaldoFinal.setText(calcularSaldoFinal());
                }
            });
        }


        return view;

    }


    public void activarByM() {

        if (!activado) {

            edTextSaldoFinal.setText("");

            lyByM.setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams parametros = lyByM.getLayoutParams();
            parametros.height = parametros.WRAP_CONTENT;

            lyByM.setLayoutParams(parametros);
            activado = true;

        } else if (activado) {
            lyByM.setVisibility(View.GONE);
            activado = false;
        }

    }

    public String calcularSaldoFinal() {
        total = 0f;

        for (int i = 0; i < saldo.size();i++){
            String mon;
            if(!saldo.get(i).getText().toString().equals("")){
                mon = saldo.get(i).getText().toString();
            }else{
                mon = "0";
            }

            switch (i){
                case 0:
                    total += Float.parseFloat(mon);
                    break;
                case 1:
                    total += Float.parseFloat(mon)*2;
                    break;
                case 2:
                    total += Float.parseFloat(mon)*5;
                    break;
                case 3:
                    total += Float.parseFloat(mon)*10;
                    break;
                case 4:
                    total += Float.parseFloat(mon)*20;
                    break;
                case 5:
                    total += Float.parseFloat(mon)*50;
                    break;
                case 6:
                    total += Float.parseFloat(mon)*100;
                    break;
                case 7:
                    total += Float.parseFloat(mon)*200;
                    break;
                case 8:
                    total += Float.parseFloat(mon)*500;
                    break;
                case 9:
                    total += Float.parseFloat(mon)*1000;
                    break;
                case 10:
                    total += Float.parseFloat(mon)*2000;
                    break;
                case 11:
                    total += Float.parseFloat(mon)*5000;
                    break;
                case 12:
                    total += Float.parseFloat(mon)*10000;
                    break;
                case 13:
                    total += Float.parseFloat(mon)*20000;
                    break;
                case 14:
                    total += Float.parseFloat(mon)*50000;
                    break;
            }

        }

        return String.format(Locale.getDefault(),"%.2f",total / 100);
    }
}



