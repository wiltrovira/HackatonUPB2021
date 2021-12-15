package com.wiltrovira.hackatonupb2021;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import datos.ReporteEstadoAdapter;
import domain.ReporteEstadoDTO;

public class ReporteEstadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.reporte_estado_activity);

        // Create a fake list of earthquake locations.
        List<ReporteEstadoDTO> reportesEstado = new ArrayList<>();

        reportesEstado.add(new ReporteEstadoDTO(7, "Feb 2, 2016", 1, String.valueOf(ReporteEstadoDTO.CATEGORIA_EVENTOS.DERRUMBRES)));
        reportesEstado.add(new ReporteEstadoDTO(7, "Feb 2, 2016", 2, String.valueOf(ReporteEstadoDTO.CATEGORIA_EVENTOS.TERREMOTOS)));
        reportesEstado.add(new ReporteEstadoDTO(7, "Feb 2, 2016", 3, String.valueOf(ReporteEstadoDTO.CATEGORIA_EVENTOS.VOLCANES)));
        reportesEstado.add(new ReporteEstadoDTO(7, "Feb 2, 2016", 4, String.valueOf(ReporteEstadoDTO.CATEGORIA_EVENTOS.INUNDACIONES)));
        reportesEstado.add(new ReporteEstadoDTO(7, "Feb 2, 2016", 5, String.valueOf(ReporteEstadoDTO.CATEGORIA_EVENTOS.OTROS_EVENTOS)));
        reportesEstado.add(new ReporteEstadoDTO(7, "Feb 2, 2016", 6, String.valueOf(ReporteEstadoDTO.CATEGORIA_EVENTOS.TSUNAMIS)));
        reportesEstado.add(new ReporteEstadoDTO(7, "Feb 2, 2016", 7, String.valueOf(ReporteEstadoDTO.CATEGORIA_EVENTOS.TERREMOTOS)));
        reportesEstado.add(new ReporteEstadoDTO(7, "Feb 2, 2016", 8, String.valueOf(ReporteEstadoDTO.CATEGORIA_EVENTOS.INCENDIOS)));
        reportesEstado.add(new ReporteEstadoDTO(7, "Feb 2, 2016", 9, String.valueOf(ReporteEstadoDTO.CATEGORIA_EVENTOS.DERRUMBRES)));
        reportesEstado.add(new ReporteEstadoDTO(7, "Feb 2, 2016", 10, String.valueOf(ReporteEstadoDTO.CATEGORIA_EVENTOS.HURACANES)));

        // Find a reference to the {@link ListView} in the layout
        ListView reporteEstadoListView = findViewById(R.id.lv_reporteEstado);

        // Create a new {@link ArrayAdapter} of earthquakes
        final ReporteEstadoAdapter reporteEstadoAdapter = new ReporteEstadoAdapter(this, reportesEstado);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        reporteEstadoListView.setAdapter(reporteEstadoAdapter);

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
