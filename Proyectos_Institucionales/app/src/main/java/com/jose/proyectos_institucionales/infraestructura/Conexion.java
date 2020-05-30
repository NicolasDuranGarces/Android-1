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
                "porcentajeDesarrollado integer" +
                ")"
        );
        db.execSQL("create table cargo(" +
                "id integer primary key autoincrement, " +
                "idProyecto integer references proyecto on delete cascade, " +
                "nombre text, " +
                "descripcion text, " +
                "horario text, " +
                "salario integer" +
                ")"
        );
        db.execSQL("create table integrante(" +
                "id integer primary key autoincrement, " +
                "idProyecto integer references proyecto on delete cascade, " +
                "idUsuario integer references usuario on delete cascade, " +
                "idCargo integer references cargo on delete cascade, " +
                "unique (idProyecto, idUsuario, idCargo)" +
                ")"
        );
        db.execSQL("create table actividad(" +
                "id integer primary key autoincrement, " +
                "idProyecto integer references proyecto on delete cascade, " +
                "nombre text, " +
                "descripcion text, " +
                "idResponsable integer references usuario on delete cascade, " +
                "fechaInicio text, " +
                "fechaFin text, " +
                "porcentajeDesarrollado integer" +
                ")"
        );
        db.execSQL("create table tarea(" +
                "id integer primary key autoincrement, " +
                "idActividad integer references actividad on delete cascade, " +
                "nombre text, " +
                "descripcion text, " +
                "fechaInicio text, " +
                "fechaFin text, " +
                "porcentajeDesarrollado integer" +
                ")"
        );
        db.execSQL("create table recurso(" +
                "id integer primary key autoincrement, " +
                "idProyecto integer references proyecto on delete cascade, " +
                "nombre text, " +
                "cantidad integer, " +
                "descripcion text, " +
                "ubicacion text" +
                ")"
        );
        db.execSQL("create table recurso_tarea(" +
                "id integer primary key autoincrement, " +
                "idTarea integer references tarea on delete cascade, " +
                "idRecurso integer references recurso on delete cascade," +
                "unique (idTarea, idRecurso)" +
                ")"
        );
        db.execSQL("create table reunion(" +
                "id integer primary key autoincrement, " +
                "idProyecto integer references proyecto on delete cascade, " +
                "coordenadaLatitud text, " +
                "coordenadaLongitud text, " +
                "sitio text, " +
                "tematica text" +
                ")"
        );
        db.execSQL("create table comentario(" +
                "id integer primary key autoincrement, " +
                "idActividad integer references actividad on delete cascade, " +
                "titulo text, " +
                "observacion text" +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists usuario");
        db.execSQL("drop table if exists proyecto");
        db.execSQL("drop table if exists cargo");
        db.execSQL("drop table if exists integrante");
        db.execSQL("drop table if exists actividad");
        db.execSQL("drop table if exists tarea");
        db.execSQL("drop table if exists recurso");
        db.execSQL("drop table if exists recurso_tarea");
        db.execSQL("drop table if exists reunion");
        db.execSQL("drop table if exists comentario");
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
