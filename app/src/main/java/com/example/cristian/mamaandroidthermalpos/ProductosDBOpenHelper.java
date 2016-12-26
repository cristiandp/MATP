package com.example.cristian.mamaandroidthermalpos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.cristian.mamaandroidthermalpos.ProductosContract.*;

/**
 * Created by jessi on 26/12/2016.
 */

public class ProductosDBOpenHelper extends SQLiteOpenHelper {

    public static final int VERSION_DB = 1;
    public static final String NOMBRE_DB ="productos.db";





    public ProductosDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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
        +NombreColumnas.REFERENCIA + " INT NOT NULL");

        //Contenedor de valores. Almacena las columnas del registro en pares clave-valor
        ContentValues values = new ContentValues();

        values.put(NombreColumnas.CATEGORIA, "Smartphones");
        values.put(NombreColumnas.STOCK, "50");
        values.put(NombreColumnas.NOMBRE_PRODUCTO, "Xiaomi Redmi Note 3 Pro");
        values.put(NombreColumnas.PRECIO, "170");
        values.put(NombreColumnas.REFERENCIA, "0000000000");

        db.insert(ProductosContract.NombreColumnas.NOMBRE_TABLA, null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
