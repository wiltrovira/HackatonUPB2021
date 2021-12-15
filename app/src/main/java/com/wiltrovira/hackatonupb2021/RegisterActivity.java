package com.wiltrovira.hackatonupb2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import domain.UsuarioDTO;

public class RegisterActivity extends AppCompatActivity {
    private EditText etUserName, etPassword, etRePassword;
    private Button btnCreateAccount;
    private TextView tvLogin;
    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUserName = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etRePassword = findViewById(R.id.et_rePassword);
        btnCreateAccount = findViewById(R.id.btn_createAccount);
        tvLogin = findViewById(R.id.tv_createAccount);
        imgBack = findViewById(R.id.img_Back);


        /**
         * Botón atrás
         */
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(backIntent);
            }
        });


        /**
         * Botón Crear cuenta
         */
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),
                        "Creando cuenta...",
                        Toast.LENGTH_LONG).show();

                //Datos ingresados por el usuario en el formulario de creación de cuentas
                String userName = etUserName.getText().toString();
                String password = etPassword.getText().toString();
                String rePassword = etRePassword.getText().toString();

                //Valida la información ingresada por el usuario
                if (userName.equals("") || password.equals("") || rePassword.equals("")) {
                    Toast.makeText(v.getContext(),
                            "Diligencie todos los campos",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                //Valida si el password y el rePassword son iguales
                if (!password.equals(rePassword)) {
                    Toast.makeText(v.getContext(),
                            "Las contraseñas no coinciden.",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                //Objeto que se conecta a la base de datos
                UsuarioDTO usuarioDTO = new UsuarioDTO(userName, password); //Usuario a crear
                UsuarioDaoSQLite usuarioDao = new UsuarioDaoSQLite(RegisterActivity.this, usuarioDTO);

                //Valida si existe el usuario
                boolean existeUsuario = false;
                try {
                    existeUsuario = usuarioDao.checkUsername();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                //Si el usuario no existe, procede a crear el registro
                if (existeUsuario) {
                    Toast.makeText(v.getContext(),
                            "No se pudo crear el usuario. Ya existe en la base de datos.",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                //Realiza la inserción
                long insercionExitosa = 0;
                try {
                    insercionExitosa = usuarioDao.insertOne(usuarioDTO);
                } catch (NoSuchProviderException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                //Si la inserción es exitosa, lo lleva a la pantalla de inicio de sesión
                if (insercionExitosa > 0) {
                    Toast.makeText(v.getContext(),
                            "Usuario creado. Ya puede iniciar sesión",
                            Toast.LENGTH_LONG).show();

                    Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                } else {
                    Toast.makeText(v.getContext(),
                            "Hubo un error al crear el usuario.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        /**
         * Enlace Iniciar sesión
         */
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(),
                        "Login...",
                        Toast.LENGTH_LONG).show();

                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });
    }
}
