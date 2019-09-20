package ec.gob.arch.ejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import ec.gob.arch.ejemplo.Activity.ClienteWsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void irPaginaWs(View v) {
        Log.v("logIngresa", "entra consumirWS");
        Intent irPagina = new Intent(MainActivity.this, ClienteWsActivity.class);
        startActivity(irPagina);

        Log.v("logIrWs", "Ir a Pagina WS");
        Log.v("logIrWs", "log de prueba");
        Log.v("logIrWs", "Cambio de Prueba, Irvin");
        Log.v("logIrWs", "Cambio de Prueba, Gaby");
        Log.v("logIrWs", "Otra prueba");
        Log.v("logIrWs", "Blanky prueba");


    }

    public void holaIrvin(){
        
    }
    public void holaBlanky(){


    }
    public void holaBlanky2(){


    }

    public void holaGaby(){
        Log.v("logIrWs", "Cambio de Prueba, Gaby");
    }

}
