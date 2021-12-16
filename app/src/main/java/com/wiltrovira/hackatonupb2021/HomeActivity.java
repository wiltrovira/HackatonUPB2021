package com.wiltrovira.hackatonupb2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import Utilities.Validaciones;
import application.HackatonUPB2021Application;

public class HomeActivity extends AppCompatActivity {

    //Datos de la aplicación que se comparten de manera global
    HackatonUPB2021Application hackatonUPB2021Application = (HackatonUPB2021Application) HackatonUPB2021Application.getAppContext();


    //Lee las preferencias del usuario
    SharedPreferences preferenciasUsuario = HackatonUPB2021Application.getPreferenciasUsuario();

    Button btnReportarEvento;
    ImageButton btnWhatsapp, btnLogout, btnEstadoSalud, btnMisDatos, btnMisContactos;
    ImageView imgAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnWhatsapp = findViewById(R.id.btn_whatsapp);
        btnLogout = findViewById(R.id.btn_logout);
        imgAtras = findViewById(R.id.img_Back);
        btnReportarEvento = findViewById(R.id.btn_categoriaEvento);
        btnEstadoSalud = findViewById(R.id.btn_estadoSalud);
        btnMisDatos = findViewById(R.id.btn_misDatos);
        btnMisContactos = findViewById(R.id.btn_misContactos);


        /**
         * Botón Mis contactos
         */
        btnMisContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent misContactosIntent = new Intent(HomeActivity.this, MisContactosActivity.class);
                startActivity(misContactosIntent);
            }
        });


        /**
         * Botón Mis datos
         */
        btnMisDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent misDatosIntent = new Intent(HomeActivity.this, MisDatosActivity.class);
                startActivity(misDatosIntent);
            }
        });

        /**
         * Botón para reportar eventos
         */
        btnReportarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent reportarEventoIntent = new Intent(HomeActivity.this, CategoriasEventoActivity.class);
                startActivity(reportarEventoIntent);

            }
        });

        btnEstadoSalud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent reporteEstadoSaludIntent = new Intent(HomeActivity.this, MiHistorialActivity.class);
                startActivity(reporteEstadoSaludIntent);

            }
        });

        /**
         * Botón Whatsapp
         */
        btnWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje = "Este es un mensaje de prueba desde la app";
                String telefono = "573124501504";

                Intent whatsappIntent = new Intent();
                whatsappIntent.setAction(Intent.ACTION_VIEW);
                String urlWhatsapp = "https://api.whatsapp.com/send?phone=" + telefono + "&text=" + mensaje;
                Uri whatsappUri = Uri.parse(urlWhatsapp);
                whatsappIntent.setData(whatsappUri);

                PackageManager pm = HomeActivity.this.getPackageManager();
                if (Validaciones.estaInstaladaAplicacion("com.whatsapp", getApplicationContext())){
                    String nombrePaquete = "com.whatsapp";
                    Intent intent = getPackageManager().getLaunchIntentForPackage(nombrePaquete );

                    if(intent == null) {
                        Toast.makeText(
                                HomeActivity.this,
                                "No se pudo abrir",
                                Toast.LENGTH_SHORT)
                                .show();
                        return;                    }
                    startActivity(intent); //Abre aplicacion.

                    startActivity(whatsappIntent);
                } else {
                    Toast.makeText(
                            HomeActivity.this,
                            "Whatsapp no instalado",
                            Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
            }
        });

        /**
         * Botón de cerrar sesión
         */
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cerrarSesión();

                Intent mainIntent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(mainIntent);
            }
        });

        /**
         * Botón Atrás
         */
        imgAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cerrarSesión();

                Intent mainIntent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(mainIntent);
            }
        });
    }

    /**
     * Cerrar sesión: Limpia las preferencias
     */
    private void cerrarSesión() {
        //Guarda las preferencias del usuario
        SharedPreferences.Editor editorPreferencias = preferenciasUsuario.edit();
        editorPreferencias.putInt("preferenciaIdUsuario", -1);
        editorPreferencias.putString("preferenciaUsername", "");
        editorPreferencias.putBoolean("preferenciaIsLoggedIn", false);
        editorPreferencias.commit(); //confirma los cambios
    }

}