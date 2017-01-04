package com.example.cristian.mamaandroidthermalpos;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.cristian.mamaandroidthermalpos.Bluetooth.ConectarBluetooth;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    DrawerLayout drawerLayout;
    static NavigationView navView;
    Switch swtBluetooth;
    Fragment oldFragment = null;
    private boolean dobleBackSalir = false;
    public static boolean sCaja = false;
    public static float saldo_inicial = 0f;
    public static float saldo_final = 0f;

    private static BluetoothAdapter bAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        toolbar.setTitle("Bienvenido/a");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        Button btnVentasCobrar;


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
                                fragment = new FragmentCaja();
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
        //Comienzo de la conexión
        new ConectarBluetooth(this);

    }

    private final BroadcastReceiver bReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String accion = intent.getAction();
            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(accion)){
                //SE HA PRODUCIDO UNA ACCION EN EL BT - TODAVIA DESCONOCIDA
                final int estado = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,BluetoothAdapter.ERROR);
                switch(estado){
                    case BluetoothAdapter.STATE_ON:
                        //SE HA ACTIVADO EL BLUETOOTH
                        swtBluetooth.setChecked(true);
                        if(FragmentVentas.btnCobrar != null)
                            FragmentVentas.btnCobrar.setEnabled(true);
                        ConectarBluetooth.bAdapter.startDiscovery();
                        break;
                    case BluetoothAdapter.STATE_OFF:
                        swtBluetooth.setChecked(false);
                        if(FragmentVentas.btnCobrar != null)
                            FragmentVentas.btnCobrar.setEnabled(false);
                        if(ConectarBluetooth.bAdapter.isDiscovering())
                            ConectarBluetooth.bAdapter.cancelDiscovery();
                        break;
                }
            }

            final String accion2 = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(accion2)) {
                //HAY QUE COMPROBAR SI ES LA IMPRESORA
                final BluetoothDevice impresora = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                if (impresora.getAddress().equals("00:11:22:33:44:55")) {
                    ConectarBluetooth.bAdapter.cancelDiscovery();
                    try {
                        ConectarBluetooth.socket = ConectarBluetooth.getSocket(impresora);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(accion)) {
                //CUANDO SE PARA LA BÚSQUEDA
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
            filtro.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
            filtro.addAction(BluetoothDevice.ACTION_FOUND);
            getApplicationContext().registerReceiver(bReceiver,filtro);
            if(ConectarBluetooth.bAdapter.isEnabled()){
                swtBluetooth.setChecked(true);
                ConectarBluetooth.bAdapter.startDiscovery();
            }else{
                swtBluetooth.setChecked(false);
            }
        }
        return true;
    }

    public static void cambiarActivo(int menu, boolean estado){
        navView.getMenu().getItem(menu).setChecked(estado);
    }

    public void cambiarTitulo(String titulo){
        getSupportActionBar().setTitle(titulo);
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
            //Reset de la variable en caso de que no se le
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

        if(b){
            ConectarBluetooth.bAdapter.enable();
        }else{
            ConectarBluetooth.bAdapter.disable();
        }
    }
}


