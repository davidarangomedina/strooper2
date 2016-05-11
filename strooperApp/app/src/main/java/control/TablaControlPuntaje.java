package control;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import datos.DatabaseHandler;
import model.Puntaje;

/**
 * Created by openlab1 on 11/05/2016.
 */
public class TablaControlPuntaje extends DatabaseHandler{


    public TablaControlPuntaje(Context context){
        super(context);

    }

    public boolean registrarPuntaje (Puntaje objectPuntaje){
        ContentValues values = new ContentValues();

        values.put("idJugador",objectPuntaje.getIdJugador());
        values.put("aciertos",objectPuntaje.getAciertos());
        values.put("intentos",objectPuntaje.getIntentos());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean fueRegistrado = db.insert("puntaje",null,values) > 0;

        db.close();

        return fueRegistrado;
    }
}
