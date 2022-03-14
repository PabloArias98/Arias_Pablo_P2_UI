package com.example.arias_pablo_examen2t;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> albumes;
    private Button anadirAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anadirAlbum = findViewById(R.id.addAlbum);
        final Spinner spin1 = findViewById(R.id.spinner);

        anadirAlbum.setEnabled(false);
        Bundle bundle = getIntent().getExtras();

        //Si es la primera vez que iniciamos la aplicación el bundle estará a null y se inicializará el ArrayList
        if (bundle == null) {
            albumes = new ArrayList<>();
            albumes.add("Nuevo álbum");
        } else {
            //Recogemos los datos enviados por el intent del activity de formulario y los metemos en variables
            String datosAlbum = bundle.getString("album");
            String datosArtista = bundle.getString("artista");

            //Añadimos los datos al ArrayList (album + artista)
            albumes.add(datosAlbum + " de " + datosArtista);
        }


        ArrayAdapter adapt1 = new ArrayAdapter<>(this, R.layout.item_encarnado, albumes);
        spin1.setAdapter(adapt1);
        adapt1.setDropDownViewResource(R.layout.spinner_item_encarnado);

        //Según el álbum y el artista que seleccionemos se mostrará un Toast con sus datos
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItem().toString().equals("Nuevo álbum")) {
                    anadirAlbum.setEnabled(true);
                } else {
                    Toast.makeText(MainActivity.this, parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    anadirAlbum.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    public void toFormulario(View view) {
        Intent intento = new Intent(MainActivity.this, Formulario.class);
        startActivity(intento);
    }
}


