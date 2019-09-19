package ec.gob.arch.ejemplo.servicios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ec.gob.arch.ejemplo.Constantes.CtClienteGlp;
import ec.gob.arch.ejemplo.clases.GeVwClientesGlp;
import ec.gob.arch.ejemplo.database.BasePruebas;

public class ServiciosClienteGlp {
    public BasePruebas dbHelper;
    protected SQLiteDatabase db;
    public String[] columnas = new String[]{
            CtClienteGlp.ID_SQLITE,
            CtClienteGlp.CODIGO,
            CtClienteGlp.RUC
    };

    public ServiciosClienteGlp(Context c) {

        dbHelper = BasePruebas.nuevaInstancia(c);
    }

    public void insertarClienteGlp(GeVwClientesGlp cliente) {
        Log.v("log_servicio", "llamando a insertarClienteGlp ");

        db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CtClienteGlp.CODIGO, cliente.getCodigo());
        cv.put(CtClienteGlp.RUC, cliente.getRuc());
        db.insert(CtClienteGlp.TABLA_CLIENTE_GLP, null, cv);
        dbHelper.close();
    }

    public List<GeVwClientesGlp> buscarTodos() {
        List<GeVwClientesGlp> lista = new ArrayList<>();
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(CtClienteGlp.TABLA_CLIENTE_GLP, columnas, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            GeVwClientesGlp clientesGlp = new GeVwClientesGlp();
            clientesGlp.setIdSqlite(cursor.getInt(0));
            clientesGlp.setCodigo(cursor.getString(1));
            clientesGlp.setRuc(cursor.getString(2));
            Log.v("log_Buscar", "carga cliente " + clientesGlp.getCodigo());
            lista.add(clientesGlp);
            cursor.moveToNext();
        }
        db.close();
        return lista;
    }
}
