package ec.gob.arch.ejemplo.webService;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ec.gob.arch.ejemplo.R;
import ec.gob.arch.ejemplo.clases.GeVwClientesGlp;
import ec.gob.arch.ejemplo.clases.Persona;

public class ClienteWS {

    public String actualizarPersona() {
        try {
            /**
             * Abrimos la conexion
             */

            Log.v("log_ClienteWS", "Inicio actualizarPersona: ");
            URL url = new URL("http://172.16.14.120:8080/webServiceGlpMobilWeb/rest/ws-usuario/metodo-obtenerDistribuidores");
            String respuesta = null;
            HttpURLConnection connexion = (HttpURLConnection) url.openConnection();


            /**
             * seteamos los parametros de entrada y salida
             */
            connexion.setDoInput(true);
            connexion.setDoOutput(true);

            connexion.setRequestMethod("POST");
            connexion.setRequestProperty("Accept", "application/json");
            connexion.setRequestProperty("Content-Type", "application/json");
            connexion.setReadTimeout(10000);

            //escribir en la conexion
            OutputStream stream = connexion.getOutputStream();
            OutputStreamWriter body = new OutputStreamWriter(stream);

            //cargar lista
            GeVwClientesGlp cliente = new GeVwClientesGlp();
            cliente.setCodigoTipo("57");
            cliente.setRuc("0103375622001");
            //Transformamos a formato Gson
            Gson gson = new GsonBuilder().create();
            String parametro = gson.toJson(cliente);
            //Transformamos a String el Gson
//            String personasGson= gson.toJson(listaPersonas);

            body.write(parametro);
            body.close();
// lee la respuesta;
            InputStreamReader recibe = new InputStreamReader(connexion.getInputStream());
            BufferedReader buffer = new BufferedReader(recibe);
            respuesta = buffer.readLine();
            Log.v("log", "respuesta ws: " + respuesta);
            //    Gson gsonRespuesta=  new Gson();
            //     Type type= new TypeToken<List<GeVwClientesGlp>>(){}.getType();

            //     gsonRespuesta.fromJson(respuesta,type);
            return respuesta;


        } catch (ProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }


}

