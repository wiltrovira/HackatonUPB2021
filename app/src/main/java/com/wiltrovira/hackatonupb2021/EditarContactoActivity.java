package com.wiltrovira.hackatonupb2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.SQLException;

import application.HackatonUPB2021Application;
import datos.ContactoDaoSQLite;
import domain.ContactoBuilder;
import domain.ContactoDTO;

public class EditarContactoActivity extends AppCompatActivity {

    ImageView btnAtras;
    Button btnActualizarContacto;
    EditText etNombre, etApellido, etCorreo, etTelefono, etRelacion;

    //Lee las preferencias del usuario
    SharedPreferences preferenciasUsuario = HackatonUPB2021Application.getPreferenciasUsuario();

    String paramModoEditarAgregar = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contacto);

        //Recupera los datos del usuario guardado en sus preferencias
        String preferenciaUsername = preferenciasUsuario.getString("preferenciaUsername", "");
        int preferenciaIdUsuario = preferenciasUsuario.getInt("preferenciaIdUsuario", -1);

        //Información que viene del formulario HomeActivity
        Intent misContactosIntent = getIntent();
        if (misContactosIntent != null) {
            paramModoEditarAgregar = misContactosIntent.getStringExtra("paramModoEditarAgregar");
        }

        //Controles del formulario
        btnActualizarContacto = findViewById(R.id.btn_actualizarContacto);
        btnAtras = findViewById(R.id.img_Back);
        etNombre = findViewById(R.id.et_nombreContacto);
        etApellido = findViewById(R.id.et_apellidoContacto);
        etCorreo = findViewById(R.id.et_correoElectronicoContacto);
        etTelefono = findViewById(R.id.et_telefonoContacto);
        etRelacion = findViewById(R.id.et_relacionContacto);

        /*
        Botón Actualizar contacto
         */
        btnActualizarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lee las preferencias del usuario
                boolean preferenciaEstaSesionIniciada = preferenciasUsuario.getBoolean("preferenciaIsLoggedIn", false);
                if (!preferenciaEstaSesionIniciada) {
                    Toast.makeText(v.getContext(),
                            "Debe iniciar sesión para poder agregar contactos",
                            Toast.LENGTH_LONG).show();

                    //Lo envía a la ventana de incio de sesión
                    Intent loginIntent = new Intent(EditarContactoActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                    return;
                }

                //Datos de usuario y password
                String nombre = etNombre.getText().toString();
                String apellido = etApellido.getText().toString();
                String correoElectronico = etCorreo.getText().toString();
                String telefono = etTelefono.getText().toString();
                String relacion = etRelacion.getText().toString();

                //Valida la información ingresada por el usuario
                if (nombre.equals("") || telefono.equals("")) {
                    Toast.makeText(v.getContext(),
                            "El nombre y el teléfono son obligatorios. Opcional: Apellido, correo y relación",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                //Objeto que se conecta a la base de datos
                ContactoBuilder contactoBuilder = new ContactoBuilder();
                contactoBuilder
                        .setIdUsuario(preferenciaIdUsuario)
                        .setNombre(nombre)
                        .setApellido(apellido)
                        .setCorreoElectronico(correoElectronico)
                        .setTelefono(telefono)
                        .setRelacion(relacion);

                //Realiza la inserción
                long operacionExitosa = 0;
                ContactoDTO contactoDTO = contactoBuilder.getContactoDTO();
                ContactoDaoSQLite contactoDao = new ContactoDaoSQLite(EditarContactoActivity.this, contactoDTO);
                try {
                    if (paramModoEditarAgregar.equals("AGREGAR")) {
                        operacionExitosa = contactoDao.insertOne(contactoDTO);
                    } else {
                        operacionExitosa = contactoDao.updateOne(contactoDTO);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                //Si la inserción es exitosa, lo lleva a la pantalla de inicio de sesión
                if (operacionExitosa > 0) {
                    Toast.makeText(v.getContext(),
                            "Contacto guardado",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(v.getContext(),
                            "Hubo un error al guardar los datos del contacto",
                            Toast.LENGTH_LONG).show();
                }

                Intent misContactosIntent = new Intent(EditarContactoActivity.this, MisContactosActivity.class);
                startActivity(misContactosIntent);
            }
        });

        /*
        Botón atrás
         */
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent homeIntent = new Intent(EditarContactoActivity.this, HomeActivity.class);
                startActivity(homeIntent);
            }
        });
    }
}