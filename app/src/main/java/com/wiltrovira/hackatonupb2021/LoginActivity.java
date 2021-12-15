package com.wiltrovira.hackatonupb2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.HackatonUPB2021Application;
import datos.UsuarioDaoSQLite;
import domain.UsuarioDTO;

public class LoginActivity extends AppCompatActivity {

    //Datos de la aplicación que se comparten de manera global
    HackatonUPB2021Application hackatonUPB2021Application = (HackatonUPB2021Application) HackatonUPB2021Application.getAppContext();
//    Context hackatonUPB2021Context = HackatonUPB2021Application.getAppContext();

    private EditText etUserName, etPassword;
    private Button btnLogin;
    private TextView tvCreateAccount;

    //Lee las preferencias del usuario
    SharedPreferences preferenciasUsuario = HackatonUPB2021Application.getPreferenciasUsuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserName = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_Login);
        tvCreateAccount = findViewById(R.id.tv_createAccount);


        //Lee las preferencias del usuario
        boolean preferenciaEstaSesionIniciada = preferenciasUsuario.getBoolean("preferenciaIsLoggedIn", false);
//        String preferenciaUsername = preferenciasUsuario.getString("preferenciaUsername", "");
        if (preferenciaEstaSesionIniciada) {
            Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(homeIntent);
        }

        //Botón Crear cuenta
        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });


        //Botón Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Datos de usuario y password
                String userName = etUserName.getText().toString();
                String password = etPassword.getText().toString();


                //Valida la información ingresada por el usuario
                if (userName.equals("") || password.equals("")) {
                    Toast.makeText(v.getContext(),
                            "Diligencie todos los campos",
                            Toast.LENGTH_LONG).show();
                    return;

                }

                //Objeto que se conecta a la base de datos
                UsuarioDTO usuarioDTO = new UsuarioDTO(userName, password); //Usuario a crear
                UsuarioDaoSQLite usuarioDao = new UsuarioDaoSQLite(LoginActivity.this, usuarioDTO);

                //Valida si el usuario y contraseña son correctos
                boolean usuarioPasswordCorrecto = false;
                try {
                    usuarioPasswordCorrecto = usuarioDao.checkUsernamePassword();
                } catch (NoSuchProviderException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                if (usuarioPasswordCorrecto) {
                    Toast.makeText(v.getContext(),
                            "Login fue exitoso!!!",
                            Toast.LENGTH_LONG).show();

                    //Busca los datos del usuario
                    UsuarioDTO miUsuario = null;
                    try {
                        miUsuario = usuarioDao.selectByUsername(userName);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    if (miUsuario != null) {
                        //Guarda las preferencias del usuario
                        SharedPreferences.Editor editorPreferencias = preferenciasUsuario.edit();
                        editorPreferencias.putInt("preferenciaIdUsuario", miUsuario.getIdUsuario());
                        editorPreferencias.putString("preferenciaUsername", miUsuario.getUserName());
                        editorPreferencias.putBoolean("preferenciaIsLoggedIn", true);
                        editorPreferencias.commit(); //confirma los cambios
                    }
                    Intent homeIntent = new Intent(v.getContext(), HomeActivity.class);
                    startActivity(homeIntent);
                } else {
                    Toast.makeText(v.getContext(),
                            "Usuario y contraseña errados!",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}