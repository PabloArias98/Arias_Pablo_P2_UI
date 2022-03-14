package com.example.arias_pablo_examen2t;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Formulario extends AppCompatActivity {

    private Button btnAnadido, btnBuscar;
    private EditText textoAlbum, textoArtista;
    private RadioGroup grupoGenero;
    private RadioButton radioElectro, radioRB;
    private TextView enunciadoS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        final Spinner spinSub = findViewById(R.id.spinnerSub);
        grupoGenero = findViewById(R.id.radgroup);
        enunciadoS = findViewById(R.id.enuncSubgenero);

        textoAlbum = findViewById(R.id.campoAlbum);
        textoArtista = findViewById(R.id.campoArtista);

        btnBuscar = findViewById(R.id.btnBuscar);
        btnAnadido = findViewById(R.id.btnAnadir);

        radioElectro = findViewById(R.id.rdElectronica);
        radioRB = findViewById(R.id.rdRB);

        String[] listaSubElectro = new String[]{"House", "ElectroHipHop", "Eurodance", "Europop", "Techno", "Trance"};
        ArrayAdapter adaptElectro = new ArrayAdapter<>(Formulario.this, R.layout.item_encarnado, listaSubElectro);
        adaptElectro.setDropDownViewResource(R.layout.spinner_item_encarnado);

        String[] listaSubRB = new String[]{"Disco", "Funk", "New Jack Swing", "Rhythm and Blues"};
        ArrayAdapter adaptRB = new ArrayAdapter<>(Formulario.this,R.layout.item_encarnado, listaSubRB);
        adaptRB.setDropDownViewResource(R.layout.spinner_item_encarnado);

        spinSub.setVisibility(View.INVISIBLE);
        enunciadoS.setVisibility(View.INVISIBLE);

        //Según la familia de géneros que escojamos el spinner tomará unos subgéneros u otros
        grupoGenero.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rdElectronica:
                        spinSub.setAdapter(adaptElectro);

                        spinSub.setVisibility(View.VISIBLE);
                        enunciadoS.setVisibility(View.VISIBLE);
                        break;

                    case R.id.rdRB:
                        spinSub.setAdapter(adaptRB);
                        spinSub.setVisibility(View.VISIBLE);
                        enunciadoS.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });


    }

    public void buscarWeb(View view) {
        if (textoAlbum.getText().toString().isEmpty() && (textoArtista.getText().toString().isEmpty())) {
            Toast.makeText(Formulario.this, "Los dos campos están vacíos", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();

            String datosAlbum = textoAlbum.getText().toString();
            String datosArtista = textoArtista.getText().toString();

            intent.setAction(Intent.ACTION_VIEW);

            intent.setData(Uri.parse("https://www.google.com/search?q=" + datosAlbum + "+" + datosArtista));

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "Error, introduzca un dato correcto!!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void anadido(View view) {
        if ((!textoAlbum.getText().toString().isEmpty() || (!textoArtista.getText().toString().isEmpty())) && (!radioElectro.isChecked() && !radioRB.isChecked())) {
            Toast.makeText(Formulario.this, "Debe elegir un género", Toast.LENGTH_SHORT).show();
        }
        else if ((textoAlbum.getText().toString().isEmpty() && (textoArtista.getText().toString().isEmpty())) && (!radioElectro.isChecked() && !radioRB.isChecked())) {
            Toast.makeText(Formulario.this, "No tiene ningún campo cubierto", Toast.LENGTH_SHORT).show();
        }
        else if ((!textoAlbum.getText().toString().isEmpty() && (textoArtista.getText().toString().isEmpty()))) {
            Toast.makeText(Formulario.this, "Debe introducir un nombre de artista", Toast.LENGTH_SHORT).show();
        }
        else if((textoAlbum.getText().toString().isEmpty() && (!textoArtista.getText().toString().isEmpty()))) {
            Toast.makeText(Formulario.this, "Debe introducir un nombre de álbum", Toast.LENGTH_SHORT).show();
        }
        else if((textoAlbum.getText().toString().isEmpty() && (textoArtista.getText().toString().isEmpty()))) {
            Toast.makeText(Formulario.this, "Debe de introducir un nombre de álbum y artista", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(Formulario.this, MainActivity.class);
            //Enviamos los datos por el intent para meterlos en el ArrayList luego
            intent.putExtra("album", textoAlbum.getText().toString());
            intent.putExtra("artista", textoArtista.getText().toString());
            startActivity(intent);

        }
    }
}