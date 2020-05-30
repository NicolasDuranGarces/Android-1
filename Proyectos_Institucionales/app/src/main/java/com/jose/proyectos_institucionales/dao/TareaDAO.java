package com.jose.proyectos_institucionales.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.jose.proyectos_institucionales.infraestructura.Conexion;
import com.jose.proyectos_institucionales.modelo.Tarea;

import java.util.ArrayList;
import java.util.List;

public class TareaDAO {

    Conexion conex;

    public TareaDAO(Activity activity){
        conex = new Conexion(activity);
    }

    public boolean guardar(Tarea tarea){
        ContentValues registro = new ContentValues();
        registro.put("id", tarea.getId());
        registro.put("idActividad", tarea.getIdActividad());
        registro.put("nombre", tarea.getNombre());
        registro.put("descripcion", tarea.getDescripcion());
        registro.put("fechaInicio", tarea.getFechaInicio());
        registro.put("fechaFin", tarea.getFechaFin());
        registro.put("porcentajeDesarrollado", tarea.getPorcentajeDesarrollado());

        return conex.ejecutarInsert("tarea", registro);
    }

    public Tarea buscar(Integer id){
        Tarea tarea = null;
        String consulta = "select id, " +
                "idActividad, " +
                "nombre, " +
                "descripcion, " +
                "fechaInicio, " +
                "fechaFin, " +
                "porcentajeDesarrollado " +
                "from tarea " +
                "where id= " + id ;
        Cursor temp = conex.ejecutarSearch(consulta);

        if (temp.getCount() > 0){
            temp.moveToFirst();
            tarea = new Tarea(
                    temp.getInt(0),
                    temp.getInt(1),
                    temp.getString(2),
                    temp.getString(3),
                    temp.getString(4),
                    temp.getString(5),
                    temp.getInt(6)
            );
        }
        conex.cerrarConexion();
        return tarea;
    }

    public boolean eliminar(Tarea tarea){
        String tabla = "tarea";
        String condicion = "id = " + tarea.getId();
        return conex.ejecutarDelete(tabla, condicion);
    }

    public boolean modificar(Tarea tarea){
        String tabla = "tarea";
        String condicion = "id = " + tarea.getId();

        ContentValues registro = new ContentValues();
        registro.put("idActividad", tarea.getIdActividad());
        registro.put("nombre", tarea.getNombre());
        registro.put("descripcion", tarea.getDescripcion());
        registro.put("fechaInicio", tarea.getFechaInicio());
        registro.put("fechaFin", tarea.getFechaFin());
        registro.put("porcentajeDesarrollado", tarea.getPorcentajeDesarrollado());

        return conex.ejecutarUpdate(tabla, condicion, registro);

    }

    public List<Tarea> listarTareasActividad(Integer idActividad){
        List<Tarea> listaTareas = new ArrayList<Tarea>();
        String consulta = "select id, " +
                "idActividad, " +
                "nombre, " +
                "descripcion, " +
                "fechaInicio, " +
                "fechaFin, " +
                "porcentajeDesarrollado " +
                "from tarea " +
                "where idActividad= " + idActividad ;
        Cursor temp = conex.ejecutarSearch(consulta);

        if(temp.moveToFirst()){
            do{
                Tarea tarea = new Tarea(
                        temp.getInt(0),
                        temp.getInt(1),
                        temp.getString(2),
                        temp.getString(3),
                        temp.getString(4),
                        temp.getString(5),
                        temp.getInt(6)
                );
                listaTareas.add(tarea);
            } while (temp.moveToNext());
        }
        return listaTareas;
    }
    
}
