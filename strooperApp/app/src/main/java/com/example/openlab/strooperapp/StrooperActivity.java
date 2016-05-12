package com.example.openlab.strooperapp;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import control.TablaControlJugador;
import control.TablaControlPuntaje;
import model.Jugador;
import model.Puntaje;


public class StrooperActivity extends AppCompatActivity {

    TextView txtSegundos,txtNick,txtPuntaje, txtColor;
   //EditText nick2;
    ImageView btnTrue, btnFalse;






    String vector_colores[]={"Azul","Verde","Rojo","Amarillo"}, colorPalabra="";

    int correctos=0,intentos=0;

    String nick="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strooper);

        inicializarCampos();
        btnFalse = (ImageView) findViewById(R.id.btnFlase);
        btnTrue = (ImageView) findViewById(R.id.btnTrue);

    }

    public void enableBotones(boolean sw){
        btnTrue.setEnabled(sw);
        btnFalse.setEnabled(sw);
    }

    public void BotonTrue(View view){
        if(txtColor.getText().equals(colorPalabra)){
            Toast.makeText(getBaseContext(), "Correcto", Toast.LENGTH_SHORT).show();
            correctos++;
        }else{
            Toast.makeText(getBaseContext(), "Incorrecto", Toast.LENGTH_SHORT).show();
        }
        enableBotones(false);
    }

    public void BotonFalse(View view){
        if(!txtColor.getText().equals(colorPalabra)){
            Toast.makeText(getBaseContext(), "Correcto", Toast.LENGTH_SHORT).show();
            correctos++;
        }else{
            Toast.makeText(getBaseContext(), "Incorrecto", Toast.LENGTH_SHORT).show();
        }
        enableBotones(false);
    }

    private void inicializarCampos(){
        txtNick = (TextView) findViewById(R.id.txtNick);
        txtPuntaje = (TextView) findViewById(R.id.txtPuntaje);
        txtSegundos = (TextView) findViewById(R.id.txtSegundos);
        txtColor = (TextView) findViewById(R.id.txtColor);

       Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            nick = bundle.getString("nick");
            txtNick.setText(nick);
        }else {
            txtNick.setText("Fail");
        }

        //String nick = getIntent().getStringExtra("nick");
        //txtNick.setText(nick);

       // int idJugador = Integer.parseInt(getIntent().getIntExtra("id"));



        txtSegundos.setText("Jugar Ahora");
    }

    public void jugarAhora(View view){

        Context context = view.getContext();

        if(txtSegundos.getText().toString().equalsIgnoreCase("Jugar Ahora")){
            cambiarColorTexto(txtColor);
            cambiarTexto(txtColor);
            inicarCuentaAtras();
        }
    }

    private void almacenarPuntajes() {
        // TODO: Capturar el ID del jugador
        Jugador j= new Jugador();
        j.setNick(nick);

        List<Jugador> jugador = new TablaControlJugador(this).acceder(j);

        int idJugador =0;

        if (jugador.size() > 0) {

            for (Jugador obj : jugador) {
                idJugador = obj.getId();
            }

        }

        // TODO: Insertar Puntajes


        Puntaje puntajes = new Puntaje();

        puntajes.setIdJugador(idJugador);
        puntajes.setAciertos(correctos);
        puntajes.setIntentos(intentos);

        boolean fueRegistrado = new TablaControlPuntaje(getApplicationContext()).registrarPuntaje(puntajes);

        if(fueRegistrado){
            Toast.makeText(getApplicationContext(),"Puntajes guardados", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getApplicationContext(), "Erros Puntajes",Toast.LENGTH_SHORT).show();
        }

    }

    CountDownTimer counter;
    int segundos =3;
    public void inicarCuentaAtras(){
        enableBotones(true);
        intentos++;
        txtPuntaje.setText(correctos+"");
        txtPuntaje.setText(correctos+" correctos de "+intentos+" intentos");
        txtPuntaje.setText(correctos+" correctos de "+intentos+" intentos");
        counter = new CountDownTimer(5*1000, 1000) {
            @Override
            public void onTick(long miliSegundos) {
                segundos = (int) (miliSegundos / 1000);

                //Modificado por David A.
                if(!txtSegundos.getText().equals("Juego Finalizado")){
                    //cambiarColorTexto(txtColor);
                    //cambiarTexto(txtColor);
                    txtSegundos.setText("" + (segundos - 1));

                }
            }

            @Override
            public void onFinish() {
                //enableBotones(true);
                if(intentos<10){
                    inicarCuentaAtras();
                    cambiarColorTexto(txtColor);
                    cambiarTexto(txtColor);
                }


                if(intentos==10){
                    txtSegundos.setText("Juego Finalizado");
                    almacenarPuntajes();
                }
                Toast.makeText(getApplicationContext(),""+intentos,Toast.LENGTH_SHORT).show();
            }
        };

        counter.start();
        Toast.makeText(getApplicationContext(),"Terminó",Toast.LENGTH_SHORT).show();
    }

    //Creado por David A.
    public void cambiarColorTexto(TextView colortexto){
        int max=4;
        switch (new Random().nextInt(max)){
            case 0: txtColor.setTextColor(getResources().getColor(R.color.verde));
                colorPalabra="Verde";
                break;
            case 1: txtColor.setTextColor(getResources().getColor(R.color.azul));
                colorPalabra="Azul";
                break;
            case 2: txtColor.setTextColor(getResources().getColor(R.color.amarillo));
                colorPalabra="Amarillo";
                break;
            case 3: txtColor.setTextColor(getResources().getColor(R.color.rojo));
                colorPalabra="Rojo";
                break;
        }

    }

    //Creado por David A.
    public void cambiarTexto(TextView texto){
        texto.setText(vector_colores[new Random().nextInt(4)]);
    }

    public void home(View view){
        Intent i = new Intent(StrooperActivity.this, MenuActivity.class);
        startActivity(i);
    }

    public void finalizar(View view){
        this.finish();
    }
}
  /*  public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String tiempo = String.format("%02d", TimeUnit.MILLISECONDS.toSeconds(millis));
            counter.setText(tiempo);
        }

        @Override
        public void onFinish() {
            counter.setText("Finalizado");
        }
    }*/

