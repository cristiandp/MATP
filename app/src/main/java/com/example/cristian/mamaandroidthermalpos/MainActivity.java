package com.example.cristian.mamaandroidthermalpos;

import android.annotation.TargetApi;
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
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    DrawerLayout drawerLayout;
    NavigationView navView;
    Switch swtBluetooth;
    Fragment oldFragment = null;
    private boolean dobleBackSalir = false;

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
    }

    //Inflador del menú
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        swtBluetooth = (Switch) this.findViewById(R.id.switchBluetooth);
        if(swtBluetooth != null){
            swtBluetooth.setOnCheckedChangeListener(this);
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
        if(b){
            Toast.makeText(getApplicationContext(),"El switch esta en ON",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"El switch esta en OFF",Toast.LENGTH_SHORT).show();
        }
    }
}


