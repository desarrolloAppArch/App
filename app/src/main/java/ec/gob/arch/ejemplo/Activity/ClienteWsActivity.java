package ec.gob.arch.ejemplo.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ec.gob.arch.ejemplo.R;
import ec.gob.arch.ejemplo.clases.GeVwClientesGlp;
import ec.gob.arch.ejemplo.servicios.ServiciosClienteGlp;
import ec.gob.arch.ejemplo.task.TaskActualizarPersona;

public class ClienteWsActivity extends Activity implements View.OnClickListener {
    String respuesta;
    public ServiciosClienteGlp serviciosClienteGlp;
    public List<GeVwClientesGlp> listClientes;
    public ListView lvObjetoClientes;
    public ClientesAdapter clientesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_ws);
        serviciosClienteGlp = new ServiciosClienteGlp(this);
        lvObjetoClientes = findViewById(R.id.lvClientes);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == findViewById(R.id.buttonConsumirWs).getId()) {
            Log.v("log_ws", "Dio clic en el boton consumirWS");
            try {
                TaskActualizarPersona tareaActualizar = new TaskActualizarPersona();
                tareaActualizar.execute();
                respuesta = (String) tareaActualizar.get();
                Gson gsonRespuesta = new Gson();
                Type type = new TypeToken<List<GeVwClientesGlp>>() {
                }.getType();
                List<GeVwClientesGlp> lista = gsonRespuesta.fromJson(respuesta, type);
                for (GeVwClientesGlp clientes : lista) {
                    Log.v("log_clic", "insertando Ruc: " + clientes.getCodigo());
                    serviciosClienteGlp.insertarClienteGlp(clientes);
                }
                Log.v("log_respuesta", "respuesta: " + respuesta);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        }
    }

    public void obtenerTodos(View v) {
        Log.v("log_activity", "llamandoobtenerTodos : ");
        listClientes = new ArrayList<>();
        listClientes = serviciosClienteGlp.buscarTodos();
        for (GeVwClientesGlp cliente : listClientes) {
            Log.v("log_activity", "consultando clientes: " + cliente.getIdSqlite());

        }
        clientesAdapter = new ClientesAdapter(this, R.layout.fila_cliente, listClientes);
        lvObjetoClientes.setAdapter(clientesAdapter);
    }

    class ClientesAdapter extends ArrayAdapter<GeVwClientesGlp> {
        List<GeVwClientesGlp> clientesAdapter;

        class Fila {
            TextView tvIdSqlite;
            TextView tvCodigo;
            TextView tvRuc;
        }

        public ClientesAdapter(Context context, int resource, List<GeVwClientesGlp> listaClientes) {
            super(context, resource, listaClientes);
            clientesAdapter = listaClientes;


        }

        //@RequiresApi()
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            Fila fila = null;
            fila = new Fila();

            if (view == null) {
                LayoutInflater layout = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layout.inflate(R.layout.fila_cliente, null);
                fila.tvIdSqlite = view.findViewById(R.id.tvIdSqlite);
                fila.tvCodigo = view.findViewById(R.id.tvCodigoCliente);
                fila.tvRuc = view.findViewById(R.id.tvRucCliente);
                view.setTag(fila);

            } else {
                fila = (Fila) view.getTag();
            }
            GeVwClientesGlp clientePosicionAactual = clientesAdapter.get(position);
            fila.tvIdSqlite.setText(clientePosicionAactual.getIdSqlite().toString());
            fila.tvCodigo.setText(clientePosicionAactual.getCodigo());
            fila.tvRuc.setText(clientePosicionAactual.getRuc());

            return view;
        }
    }


}
