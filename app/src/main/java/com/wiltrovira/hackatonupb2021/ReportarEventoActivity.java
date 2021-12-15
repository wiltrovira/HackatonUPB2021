package com.wiltrovira.hackatonupb2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.sql.SQLException;

import application.HackatonUPB2021Application;
import datos.ContactoDaoSQLite;
import datos.MiHistorialDaoSQLite;
import domain.ContactoDTO;
import domain.MiHistorialBuilder;
import domain.MiHistorialDTO;

public class ReportarEventoActivity extends AppCompatActivity {


    ImageView btnAtras;
    EditText etUbicacion, etUbicacionDescripcion;
    TextView tvCategoriaEvento;
    Button btnReportarEvento;

    //
    //Lee las preferencias del usuario
    SharedPreferences preferenciasUsuario = HackatonUPB2021Application.getPreferenciasUsuario();

    String paramCategoriaEvento = "";
    String paramModoEditarAgregar = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar_evento);

        //Recupera los datos del usuario guardado en sus preferencias
        String preferenciaUsername = preferenciasUsuario.getString("preferenciaUsername", "");
        int preferenciaIdUsuario = preferenciasUsuario.getInt("preferenciaIdUsuario", -1);

        btnAtras = findViewById(R.id.img_Back);
        tvCategoriaEvento = findViewById(R.id.tv_categoriaEvento);
        btnReportarEvento = findViewById(R.id.btn_reportarEvento);
        etUbicacion = findViewById(R.id.et_ubicacion);
        etUbicacionDescripcion = findViewById(R.id.et_ubicacionDescripcion);

        //Información que viene del formulario HomeActivity
        Intent eventCategoryIntent = getIntent();
        if (eventCategoryIntent != null) {
            paramCategoriaEvento = eventCategoryIntent.getStringExtra("paramCategoriaEvento"); //Categoría o evento reportado
            paramModoEditarAgregar = eventCategoryIntent.getStringExtra("paramModoEditarAgregar");
            tvCategoriaEvento.setText(paramCategoriaEvento);
        }

        /*
        Botón Reportar evento
         */
        btnReportarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Lee las preferencias del usuario
                boolean preferenciaEstaSesionIniciada = preferenciasUsuario.getBoolean("preferenciaIsLoggedIn", false);
                if (!preferenciaEstaSesionIniciada) {
                    Toast.makeText(v.getContext(),
                            "Debe iniciar sesión para poder reportar eventos",
                            Toast.LENGTH_LONG).show();

                    //Lo envía a la ventana de incio de sesión
                    Intent loginIntent = new Intent(ReportarEventoActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                    return;
                }

                //Datos de usuario y password
                String ubicacion = etUbicacion.getText().toString();
                String ubicacionDescripcion = etUbicacionDescripcion.getText().toString();

                //Valida la información ingresada por el usuario
                if (ubicacion.equals("") && ubicacionDescripcion.equals("")) {
                    Toast.makeText(v.getContext(),
                            "Por favor, indique su ubicación o describa cómo llegar a usted",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                //Objeto que se conecta a la base de datos
                MiHistorialBuilder miHistorialBuilder = new MiHistorialBuilder();
                miHistorialBuilder
                        .setIdUsuario(preferenciaIdUsuario)
                        .setFechaReporte(System.currentTimeMillis() / 1000L)
                        .setCategoriaEvento(paramCategoriaEvento)
                        .setUbicacion(ubicacion)
                        .setUbicacionDescripcion(ubicacionDescripcion);

                //Realiza la inserción
                long operacionExitosa = 0;
                MiHistorialDTO miHistorialDTO = miHistorialBuilder.getMiHistorialDTO();
                MiHistorialDaoSQLite miHistorialDao = new MiHistorialDaoSQLite(ReportarEventoActivity.this, miHistorialDTO);
                try {
                    if (paramModoEditarAgregar.equals("AGREGAR")) {
                        operacionExitosa = miHistorialDao.insertOne(miHistorialDTO);
                    } else {
                        operacionExitosa = miHistorialDao.updateOne(miHistorialDTO);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                //Si la inserción es exitosa, lo lleva a la pantalla de inicio de sesión
                if (operacionExitosa > 0) {
                    Toast.makeText(v.getContext(),
                            "Evento reportado",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(v.getContext(),
                            "Hubo un error al guardar el evento reportado",
                            Toast.LENGTH_LONG).show();
                }

                Intent miHistorialIntent = new Intent(ReportarEventoActivity.this, MiHistorialActivity.class);
                startActivity(miHistorialIntent);
            }
        });

        /*
        Botón atrás
         */
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(ReportarEventoActivity.this, HomeActivity.class);
                startActivity(homeIntent);
            }
        });

    }
}