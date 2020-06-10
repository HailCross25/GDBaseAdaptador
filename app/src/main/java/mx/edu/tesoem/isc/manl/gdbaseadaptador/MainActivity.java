package mx.edu.tesoem.isc.manl.gdbaseadaptador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView gvDatos;
    EditText txtNombre, txtEdad, txtSexo;
    List<Datos> datos = new ArrayList<>();
    AdaptadorBase adaptadorBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gvDatos = findViewById(R.id.gvDatos);
        txtNombre = findViewById(R.id.txtNombre);
        txtEdad = findViewById(R.id.txtEdad);
        txtSexo = findViewById(R.id.txtSexo);

        Verificar();

        gvDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Datos dato = (Datos) adaptadorBase.getItem(position);
                DatosParcelable datosParcelable = new DatosParcelable(dato.getNombre(), dato.getEdad(), dato.getSexo());
                Intent intent  = new Intent(MainActivity.this, DetallesActivity.class);
                intent.putExtra("Nombre",dato.getNombre());
                intent.putExtra("DatosParcelable",datosParcelable);
                startActivity(intent);
            }
        });
    }

    private void Verificar(){
        Almacen conexion = new Almacen();

        if(conexion.Existe(this)){
            if(conexion.Leer(this)){
                datos = conexion.getDatos();
                adaptadorBase = new AdaptadorBase(datos, this);
                gvDatos.setAdapter(adaptadorBase);
            }else{
                Toast.makeText(this, "No se pudo leer el archivo", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "No existe el archivo, favor de grabar informacion", Toast.LENGTH_SHORT).show();
        }
    }

    public void Graba(View v){
        Datos dato = new Datos();
        Almacen conexion = new Almacen();

        dato.setNombre(txtNombre.getText().toString());
        dato.setEdad(txtEdad.getText().toString());
        dato.setSexo(txtSexo.getText().toString());

        datos.add(dato);
        if(conexion.Escribir(this,datos)){
            Toast.makeText(this, "Se escribieron correctmente", Toast.LENGTH_SHORT).show();
            Verificar();
        }else{
            Toast.makeText(this, "Error al escribir...", Toast.LENGTH_SHORT).show();
        }
    }
}
