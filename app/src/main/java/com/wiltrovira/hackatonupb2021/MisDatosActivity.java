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

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.SQLException;

import application.HackatonUPB2021Application;
import datos.UsuarioDaoSQLite;
import domain.UsuarioBuilder;
import domain.UsuarioDTO;

public class MisDatosActivity extends AppCompatActivity {
    Button btnActualizarDatos;
    ImageView btnAtras;
    EditText etNombre, etApellido, etCorreo, etTelefono;

    //Lee las preferencias del usuario
    SharedPreferences preferenciasUsuario = HackatonUPB2021Application.getPreferenciasUsuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_datos);

        //Controles del formulario
        btnActualizarDatos = findViewById(R.id.btn_actualizarDatos);
        btnAtras = findViewById(R.id.img_Back);
        etNombre = findViewById(R.id.et_nombre);
        etApellido = findViewById(R.id.et_apellido);
        etCorreo = findViewById(R.id.et_correoElectronico);
        etTelefono = findViewById(R.id.et_telefono);


        cargarDatosFormulario();

        //Recupera los datos del usuario guardado en sus preferencias
        String preferenciaUsername = preferenciasUsuario.getString("preferenciaUsername", "");
        int preferenciaIdUsuario = preferenciasUsuario.getInt("preferenciaIdUsuario", -1);
//                String preferenciaNombre = preferenciasUsuario.getString("preferenciaNombre", "");
//                String preferenciaApellido = preferenciasUsuario.getString("preferenciaNombre", "");
//                String preferenciaCorreoElectronico = preferenciasUsuario.getString("preferenciaCorreoElectronico", "");
//                String preferenciaTelefono = preferenciasUsuario.getString("preferenciaTelefono", "");


        /*
        Botón actualizar datos
         */
        btnActualizarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Lee las preferencias del usuario
                boolean preferenciaEstaSesionIniciada = preferenciasUsuario.getBoolean("preferenciaIsLoggedIn", false);
                if (!preferenciaEstaSesionIniciada) {
                    Toast.makeText(v.getContext(),
                            "Debe iniciar sesión para poder ingresar sus datos",
                            Toast.LENGTH_LONG).show();

                    //Lo envía a la ventana de incio de sesión
                    Intent loginIntent = new Intent(MisDatosActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                    return;
                }

                //Datos de usuario y password
                String nombre = etNombre.getText().toString();
                String apellido = etApellido.getText().toString();
                String correoElectronico = etCorreo.getText().toString();
                String telefono = etTelefono.getText().toString();

                //Valida la información ingresada por el usuario
                if (nombre.equals("") || apellido.equals("") || correoElectronico.equals("") || telefono.equals("")) {
                    Toast.makeText(v.getContext(),
                            "Por su tranquilidad, recomendamos diligenciar todos los datos",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                //Objeto que se conecta a la base de datos
                UsuarioBuilder usuarioBuilder = new UsuarioBuilder();
                usuarioBuilder
                        .setIdUsuario(preferenciaIdUsuario)
                        .setUserName(preferenciaUsername)
                        .setNombre(nombre)
                        .setApellido(apellido)
                        .setCorreoElectronico(correoElectronico)
                        .setTelefono(telefono);

                //Realiza la inserción
                long edicionExitosa = 0;
                UsuarioDTO usuarioDTO = usuarioBuilder.getUsuarioDTO();
                UsuarioDaoSQLite usuarioDao = new UsuarioDaoSQLite(MisDatosActivity.this, usuarioDTO);
                try {
                    edicionExitosa = usuarioDao.updateOne(usuarioDTO);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                //Si la inserción es exitosa, lo lleva a la pantalla de inicio de sesión
                if (edicionExitosa > 0) {
                    Toast.makeText(v.getContext(),
                            "Datos actualizados correctamente",
                            Toast.LENGTH_LONG).show();

                    Intent homeIntent = new Intent(MisDatosActivity.this, HomeActivity.class);
                    startActivity(homeIntent);
                } else {
                    Toast.makeText(v.getContext(),
                            "Hubo un error al actualizar tus datos. Inténtalo nuevamente.",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        /*
        Botón atrás
         */
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent homeIntent = new Intent(MisDatosActivity.this, HomeActivity.class);
                startActivity(homeIntent);
            }
        });
    }

    /**
     * Carga los datos del usuario en el formulario
     */
    private void cargarDatosFormulario() {
        //Recupera los datos del usuario guardado en sus preferencias
        String preferenciaUsername = preferenciasUsuario.getString("preferenciaUsername", "");

        UsuarioBuilder usuarioBuilder = new UsuarioBuilder();
        usuarioBuilder
                .setUserName(preferenciaUsername);

        UsuarioDTO usuarioDTO = usuarioBuilder.getUsuarioDTO();
        UsuarioDaoSQLite usuarioDao = new UsuarioDaoSQLite(MisDatosActivity.this, usuarioDTO);

        //Busca los datos del usuario en la base de datos
        UsuarioDTO miUsuario = null;
        try {
            miUsuario = usuarioDao.selectByUsername(preferenciaUsername);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (miUsuario != null) {
                etNombre.setText(miUsuario.getNombre());
                etApellido.setText(miUsuario.getApellido());
                etCorreo.setText(miUsuario.getCorreoElectronico());
                etTelefono.setText(miUsuario.getTelefono());
            }
        }

        //Datos de usuario y password
//        String nombre = etNombre.getText().toString();
//        String apellido = etApellido.getText().toString();
//        String correoElectronico = etCorreo.getText().toString();
//        String telefono = etTelefono.getText().toString();

    }
}