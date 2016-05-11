package datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by openlab on 29/04/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    //nombre BD
    private static final String DATABASE_NAME = "strooper";
    //Nombre tablas
    private static final String TABLA_JUGADOR ="jugador";
    private static final String TABLA_PUNTAJE ="puntaje";

    private static final String CREAR_TABLA_JUGADOR ="CREATE TABLE "+TABLA_JUGADOR+
            "( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre TEXT, " +
            "nick TEXT, "+
            "clave TEXT);";


    private static final String CREAR_TABLA_PUNTAJE = "CREATE TABLE "+TABLA_PUNTAJE+
            "( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "idJugador INTEGER, " +
            "aciertos INTEGER, " +
            "intentos INTEGER);";

    public DatabaseHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void onCreate (SQLiteDatabase db){

        db.execSQL(CREAR_TABLA_JUGADOR);
        db.execSQL(CREAR_TABLA_PUNTAJE);

    }

    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS "+TABLA_JUGADOR);
        db.execSQL("DROP TABLE IF EXISTS "+TABLA_PUNTAJE);
        onCreate(db);
    }
}
