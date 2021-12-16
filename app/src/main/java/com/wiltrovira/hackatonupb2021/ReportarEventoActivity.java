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

import java.util.Date;
import java.sql.SQLException;

import application.HackatonUPB2021Application;
import datos.MiHistorialDaoSQLite;
import domain.MiHistorialBuilder;
import domain.MiHistorialDTO;

public class ReportarEventoActivity extends AppCompatActivity {


    ImageView btnAtras, btnSalud1, btnSalud4, btnSalud7, btnSalud10;
    EditText etUbicacion, etUbicacionDescripcion;
    TextView tvCategoriaEvento, tvEstadoSaludDescripcion;
    Button btnReportarEvento;

    int estadoSalud=0;

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
        btnSalud1 = findViewById(R.id.iv_salud1);
        btnSalud4 = findViewById(R.id.iv_salud4);
        btnSalud7 = findViewById(R.id.iv_salud7);
        btnSalud10 = findViewById(R.id.iv_salud10);
        tvEstadoSaludDescripcion = findViewById(R.id.tv_estadoSaludDescripcion);


        //Información que viene del formulario HomeActivity
        Intent eventCategoryIntent = getIntent();
        if (eventCategoryIntent != null) {
            paramCategoriaEvento = eventCategoryIntent.getStringExtra("paramCategoriaEvento"); //Categoría o evento reportado
            paramModoEditarAgregar = eventCategoryIntent.getStringExtra("paramModoEditarAgregar");
            tvCategoriaEvento.setText(paramCategoriaEvento);
        }

        /*
        Botón salud 1
         */
        btnSalud1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estadoSalud = 1;
                tvEstadoSaludDescripcion.setText("Me encuentro bien!");
            }
        });

        /*
        Botón salud 4
         */
        btnSalud4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estadoSalud = 4;
                tvEstadoSaludDescripcion.setText("Estoy preocupado, sin lesiones");
            }
        });


        /*
        Botón salud 7
         */
        btnSalud7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estadoSalud = 7;
                tvEstadoSaludDescripcion.setText("Afectación leve.");
            }
        });


        /*
        Botón salud 10
         */
        btnSalud10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estadoSalud = 10;
                tvEstadoSaludDescripcion.setText("Afectación grave. Atención médica urgente.");
            }
        });


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
                        .setFechaReporte(new Date().getTime())
                        .setUbicacion(ubicacion)
                        .setUbicacionDescripcion(ubicacionDescripcion)
                        .setEstadoSalud(estadoSalud)
                        .setCategoriaEvento(paramCategoriaEvento);

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
