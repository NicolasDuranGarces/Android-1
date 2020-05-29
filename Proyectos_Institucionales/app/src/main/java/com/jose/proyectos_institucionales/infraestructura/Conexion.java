package com.jose.proyectos_institucionales.infraestructura;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexion extends SQLiteOpenHelper {

    private static final String database = "parcial_3.db";
    private static final SQLiteDatabase.CursorFactory factory = null;
    private static final int version = 1;
    SQLiteDatabase bd;

    //Constructor para especificar otra db
    public Conexion(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    public Conexion(Context context){
        super(context, database, factory, version);
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
        if(!db.isReadOnly()){
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuario(" +
                "id integer primary key autoincrement, " +
                "tipoDocumentoIdentidad text, " +
                "numeroDocumento text unique, " +
                "nombres text, " +
                "apellidos text, " +
                "fechaNacimiento text, " +
                "clave text, " +
                "email text unique" +
                ")"

        );
        db.execSQL("create table proyecto(" +
                "id integer primary key autoincrement, " +
                "nombre text unique, " +
                "idDirector integer references usuario on delete cascade, " +
                "fechaInicio text, " +
                "fechaFin text, " +
                "etapa text" +
                ")"

        );
        db.execSQL("create table video(" +
                "nombre text primary key, " +

                "idReunion integer references reunion on delete cascade" +
                ")"

        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists reunion");
        db.execSQL("drop table if exists foto");
        db.execSQL("drop table if exists video");
        onCreate(db);
    }

    public void cerrarConexion(){
        bd.close();
    }



    // Métodos DML genéricos

    public boolean ejecutarInsert(String tabla, ContentValues registro){
        try{
            bd = this.getWritableDatabase();

            int res = (int) bd.insert(tabla, null, registro);
            cerrarConexion();
            if (res !=-1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean ejecutarDelete(String tabla, String condicion){
        bd = this.getWritableDatabase();

        int cant = bd.delete(tabla, condicion, null);
        cerrarConexion();
        if (cant >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean ejecutarUpdate(String tabla, String condicion, ContentValues registro){
        try{

            bd = this.getWritableDatabase();
            int cant = bd.update(tabla, registro, condicion, null);
            cerrarConexion();

            if (cant == 1) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

    public Cursor ejecutarSearch(String consulta){
        try {
            bd = this.getWritableDatabase();
            Cursor fila = bd.rawQuery(consulta, null);
            return fila;

        } catch (Exception e){
            cerrarConexion();
            return null;
        }
    }

}
