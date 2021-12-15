package com.wiltrovira.hackatonupb2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.List;

import application.HackatonUPB2021Application;
import datos.ContactoDaoSQLite;
import datos.MisContactosAdapter;
import domain.ContactoDTO;

public class MisContactosActivity extends AppCompatActivity {

    //Lee las preferencias del usuario
    SharedPreferences preferenciasUsuario = HackatonUPB2021Application.getPreferenciasUsuario();

    ImageView btnAtras;
    Button btnAgregarContacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_contactos);

        btnAtras = findViewById(R.id.img_Back);
        btnAgregarContacto = findViewById(R.id.btn_agregarContacto);

        //Recupera los datos del usuario guardado en sus preferencias
        String preferenciaUsername = preferenciasUsuario.getString("preferenciaUsername", "");
        int preferenciaIdUsuario = preferenciasUsuario.getInt("preferenciaIdUsuario", -1);

        // Create a fake list of earthquake locations.
        List<ContactoDTO> misContactos = null;
        try {
            misContactos = new ContactoDaoSQLite(MisContactosActivity.this, null).selectAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Find a reference to the {@link ListView} in the layout
        ListView misContactosListView = findViewById(R.id.lv_misContactos);

        // Create a new {@link ArrayAdapter} of earthquakes
        final MisContactosAdapter misContactosAdapter = new MisContactosAdapter(this, misContactos);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        misContactosListView.setAdapter(misContactosAdapter);


        /*
        Botón agregar contacto
         */
        btnAgregarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent editarContactoIntent = new Intent(MisContactosActivity.this, EditarContactoActivity.class);

                //Envía parámetros a la vista
                editarContactoIntent.putExtra("paramModoEditarAgregar", "AGREGAR");
                startActivity(editarContactoIntent);

            }
        });

        /*
        Botón atrás
         */
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent homeIntent = new Intent(MisContactosActivity.this, HomeActivity.class);
                startActivity(homeIntent);
            }
        });

        //Clic en la lista
        misContactosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                // Find the current earthquake that was clicked on
//                ReporteEstadoDTO currentEarthquake = reporteEstadoAdapter.getItem(position);
//
//                // Convert the String URL into a URI object (to pass into the Intent constructor)
//                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());
//
//                Intent webIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
//                startActivity(webIntent);
            }
        });

    }
}