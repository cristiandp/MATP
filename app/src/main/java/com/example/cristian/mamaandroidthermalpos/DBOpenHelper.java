package com.example.cristian.mamaandroidthermalpos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cristian.mamaandroidthermalpos.dbContract.*;
import com.example.cristian.mamaandroidthermalpos.Productos.Producto;

/**
 * Created by jessi on 26/12/2016.
 */

public class DBOpenHelper extends SQLiteOpenHelper {

    public static final int VERSION_DB = 1;
    public static final String NOMBRE_DB ="mamaandroid.db";





    public DBOpenHelper(Context context) {
        super(context, NOMBRE_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * Aqui van las tablas de datos cuando se crea la aplicación
         * TODO Mover actualizaciones de prueba a esta parte al final
         */

        /**
         * Tabla productos (_id,categoria,stock,nombre_producto,precio,referencia)
         */
        db.execSQL("CREATE TABLE " + dbContract.NombreColumnas.TABLA_PRODUCTOS + " ("
        +NombreColumnas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
        +NombreColumnas.CATEGORIA + " TEXT NOT NULL,"
        +NombreColumnas.STOCK + " INTEGER NOT NULL,"
        +NombreColumnas.NOMBRE_PRODUCTO + " TEXT NOT NULL,"
        +NombreColumnas.PRECIO + " FLOAT NOT NULL,"
        +NombreColumnas.REFERENCIA + " INT NOT NULL)");

        //DUMMY DATA PARA PRODUCTOS
        productoDePrueba(db, new Producto("Smartphones", 50, "Xiaomi Redmi Note 3 Pro", 170, "0000000000"));
        productoDePrueba(db, new Producto("Tablets", 20, "Bq Edison 3", 212.30f, "1111111111"));
        productoDePrueba(db, new Producto("Smartphones", 70, "Samsung Galaxy S7", 389.50f, "2222222222"));
        productoDePrueba(db, new Producto("Fundas", 50, "Funda iPhone 7", 5.3f,"3333333333"));

        /**
         * Añadida tabla tickets(id,ref,hora)
         * Añadida tabla ventas(id,ref,nombre,cantidad)
         * Añadida tabla tickets_ventas(id_ticket,id_venta)
         * Añadida tabla productos_ventas(id_producto,id_venta)
         *
         */

        /**
         * columna hora se rellena con la hora de la insercción automáticamente
         */
        String sql = "CREATE TABLE "+NombreColumnas.TABLA_TICKETS+"(" +
                NombreColumnas.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                NombreColumnas.REFERENCIA + " TEXT NOT NULL," +
                NombreColumnas.HORA + " DATETIME DEFAULT CURRENT_TIMESTAMP" +
                ")";
        db.execSQL(sql);
        /**
         * la cantidad de venta es 1 por default
         */
        sql = "CREATE TABLE "+NombreColumnas.TABLA_VENTAS+"("+
                NombreColumnas.ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                NombreColumnas.NOMBRE_PRODUCTO + "TEXT NOT NULL," +
                NombreColumnas.CANTIDAD + "INTEGER DEFAULT 1" +
                ")";
        db.execSQL(sql);

        //Tablas forágenas
        sql = "CREATE TABLE "+NombreColumnas.TABLA_PRODUCTOS_VENTAS+"(" +
                NombreColumnas.ID_PRODUCTO +" INTEGER," +
                NombreColumnas.ID_VENTA + " INTEGER," +
                "PRIMARY KEY("+NombreColumnas.ID_PRODUCTO+","+NombreColumnas.ID_VENTA+")," +
                "FOREIGN KEY("+NombreColumnas.ID_PRODUCTO+") REFERENCES "+NombreColumnas.TABLA_PRODUCTOS+"("+NombreColumnas.ID+")," +
                "FOREIGN KEY("+NombreColumnas.ID_VENTA+") REFERENCES "+NombreColumnas.TABLA_VENTAS+"("+NombreColumnas.ID+")" +
                ")";
        db.execSQL(sql);
        sql = "CREATE TABLE "+NombreColumnas.TABLA_TICKETS_VENTAS+"(" +
                NombreColumnas.ID_TICKET +" INTEGER," +
                NombreColumnas.ID_VENTA + " INTEGER," +
                "PRIMARY KEY("+NombreColumnas.ID_TICKET+","+NombreColumnas.ID_VENTA+")," +
                "FOREIGN KEY("+NombreColumnas.ID_TICKET+") REFERENCES "+NombreColumnas.TABLA_TICKETS+"("+NombreColumnas.ID+")," +
                "FOREIGN KEY("+NombreColumnas.ID_VENTA+") REFERENCES "+NombreColumnas.TABLA_VENTAS+"("+NombreColumnas.ID+")" +
                ")";
        db.execSQL(sql);




    }

    /**
     * FUNCIÓN dUMMY
     */
    private void dummyData(SQLiteDatabase db){

    }


    private void datosPrueba(SQLiteDatabase db){



    }
//    public long insertarProductos(Producto productos){
//
//        SQLiteDatabase db = getWritableDatabase();
//
//        return db.insert(NombreColumnas.TABLA_PRODUCTOS, null, productos.toContentValues());
//    }



    public long productoDePrueba (SQLiteDatabase db, Producto productos){

      return db.insert(NombreColumnas.TABLA_PRODUCTOS, null,productos.toContentValues());
    }

    public Cursor obtenerProducto1(String id){
        String columnas[] = new String[]{NombreColumnas.NOMBRE_PRODUCTO};
        String seleccion =NombreColumnas.ID + " =?";
        String seleccionArgumentos[] = new String[]{id};

        Cursor c = getReadableDatabase().query(NombreColumnas.TABLA_PRODUCTOS, columnas, seleccion, seleccionArgumentos,null, null,null);
        return c;
    }

    public Cursor busquedaProductos(String clausula){
        String columnas[] = new String[]{NombreColumnas.CATEGORIA,NombreColumnas.STOCK,NombreColumnas.NOMBRE_PRODUCTO,NombreColumnas.PRECIO,NombreColumnas.REFERENCIA};
        String seleccion = NombreColumnas.CATEGORIA + " LIKE ? OR " +
                NombreColumnas.NOMBRE_PRODUCTO + " LIKE ? OR " +
                NombreColumnas.PRECIO + " LIKE ? OR " +
                NombreColumnas.REFERENCIA + " LIKE ?";


        String seleccionArgumentos[] = new String[]{"%"+clausula+"%","%"+clausula+"%","%"+clausula+"%","%"+clausula+"%"};
        Cursor c = getReadableDatabase().query(NombreColumnas.TABLA_PRODUCTOS, columnas, seleccion, seleccionArgumentos,null, null,null);
//        Cursor c = getReadableDatabase().rawQuery("SELECT * FROM productos WHERE '"+clausula+"' IN (_id,categoria,stock,nombre_producto,precio,referencia);",null);
        return c;

    }

    public Cursor obtenerDatosProd(String nombre_prod){

        String columnas[] = new String[]{NombreColumnas.CATEGORIA, NombreColumnas.STOCK, NombreColumnas.PRECIO, NombreColumnas.REFERENCIA};
        String seleccion = NombreColumnas.NOMBRE_PRODUCTO + " = ?";
        String seleccionArgumentos[] = new String[]{nombre_prod};

        Cursor c = getReadableDatabase().query(NombreColumnas.TABLA_PRODUCTOS, columnas, seleccion, seleccionArgumentos, null, null, null);

        return c;


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionDB, int nuevaVersionDB) {
        //ESTA ZONA ES BASTANTE DELICADA. REGISTRAR SIEMPRE LOS CAMBIOS HECHOS.
        //TODO Acabar actualización base de datos
        if(versionDB == 1 && nuevaVersionDB >= 2){

        }

        if(versionDB >= 1 && nuevaVersionDB >= 3){

        }

    }
}
