package com.example.openlab.strooperapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button jugar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        jugar=(Button) findViewById(R.id.btnPlay);
        //agrego la escucha cuando de click en el boton realice una tarea especifica, el cual
        //recibe un parametro de tipo onClickListener quien tiene acceso a ese parametro
       /* jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this,StrooperActivity.class);
                startActivity(i);
            }
        });*/
    }
}
