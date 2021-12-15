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

import application.HackatonUPB2021Application;

public class ReportarEventoActivity extends AppCompatActivity {


    ImageView btnAtras;
    EditText etUbicacion, etUbicacionDescripcion;
    TextView tvCategoriaEvento;
    Button btnReportarEvento;

//
    //Lee las preferencias del usuario
    SharedPreferences preferenciasUsuario = HackatonUPB2021Application.getPreferenciasUsuario();

    String paramCategoriaEvento = "";

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

        //Información que viene del formulario HomeActivity
        Intent eventCategoryIntent = getIntent();
        if (eventCategoryIntent != null) {
            paramCategoriaEvento = eventCategoryIntent.getStringExtra("paramCategoriaEvento"); //Categoría o evento reportado
            tvCategoriaEvento.setText(paramCategoriaEvento);
        }

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
                if (ubicacion.equals("") || ubicacionDescripcion.equals("")) {
                    Toast.makeText(v.getContext(),
                            "Por su tranquilidad, recomendamos diligenciar todos los datos",
                            Toast.LENGTH_LONG).show();
                    return;
                }

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