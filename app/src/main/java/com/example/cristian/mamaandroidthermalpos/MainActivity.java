package com.example.cristian.mamaandroidthermalpos;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.cristian.mamaandroidthermalpos.Bluetooth.ConectarBluetooth;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    DrawerLayout drawerLayout;
    NavigationView navView;
    Switch swtBluetooth;
    Fragment oldFragment = null;
    private boolean dobleBackSalir = false;

    private BluetoothAdapter bAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        toolbar.setTitle("Bienvenido/a");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        drawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);
        navView = (NavigationView) this.findViewById(R.id.navview);

        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        boolean fragmentTransaction = false;
                        Fragment fragment = null;

                        switch(item.getItemId()){
                            case R.id.menu_seccion_1:
                                fragment = new FragmentVentas();
                                fragmentTransaction=true;
                                break;
                            case R.id.menu_seccion_2:
                                fragment = new FragmentDevoluciones();
                                fragmentTransaction=true;
                                break;
                            case R.id.menu_seccion_3:
                                fragment = new FragmentCierre();
                                fragmentTransaction=true;
                                break;
                            case R.id.menu_seccion_4:
                                fragment = new FragmentOpciones();
                                fragmentTransaction=true;
                                break;

                            case R.id.menu_salir:
                                finish();
                                break;
                        }

                        if(fragmentTransaction && oldFragment != fragment) {
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.content_frame, fragment,"fragment_exit")
                                    .addToBackStack(null)
                                    .commit();

                            item.setChecked(true);
                            getSupportActionBar().setTitle(item.getTitle());
                            drawerLayout.closeDrawers();
                        }
                        return true;
                    }
                }
        );
        bAdapter = BluetoothAdapter.getDefaultAdapter();

        if(bAdapter != null){
            //Es un dispositivo BT
            if(bAdapter.isEnabled()) {
                Toast.makeText(this, "BT ACTIVO", Toast.LENGTH_SHORT).show();



            }else{
                Toast.makeText(this, "BT APAGADO", Toast.LENGTH_SHORT).show();
            }

        }else{
            //Carece de BT
        }

    }


    private final BroadcastReceiver bReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "SE HA CAMBIADO EL ESTADO DEL BT", Toast.LENGTH_SHORT).show();
            final String accion = intent.getAction();
            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(accion)){
                //SE HA PRODUCIDO UNA ACCION EN EL BT - TODAVIA DESCONOCIDA
                final int estado = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,BluetoothAdapter.ERROR);
                switch(estado){
                    case BluetoothAdapter.STATE_ON:
                        //SE HA ACTIVADO EL BLUETOOTH
                        swtBluetooth.setChecked(true);
                        break;
                    case BluetoothAdapter.STATE_OFF:
                        swtBluetooth.setChecked(false);
                        break;
                }
            }
        }
    };

    //Inflador del menú
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        swtBluetooth = (Switch) this.findViewById(R.id.switchBluetooth);
        if(swtBluetooth != null){
            swtBluetooth.setOnCheckedChangeListener(this);

            IntentFilter filtro = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            getApplicationContext().registerReceiver(bReceiver,filtro);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if(dobleBackSalir){
            finish();
            return;
        }

        Fragment fragmentCierre = getSupportFragmentManager().findFragmentByTag("fragment_exit");
        if(fragmentCierre != null &&fragmentCierre.isVisible()){
            //Estas en un fragment de salida
            dobleBackSalir = true;
            Toast.makeText(this, R.string.mensajeSalida, Toast.LENGTH_SHORT).show();
            if(!drawerLayout.isDrawerOpen(GravityCompat.START)){
                drawerLayout.openDrawer(GravityCompat.START);
            }
            //Reset de la variable en caso de que no le den
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    dobleBackSalir=false;
                }
            }, 2000);
        }else{
            getSupportFragmentManager().popBackStackImmediate();
        }
    }



    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        ConectarBluetooth cB = new ConectarBluetooth();
        if(b){
            Toast.makeText(getApplicationContext(),"El switch esta en ON",Toast.LENGTH_SHORT).show();

            cB.empezarBusqueda(getApplicationContext());

        }else{
            Toast.makeText(getApplicationContext(),"El switch esta en OFF",Toast.LENGTH_SHORT).show();

            cB.finalizarBúsqueda(getApplicationContext());
        }
    }
}


