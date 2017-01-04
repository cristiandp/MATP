package com.example.cristian.mamaandroidthermalpos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jessi on 30/12/2016.
 */

public class FragmentAbrirCaja extends Fragment {

    View view;
    TextView txtFechayHora;
    EditText edTxtSaldoInicial;
    boolean saldoDirecto = false;
    ArrayList<EditText> saldo = new ArrayList<>();


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_abrir_caja, container, false);

        txtFechayHora = (TextView) view.findViewById(R.id.txtFechayHora);

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        txtFechayHora.setText(currentDateTimeString);

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

        edTxtSaldoInicial = (EditText) view.findViewById(R.id.edTxtSaldoInicial);

        for(int i = 0 ; i < saldo.size();i++){
            saldo.get(i).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    saldoDirecto=false;

                    return false;
                }
            });
            saldo.get(i).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void afterTextChanged(Editable editable) {
                    if(!saldoDirecto)
                    edTxtSaldoInicial.setText(calcularSaldoInicial());
                }
            });
        }

        edTxtSaldoInicial.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                saldoDirecto=true;
                limpiarCampos();
                return false;
            }
        });


        edTxtSaldoInicial.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if(saldoDirecto)
                    calcularSaldoInicial();}
        });



        return view;

    }


    public void limpiarCampos(){
        for (int i = 0; i < saldo.size();i++){
            saldo.get(i).setText("");
        }
    }

    public String calcularSaldoInicial() {
        if(saldoDirecto){
            limpiarCampos();

            String mon = edTxtSaldoInicial.getText().toString();
            mon = mon.replace(',','.');
            if(!mon.equals("")){
                MainActivity.saldo_inicial = Float.parseFloat(mon);
            }else{
                MainActivity.saldo_inicial = 0f;
            }

        }else{
            MainActivity.saldo_inicial = 0f;
               for (int i = 0; i < saldo.size();i++){
                String mon;
                if(!saldo.get(i).getText().toString().equals("")){
                    mon = saldo.get(i).getText().toString();
                }else{
                    mon = "0";
                }

                switch (i){
                    case 0:
                        MainActivity.saldo_inicial += Float.parseFloat(mon)*0.01;
                        break;
                    case 1:
                        MainActivity.saldo_inicial += Float.parseFloat(mon)*0.02;
                        break;
                    case 2:
                        MainActivity.saldo_inicial += Float.parseFloat(mon)*0.05;
                        break;
                    case 3:
                        MainActivity.saldo_inicial += Float.parseFloat(mon)*0.1;
                        break;
                    case 4:
                        MainActivity.saldo_inicial += Float.parseFloat(mon)*0.2;
                        break;
                    case 5:
                        MainActivity.saldo_inicial += Float.parseFloat(mon)*0.5;
                        break;
                    case 6:
                        MainActivity.saldo_inicial += Float.parseFloat(mon)*1;
                        break;
                    case 7:
                        MainActivity.saldo_inicial += Float.parseFloat(mon)*2;
                        break;
                    case 8:
                        MainActivity.saldo_inicial += Float.parseFloat(mon)*5;
                        break;
                    case 9:
                        MainActivity.saldo_inicial += Float.parseFloat(mon)*10;
                        break;
                    case 10:
                        MainActivity.saldo_inicial += Float.parseFloat(mon)*20;
                        break;
                    case 11:
                        MainActivity.saldo_inicial += Float.parseFloat(mon)*50;
                        break;
                    case 12:
                        MainActivity.saldo_inicial += Float.parseFloat(mon)*100;
                        break;
                    case 13:
                        MainActivity.saldo_inicial += Float.parseFloat(mon)*200;
                        break;
                    case 14:
                        MainActivity.saldo_inicial += Float.parseFloat(mon)*500;
                        break;
                }
            }
        }
        return String.format(Locale.getDefault(),"%.2f",MainActivity.saldo_inicial);
    }

}
