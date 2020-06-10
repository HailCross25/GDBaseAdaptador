package mx.edu.tesoem.isc.manl.gdbaseadaptador;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Almacen {
    private final String  NomArch="Datos.txt";
    List<Datos> datos =  new ArrayList<>();

    public boolean Existe(Context context){
        String[] archivos = context.fileList();
        for(String archivo : archivos)
            if(archivo.equals(NomArch)) return true;
        return false;
    }

    public boolean Escribir(Context context, List<Datos> datos){
        try{
            OutputStreamWriter archivo = new OutputStreamWriter(context.openFileOutput(NomArch, Activity.MODE_PRIVATE));
            for(Datos dato : datos)
                archivo.write(ConvertirJson(dato)+"\n");
            archivo.flush();
            archivo.close();

        }catch(Exception es){return false; }

        return true;

    }

    public boolean Leer(Context context){
        try{
            InputStreamReader archivo = new InputStreamReader(context.openFileInput(NomArch));
            BufferedReader br = new BufferedReader(archivo);
            String linea="";
            while ((linea = br.readLine())!=null);
            datos.add(ConvertirClase(linea));
            br.close();
            archivo.close();

        }catch(Exception es){ return false; }
        return true;
    }

    public List<Datos> getDatos(){
        return datos;
    }

    private String ConvertirJson(Datos dato){
        Gson gson = new Gson();
        return gson.toJson(dato);
    }

    private Datos ConvertirClase(String Json){
        Gson gson = new Gson();
        return gson.fromJson(Json, Datos.class);
    }


}
