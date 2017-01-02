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
import java.util.Date;

/**
 * Created by jessi on 30/12/2016.
 */

public class FragmentCierreCaja extends Fragment {

    View view;
    EditText txtFechaActual;
    TextView txtFechayHora;
    LinearLayout lyByM;
    Button btnByM;

    EditText edTextCant001, edTextCant002, edTextCant005, edTextCant010, edTextCant020, edTextCant050,
            edTextCant1, edTextCant2, edTextCant5, edTextCant10, edTextCant20,
            edTextCant50, edTextCant100, edTextCant200, edTextCant500;

    EditText edTextSaldoFinal;

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

        edTextCant001 = (EditText) view.findViewById(R.id.edTxtCant001);
        edTextCant002 = (EditText) view.findViewById(R.id.edTxtCant002);
        edTextCant005 = (EditText) view.findViewById(R.id.edTxtCant005);
        edTextCant010 = (EditText) view.findViewById(R.id.edTxtCant010);
        edTextCant020 = (EditText) view.findViewById(R.id.edTxtCant020);
        edTextCant050 = (EditText) view.findViewById(R.id.edTxtCant050);
        edTextCant1 = (EditText) view.findViewById(R.id.edTxtCant1);
        edTextCant2 = (EditText) view.findViewById(R.id.edTxtCant2);
        edTextCant5 = (EditText) view.findViewById(R.id.edTxtCant5);
        edTextCant10 = (EditText) view.findViewById(R.id.edTxtCant10);
        edTextCant20 = (EditText) view.findViewById(R.id.edTxtCant20);
        edTextCant50 = (EditText) view.findViewById(R.id.edTxtCant50);
        edTextCant100 = (EditText) view.findViewById(R.id.edTxtCant100);
        edTextCant200 = (EditText) view.findViewById(R.id.edTxtCant200);
        edTextCant500 = (EditText) view.findViewById(R.id.edTxtCant500);

        edTextSaldoFinal = (EditText) view.findViewById(R.id.edTxtsaldoFinal);

        edTextCant001.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                edTextSaldoFinal.setText(String.valueOf(calcularSaldoFinal(edTextCant001)));
            }
        });

        edTextCant002.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                edTextSaldoFinal.setText(String.valueOf(calcularSaldoFinal(edTextCant002)));
            }
        });

        edTextCant005.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                edTextSaldoFinal.setText(String.valueOf(calcularSaldoFinal(edTextCant005)));
            }
        });

        edTextCant010.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                edTextSaldoFinal.setText(String.valueOf(calcularSaldoFinal(edTextCant010)));
            }
        });

        edTextCant020.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                edTextSaldoFinal.setText(String.valueOf(calcularSaldoFinal(edTextCant020)));
            }
        });

        edTextCant050.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                edTextSaldoFinal.setText(String.valueOf(calcularSaldoFinal(edTextCant050)));
            }
        });

        edTextCant1.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                edTextSaldoFinal.setText(String.valueOf(calcularSaldoFinal(edTextCant1)));

            }
        });

        edTextCant2.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                edTextSaldoFinal.setText(String.valueOf(calcularSaldoFinal(edTextCant2)));
            }
        });

        edTextCant5.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                edTextSaldoFinal.setText(String.valueOf(calcularSaldoFinal(edTextCant5)));
            }
        });

        edTextCant10.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                edTextSaldoFinal.setText(String.valueOf(calcularSaldoFinal(edTextCant10)));
            }
        });

        edTextCant20.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                edTextSaldoFinal.setText(String.valueOf(calcularSaldoFinal(edTextCant20)));
            }
        });

        edTextCant50.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                edTextSaldoFinal.setText(String.valueOf(calcularSaldoFinal(edTextCant50)));
            }
        });

        edTextCant100.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                edTextSaldoFinal.setText(String.valueOf(calcularSaldoFinal(edTextCant100)));
            }
        });

        edTextCant200.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                edTextSaldoFinal.setText(String.valueOf(calcularSaldoFinal(edTextCant200)));
            }
        });

        edTextCant500.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                edTextSaldoFinal.setText(String.valueOf(calcularSaldoFinal(edTextCant500)));
            }
        });


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

    public float calcularSaldoFinal(EditText edTxt) {

        int cant = 0;

        switch (edTxt.getId()) {

            case R.id.edTxtCant001:

                if(edTxt.getText().toString().equals("")){
                    total = total - ((cant) * 0.01f);

                    Toast.makeText(getContext(),cant, Toast.LENGTH_SHORT).show();
                }else {

                    cant = Integer.parseInt(edTextCant001.getText().toString());

                    total = total + (cant) * 0.01f;
                }

                break;

            case R.id.edTxtCant002:

                if(edTxt.getText().toString().equals("")){
                    total = total - (cant) * 0.02f;

                    Toast.makeText(getContext(),total.toString(), Toast.LENGTH_SHORT).show();
                }else {

                    cant = Integer.parseInt(edTextCant002.getText().toString());

                    total = total + (cant) * 0.02f;

                    break;
                }

            case R.id.edTxtCant005:

                cant = Integer.parseInt(edTextCant005.getText().toString());

                total = total + (cant) * 0.05f;

                break;

            case R.id.edTxtCant010:

                cant = Integer.parseInt(edTextCant010.getText().toString());

                total = total + (cant) * 0.10f;

                break;

            case R.id.edTxtCant020:

                cant = Integer.parseInt(edTextCant010.getText().toString());

                total = total + (cant) * 0.20f;

                break;

            case R.id.edTxtCant050:

                cant = Integer.parseInt(edTextCant010.getText().toString());

                total = total + (cant) * 0.50f;

                break;

            case R.id.edTxtCant1:

                cant = Integer.parseInt(edTextCant010.getText().toString());

                total = total + (cant) * 1f;

                break;

            case R.id.edTxtCant2:

                cant = Integer.parseInt(edTextCant010.getText().toString());

                total = total + (cant) * 2f;

                break;

            case R.id.edTxtCant5:

                cant = Integer.parseInt(edTextCant010.getText().toString());

                total = total + (cant) * 5f;

                break;

            case R.id.edTxtCant10:

                cant = Integer.parseInt(edTextCant010.getText().toString());

                total = total + (cant) * 10f;

                break;

            case R.id.edTxtCant20:

                cant = Integer.parseInt(edTextCant010.getText().toString());

                total = total + (cant) * 20f;

                break;

            case R.id.edTxtCant50:

                cant = Integer.parseInt(edTextCant010.getText().toString());

                total = total + (cant) * 50f;

                break;

            case R.id.edTxtCant100:

                cant = Integer.parseInt(edTextCant010.getText().toString());

                total = total + (cant) * 100f;

                break;

            case R.id.edTxtCant200:

                cant = Integer.parseInt(edTextCant010.getText().toString());

                total = total + (cant) * 200f;

                break;

            case R.id.edTxtCant500:

                cant = Integer.parseInt(edTextCant010.getText().toString());

                total = total + (cant) * 500f;

                break;

        }
        return total;

    }
}



