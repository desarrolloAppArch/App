package ec.gob.arch.ejemplo.task;

import android.os.AsyncTask;
import android.util.Log;

import ec.gob.arch.ejemplo.webService.ClienteWS;

public class TaskActualizarPersona extends AsyncTask {
    ClienteWS clienteWS;
    String respuesta;

    @Override
    protected String doInBackground(Object... params) {
        Log.v("log_doInBackground", "doInBackground TaskActualizarPersona: ");
        clienteWS = new ClienteWS();
        respuesta = clienteWS.actualizarPersona();

        return respuesta;
    }
}
