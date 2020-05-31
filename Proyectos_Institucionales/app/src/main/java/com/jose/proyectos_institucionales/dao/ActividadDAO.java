package com.jose.proyectos_institucionales.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.jose.proyectos_institucionales.infraestructura.Conexion;
import com.jose.proyectos_institucionales.modelo.Actividad;

import java.util.ArrayList;
import java.util.List;

public class ActividadDAO {

    Conexion conex;

    public ActividadDAO(Activity activity){
        conex = new Conexion(activity);
    }

    public boolean guardar(Actividad actividad){
        ContentValues registro = new ContentValues();
        registro.put("idProyecto", actividad.getIdProyecto());
        registro.put("nombre", actividad.getNombre());
        registro.put("descripcion", actividad.getDescripcion());
        registro.put("idResponsable", actividad.getIdResponsable());
        registro.put("fechaInicio", actividad.getFechaInicio());
        registro.put("fechaFin", actividad.getFechaFin());
        registro.put("porcentajeDesarrollado", actividad.getPorcentajeDesarrollado());

        return conex.ejecutarInsert("actividad", registro);
    }

    public Actividad buscar(Integer id){
        Actividad actividad = null;
        String consulta = "select id, " +
                "idProyecto, " +
                "nombre, " +
                "descripcion, " +
                "idResponsable, " +
                "fechaInicio, " +
                "fechaFin, " +
                "porcentajeDesarrollado " +
                "from actividad " +
                "where id= " + id ;
        Cursor temp = conex.ejecutarSearch(consulta);

        if (temp.getCount() > 0){
            temp.moveToFirst();
            actividad = new Actividad(
                    temp.getInt(0),
                    temp.getInt(1),
                    temp.getString(2),
                    temp.getString(3),
                    temp.getInt(4),
                    temp.getString(5),
                    temp.getString(6),
                    temp.getInt(7)
            );
        }
        conex.cerrarConexion();
        return actividad;
    }

    public boolean eliminar(Actividad actividad){
        String tabla = "actividad";
        String condicion = "id = " + actividad.getId();
        return conex.ejecutarDelete(tabla, condicion);
    }

    public boolean modificar(Actividad actividad){
        String tabla = "actividad";
        String condicion = "id = " + actividad.getId();

        ContentValues registro = new ContentValues();
        registro.put("idProyecto", actividad.getIdProyecto());
        registro.put("nombre", actividad.getNombre());
        registro.put("descripcion", actividad.getDescripcion());
        registro.put("idResponsable", actividad.getIdResponsable());
        registro.put("fechaInicio", actividad.getFechaInicio());
        registro.put("fechaFin", actividad.getFechaFin());
        registro.put("porcentajeDesarrollado", actividad.getPorcentajeDesarrollado());

        return conex.ejecutarUpdate(tabla, condicion, registro);

    }

    public List<Actividad> listarActividadesProyecto(Integer idProyecto){
        List<Actividad> listaActividades = new ArrayList<Actividad>();
        String consulta = "select id, " +
                "idProyecto, " +
                "nombre, " +
                "descripcion, " +
                "idResponsable, " +
                "fechaInicio, " +
                "fechaFin, " +
                "porcentajeDesarrollado " +
                "from actividad " +
                "where idProyecto= " + idProyecto ;
        Cursor temp = conex.ejecutarSearch(consulta);

        if(temp.moveToFirst()){
            do{
                Actividad actividad = new Actividad(
                        temp.getInt(0),
                        temp.getInt(1),
                        temp.getString(2),
                        temp.getString(3),
                        temp.getInt(4),
                        temp.getString(5),
                        temp.getString(6),
                        temp.getInt(7)
                );
                listaActividades.add(actividad);
            } while (temp.moveToNext());
        }
        return listaActividades;
    }

    public List<Actividad> listarMisActividadesProyecto(Integer idProyecto, Integer idResponsable){
        List<Actividad> listaActividades = new ArrayList<Actividad>();
        String consulta = "select id, " +
                "idProyecto, " +
                "nombre, " +
                "descripcion, " +
                "idResponsable, " +
                "fechaInicio, " +
                "fechaFin, " +
                "porcentajeDesarrollado " +
                "from actividad " +
                "where idProyecto= " + idProyecto +
                " and idResponsable= " + idResponsable;
        Cursor temp = conex.ejecutarSearch(consulta);

        if(temp.moveToFirst()){
            do{
                Actividad actividad = new Actividad(
                        temp.getInt(0),
                        temp.getInt(1),
                        temp.getString(2),
                        temp.getString(3),
                        temp.getInt(4),
                        temp.getString(5),
                        temp.getString(6),
                        temp.getInt(7)
                );
                listaActividades.add(actividad);
            } while (temp.moveToNext());
        }
        return listaActividades;
    }
    
}
