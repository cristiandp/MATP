package com.example.cristian.mamaandroidthermalpos;

import android.app.DatePickerDialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jessi on 30/12/2016.
 */

public class FragmentCierreCaja extends Fragment{

    View view;
    EditText edtxtFechaActual;
    TextView txtFechayHora;
    LinearLayout lyByM;
    AppCompatButton btnByM;
    AppCompatButton btnDiarioCierre;

    EditText edTextSaldoFinal;
    LinearLayout lyDiarioCierre;
    boolean saldoDirecto = false;

    ArrayList<EditText> saldo = new ArrayList<>();

    boolean activado = false;

    ColorStateList cslMagenta;
    ColorStateList cslGris;

  //  Float total = 0f;


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cerrar_caja, container, false);


        txtFechayHora = (TextView) view.findViewById(R.id.txtFechayHora);

        String fechaConHora = DateFormat.getDateTimeInstance().format(new Date());

        txtFechayHora.setText(fechaConHora);


        edtxtFechaActual = (EditText) view.findViewById(R.id.edtxtFechaActual);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        edtxtFechaActual.setText(sdf.format(new Date()));

        lyByM = (LinearLayout) view.findViewById(R.id.lyByM);

        btnByM = (AppCompatButton) view.findViewById(R.id.btnByM);


        btnByM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edTextSaldoFinal.setText("");
                activarBotones();
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
                        edTextSaldoFinal.setText(calcularSaldoFinal());
                }
            });
        }

        btnDiarioCierre = (AppCompatButton) view.findViewById(R.id.btnDiarioCierre);
        lyDiarioCierre = (LinearLayout)view.findViewById(R.id.lyDiarioCierre);

        btnDiarioCierre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activarBotones();

            }
        });


        cslMagenta = new ColorStateList(new int[][]{new int[0]}, new int[]{0xffff4081});
        cslGris = new ColorStateList(new int[][]{new int[0]}, new int[]{0xffd6d7d7});
        btnByM.setSupportBackgroundTintList(cslMagenta);


        edtxtFechaActual.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        return view;

    }

    public void activarBotones(){

        if(!activado){
            lyDiarioCierre.setVisibility(View.VISIBLE);
            lyByM.setVisibility(View.GONE);

            btnDiarioCierre.setSupportBackgroundTintList(cslMagenta);
            btnDiarioCierre.setTextColor(Color.WHITE);
            btnByM.setSupportBackgroundTintList(cslGris);
            btnByM.setTextColor(Color.BLACK);

            activado = true;

        }else if(activado){
            lyDiarioCierre.setVisibility(View.GONE);
            lyByM.setVisibility(View.VISIBLE);

            btnDiarioCierre.setSupportBackgroundTintList(cslGris);
            btnDiarioCierre.setTextColor(Color.BLACK);
            btnByM.setSupportBackgroundTintList(cslMagenta);
            btnByM.setTextColor(Color.WHITE);

            activado = false;

        }
    }

    public void limpiarCampos(){
        for (int i = 0; i < saldo.size();i++){
            saldo.get(i).setText("");
        }
    }

    public String calcularSaldoFinal() {
        if(saldoDirecto){
            limpiarCampos();

            String mon = edTextSaldoFinal.getText().toString();
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


    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };


    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

        edtxtFechaActual.setText(sdf.format(myCalendar.getTime()));
    }

}



