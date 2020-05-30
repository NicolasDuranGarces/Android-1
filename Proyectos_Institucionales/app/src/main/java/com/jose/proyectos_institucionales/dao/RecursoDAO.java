package com.jose.proyectos_institucionales.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.jose.proyectos_institucionales.infraestructura.Conexion;
import com.jose.proyectos_institucionales.modelo.Recurso;

import java.util.ArrayList;
import java.util.List;

public class RecursoDAO {

    Conexion conex;

    public RecursoDAO(Activity activity){
        conex = new Conexion(activity);
    }

    public boolean guardar(Recurso recurso){
        ContentValues registro = new ContentValues();
        registro.put("id", recurso.getId());
        registro.put("idProyecto", recurso.getIdProyecto());
        registro.put("nombre", recurso.getNombre());
        registro.put("cantidad", recurso.getCantidad());
        registro.put("descripcion", recurso.getDescripcion());
        registro.put("ubicacion", recurso.getUbicacion());

        return conex.ejecutarInsert("recurso", registro);
    }

    public Recurso buscar(Integer id){
        Recurso recurso = null;
        String consulta = "select id, " +
                "idProyecto, " +
                "nombre, " +
                "cantidad, " +
                "descripcion, " +
                "ubicacion " +
                "from recurso " +
                "where id= " + id ;
        Cursor temp = conex.ejecutarSearch(consulta);

        if (temp.getCount() > 0){
            temp.moveToFirst();
            recurso = new Recurso(
                    temp.getInt(0),
                    temp.getInt(1),
                    temp.getString(2),
                    temp.getInt(3),
                    temp.getString(4),
                    temp.getString(5)
            );
        }
        conex.cerrarConexion();
        return recurso;
    }

    public boolean eliminar(Recurso recurso){
        String tabla = "recurso";
        String condicion = "id = " + recurso.getId();
        return conex.ejecutarDelete(tabla, condicion);
    }

    public boolean modificar(Recurso recurso){
        String tabla = "recurso";
        String condicion = "id = " + recurso.getId();

        ContentValues registro = new ContentValues();
        registro.put("idProyecto", recurso.getIdProyecto());
        registro.put("nombre", recurso.getNombre());
        registro.put("cantidad", recurso.getCantidad());
        registro.put("descripcion", recurso.getDescripcion());
        registro.put("ubicacion", recurso.getUbicacion());

        return conex.ejecutarUpdate(tabla, condicion, registro);

    }

    public List<Recurso> listarRecursosProyecto(Integer idProyecto){
        List<Recurso> listaRecursos = new ArrayList<Recurso>();
        String consulta = "select id, " +
                "idProyecto, " +
                "nombre, " +
                "cantidad, " +
                "descripcion, " +
                "ubicacion " +
                "from recurso " +
                "where idProyecto= " + idProyecto ;
        Cursor temp = conex.ejecutarSearch(consulta);

        if(temp.moveToFirst()){
            do{
                Recurso recurso = new Recurso(
                        temp.getInt(0),
                        temp.getInt(1),
                        temp.getString(2),
                        temp.getInt(3),
                        temp.getString(4),
                        temp.getString(5)
                );
                listaRecursos.add(recurso);
            } while (temp.moveToNext());
        }
        return listaRecursos;
    }

    public List<Recurso> listarRecursosTarea(Integer idTarea){
        List<Recurso> listaRecursos = new ArrayList<Recurso>();
        String consulta = "select r.id, " +
                "r.idProyecto, " +
                "r.nombre, " +
                "r.cantidad, " +
                "r.descripcion, " +
                "r.ubicacion " +
                "from recurso r " +
                "join recurso_tarea rt on r.id = rt.idRecurso " +
                "join tarea t on rt.idTarea = t.id " +
                "where idTarea= " + idTarea ;
        Cursor temp = conex.ejecutarSearch(consulta);

        if(temp.moveToFirst()){
            do{
                Recurso recurso = new Recurso(
                        temp.getInt(0),
                        temp.getInt(1),
                        temp.getString(2),
                        temp.getInt(3),
                        temp.getString(4),
                        temp.getString(5)
                );
                listaRecursos.add(recurso);
            } while (temp.moveToNext());
        }
        return listaRecursos;
    }
    
}
