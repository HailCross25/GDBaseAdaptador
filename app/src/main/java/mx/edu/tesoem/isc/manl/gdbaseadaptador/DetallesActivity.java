package mx.edu.tesoem.isc.manl.gdbaseadaptador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetallesActivity extends AppCompatActivity {

    TextView lblNombre, lblEdad, lblSexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        lblNombre = findViewById(R.id.dlblNombre);
        lblEdad = findViewById(R.id.dlblEdad);
        lblSexo = findViewById(R.id.dlblSexo);

        String nombre = getIntent().getExtras().get("Nombre").toString();
        DatosParcelable dato = getIntent().getParcelableExtra("DatosParcelable");

        lblNombre.setText(dato.getNombre());
        lblEdad.setText(dato.getEdad());
        lblSexo.setText(dato.getSexo());

        getSupportActionBar().setTitle(nombre);
    }
}
