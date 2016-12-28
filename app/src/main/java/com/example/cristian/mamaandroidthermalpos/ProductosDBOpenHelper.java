package com.example.cristian.mamaandroidthermalpos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.cristian.mamaandroidthermalpos.ProductosContract.*;

/**
 * Created by jessi on 26/12/2016.
 */

public class ProductosDBOpenHelper extends SQLiteOpenHelper {

    public static final int VERSION_DB = 1;
    public static final String NOMBRE_DB ="productos.db";





    public ProductosDBOpenHelper(Context context) {
        super(context, NOMBRE_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + ProductosContract.NombreColumnas.NOMBRE_TABLA + " ("
        +NombreColumnas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
        +NombreColumnas.CATEGORIA + " TEXT NOT NULL,"
        +NombreColumnas.STOCK + " INTEGER NOT NULL,"
        +NombreColumnas.NOMBRE_PRODUCTO + " TEXT NOT NULL,"
        +NombreColumnas.PRECIO + " FLOAT NOT NULL,"
        +NombreColumnas.REFERENCIA + " INT NOT NULL)");

        datosPrueba(db);



        //Cursor c = db.query(NombreColumnas.NOMBRE_TABLA, columnas, seleccion, seleccionArgumentos,null, null,null);


    }


    private void datosPrueba(SQLiteDatabase db){

        //Log.d("INSERCCION",Long.toString(productoDePrueba(db, new Productos("Smartphones", 50, "Xiaomi Redmi Note 3 Pro", 170, "0000000000"))));
        productoDePrueba(db, new Productos("Smartphones", 50, "Xiaomi Redmi Note 3 Pro", 170, "0000000000"));
        productoDePrueba(db, new Productos("Tablets", 20, "Bq Edison 3", 212.30f, "1111111111"));
        productoDePrueba(db, new Productos("Smartphones", 70, "Samsung Galaxy S7", 389.50f, "2222222222"));
        productoDePrueba(db, new Productos("Fundas", 50, "Funda iPhone 7", 5.3f,"3333333333"));

    }
//    public long insertarProductos(Productos productos){
//
//        SQLiteDatabase db = getWritableDatabase();
//
//        return db.insert(NombreColumnas.NOMBRE_TABLA, null, productos.toContentValues());
//    }



    public long productoDePrueba (SQLiteDatabase db, Productos productos){

      return db.insert(NombreColumnas.NOMBRE_TABLA, null,productos.toContentValues());
    }

    public Cursor obtenerProducto1(String id){
        String columnas[] = new String[]{NombreColumnas.NOMBRE_PRODUCTO};
        String seleccion =NombreColumnas.ID + " =?";
        String seleccionArgumentos[] = new String[]{id};

        Cursor c = getReadableDatabase().query(NombreColumnas.NOMBRE_TABLA, columnas, seleccion, seleccionArgumentos,null, null,null);
        return c;
    }

    public Cursor busquedaProductos(String clausula){
        String columnas[] = new String[]{NombreColumnas.NOMBRE_PRODUCTO};
        String seleccion = NombreColumnas.CATEGORIA + " LIKE ? OR " +
                NombreColumnas.NOMBRE_PRODUCTO + " LIKE ? OR " +
                NombreColumnas.PRECIO + " LIKE ? OR " +
                NombreColumnas.REFERENCIA + " LIKE ?";


        String seleccionArgumentos[] = new String[]{"%"+clausula+"%","%"+clausula+"%","%"+clausula+"%","%"+clausula+"%"};
        Cursor c = getReadableDatabase().query(NombreColumnas.NOMBRE_TABLA, columnas, seleccion, seleccionArgumentos,null, null,null);
//        Cursor c = getReadableDatabase().rawQuery("SELECT * FROM productos WHERE '"+clausula+"' IN (_id,categoria,stock,nombre_producto,precio,referencia);",null);
        return c;

    }

    public Cursor obtenerDatosProd(String nombre_prod){

        String columnas[] = new String[]{NombreColumnas.CATEGORIA, NombreColumnas.STOCK, NombreColumnas.PRECIO, NombreColumnas.REFERENCIA};
        String seleccion = NombreColumnas.NOMBRE_PRODUCTO + " = ?";
        String seleccionArgumentos[] = new String[]{nombre_prod};

        Cursor c = getReadableDatabase().query(NombreColumnas.NOMBRE_TABLA, columnas, seleccion, seleccionArgumentos, null, null, null);

        return c;


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
