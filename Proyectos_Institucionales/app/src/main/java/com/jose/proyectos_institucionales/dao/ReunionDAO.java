package com.jose.proyectos_institucionales.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.jose.proyectos_institucionales.infraestructura.Conexion;
import com.jose.proyectos_institucionales.modelo.Reunion;

import java.util.ArrayList;
import java.util.List;

public class ReunionDAO {

    Conexion conex;

    public ReunionDAO(Activity activity){
        conex = new Conexion(activity);
    }

    public boolean guardar(Reunion reunion){
        ContentValues registro = new ContentValues();
        registro.put("id", reunion.getId());
        registro.put("idProyecto", reunion.getIdProyecto());
        registro.put("coordenadaLatitud", reunion.getCoordenadaLatitud());
        registro.put("coordenadaLongitud", reunion.getCoordenadaLongitud());
        registro.put("sitio", reunion.getSitio());
        registro.put("tematica", reunion.getTematica());

        return conex.ejecutarInsert("reunion", registro);
    }

    public Reunion buscar(Integer id){
        Reunion reunion = null;
        String consulta = "select id, " +
                "idProyecto, " +
                "coordenadaLatitud, " +
                "coordenadaLongitud, " +
                "sitio, " +
                "tematica " +
                "from reunion " +
                "where id= " + id ;
        Cursor temp = conex.ejecutarSearch(consulta);

        if (temp.getCount() > 0){
            temp.moveToFirst();
            reunion = new Reunion(
                    temp.getInt(0),
                    temp.getInt(1),
                    temp.getString(2),
                    temp.getString(3),
                    temp.getString(4),
                    temp.getString(5)
            );
        }
        conex.cerrarConexion();
        return reunion;
    }

    public boolean eliminar(Reunion reunion){
        String tabla = "reunion";
        String condicion = "id = " + reunion.getId();
        return conex.ejecutarDelete(tabla, condicion);
    }

    public boolean modificar(Reunion reunion){
        String tabla = "reunion";
        String condicion = "id = " + reunion.getId();

        ContentValues registro = new ContentValues();
        registro.put("idProyecto", reunion.getIdProyecto());
        registro.put("coordenadaLatitud", reunion.getCoordenadaLatitud());
        registro.put("coordenadaLongitud", reunion.getCoordenadaLongitud());
        registro.put("sitio", reunion.getSitio());
        registro.put("tematica", reunion.getTematica());

        return conex.ejecutarUpdate(tabla, condicion, registro);

    }

    public List<Reunion> listarReunionesProyecto(Integer idProyecto){
        List<Reunion> listaReuniones = new ArrayList<Reunion>();
        String consulta = "select id, " +
                "idProyecto, " +
                "coordenadaLatitud, " +
                "coordenadaLongitud, " +
                "sitio, " +
                "tematica " +
                "from reunion " +
                "where idProyecto= " + idProyecto ;
        Cursor temp = conex.ejecutarSearch(consulta);

        if(temp.moveToFirst()){
            do{
                Reunion reunion = new Reunion(
                        temp.getInt(0),
                        temp.getInt(1),
                        temp.getString(2),
                        temp.getString(3),
                        temp.getString(4),
                        temp.getString(5)
                );
                listaReuniones.add(reunion);
            } while (temp.moveToNext());
        }
        return listaReuniones;
    }
    
}
