package ec.gob.arch.ejemplo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import ec.gob.arch.ejemplo.Constantes.CtClienteGlp;

public class BasePruebas extends SQLiteOpenHelper {
    public static String NOMBRE_BASE = "pruebas";
    public static int VERSION_BASE = 1;
    public static BasePruebas basePruebas;

    public BasePruebas(Context context) {

        super(context, NOMBRE_BASE, null, VERSION_BASE);
        Log.v("log_BasePrueba", "Entró a contructor BasePruebas ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v("log_BasePrueba", "Entró a onCreate ");

        crearTablaClienteGLP(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.v("log_BasePrueba", "Entró a contructor onUpgrade ");
    }

    public static BasePruebas nuevaInstancia(Context context) {
        if (basePruebas == null) {
            Log.v("log_BasePrueba", "No existe base de datos, crea una nueva base de datos");
            basePruebas = new BasePruebas(context);

        } else {
            Log.v("log_BasePrueba", "Si existe base de datos");
        }
        return basePruebas;
    }

    public void crearTablaClienteGLP(SQLiteDatabase db) {
        Log.v("log_BasePrueba", "antes  crearTablaClienteGLP");
        db.execSQL("CREATE TABLE " + CtClienteGlp.TABLA_CLIENTE_GLP + "(" +
                CtClienteGlp.ID_SQLITE + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CtClienteGlp.CODIGO + " TEXT ," +
                CtClienteGlp.RUC + " TEXT " + ");");
        Log.v("log_BasePrueba", "despues  crearTablaClienteGLP");
    }
}
