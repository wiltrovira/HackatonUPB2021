package com.wiltrovira.hackatonupb2021;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.HackatonUPB2021Application;
import datos.ContactoDaoSQLite;
import datos.MiHistorialAdapter;
import datos.MiHistorialDaoSQLite;
import domain.MiHistorialDTO;

public class MiHistorialActivity extends AppCompatActivity {

    //Lee las preferencias del usuario
    SharedPreferences preferenciasUsuario = HackatonUPB2021Application.getPreferenciasUsuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_historial);

        //Recupera los datos del usuario guardado en sus preferencias
        String preferenciaUsername = preferenciasUsuario.getString("preferenciaUsername", "");
        int preferenciaIdUsuario = preferenciasUsuario.getInt("preferenciaIdUsuario", -1);

        //Crear una lista de reportes
        List<MiHistorialDTO> reportesEstado = null;
        try {
            reportesEstado = new MiHistorialDaoSQLite(MiHistorialActivity.this, null).selectAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Find a reference to the {@link ListView} in the layout
        ListView reporteEstadoListView = findViewById(R.id.lv_miHistorial);

        // Create a new {@link ArrayAdapter} of earthquakes
        final MiHistorialAdapter miHistorialAdapter = new MiHistorialAdapter(this, reportesEstado);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        reporteEstadoListView.setAdapter(miHistorialAdapter);

        //Clic en la lista
        reporteEstadoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
