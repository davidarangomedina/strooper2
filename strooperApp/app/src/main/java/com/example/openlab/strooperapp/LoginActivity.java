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

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import control.TablaControlJugador;
import model.Jugador;

public class LoginActivity extends AppCompatActivity {

    @InjectView(R.id.input_nick) EditText nickText;
    @InjectView(R.id.input_password) EditText passwordText;
    @InjectView(R.id.btn_login) Button loginButton;
    @InjectView(R.id.link_signup) TextView signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                final Context context = view.getContext();

                login(context);
            }
        });

        //Me lleva a SingUpActivity
        signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SingUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public void login(Context context) {

        if (!validate()) {
            Toast.makeText(context, "Digite email y clave", Toast.LENGTH_SHORT).show();
            return;
        }

       // loginButton.setEnabled(false);


        String nick = nickText.getText().toString();
        String clave = passwordText.getText().toString();

        // TODO: Validacion email y clave


        Jugador j= new Jugador();
        j.setNick(nick);
        j.setClave(clave);

        List<Jugador> jugador = new TablaControlJugador(this).acceder(j);

        String nickDB ="";
        String claveDB ="";

        if (jugador.size() > 0) {

            for (Jugador obj : jugador) {
                nickDB = obj.getNick();
                claveDB = obj.getClave();
            }

        }
        if (nickDB.equals(j.getNick()) && claveDB.equals(j.getClave())) {
            Toast.makeText(context, "Bienvenido " + nickDB, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(LoginActivity.this, StrooperActivity.class);
            i.putExtra("nick",nickDB);
            startActivity(i);
        } else {
            Toast.makeText(context, "nick o password invalido", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean validate() {
        boolean valid = true;

        String nick = nickText.getText().toString();
        String password = passwordText.getText().toString();

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
