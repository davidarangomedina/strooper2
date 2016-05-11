package com.example.openlab.strooperapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import control.TablaControlJugador;
import model.Jugador;

public class SingUpActivity extends AppCompatActivity {

    @InjectView(R.id.input_name) EditText nameText;
    @InjectView(R.id.input_nick) EditText nickText;
    @InjectView(R.id.input_password) EditText passwordText;
    @InjectView(R.id.btn_signup) Button signupButton;
    @InjectView(R.id.link_login) TextView loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        ButterKnife.inject(this);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context = view.getContext();

                signup(context);
            }
        });

        //Me lleva a LoginActivity
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void signup(Context context) {;

        if (!validate()) {
            Toast.makeText(context, "Digite email y clave",Toast.LENGTH_SHORT).show();
            return;
        }

        signupButton.setEnabled(false);

        String nombre = nameText.getText().toString();
        String nick = nickText.getText().toString();
        String clave = passwordText.getText().toString();

        // TODO: Insertar en la Base de Datos

        Jugador jugador = new Jugador();

        jugador.setNombre(nombre);
        jugador.setNick(nick);
        jugador.setClave(clave);

        boolean fueRegistrado = new TablaControlJugador(context).registrarJugador(jugador);

        if(fueRegistrado){
            Toast.makeText(context,"Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(SingUpActivity.this, LoginActivity.class);
            startActivity(i);

        }else{
            Toast.makeText(context, "Ya existe un usuario con ese nick",Toast.LENGTH_SHORT).show();
        }

    }


    public boolean validate() {
        boolean valid = true;

        String name = nameText.getText().toString();
        String nick = nickText.getText().toString();
        String password = passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            nameText.setError("Debe tener más de tres caracteres");
            valid = false;
        } else {
            nameText.setError(null);
        }

        if (nick.isEmpty() || nick.length() < 3) {
            nickText.setError("Debe tener más de tres caracteres");
            valid = false;
        } else {
            nickText.setError(null);
        }


        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("Digite un texto entre 4 y 10");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }
}
