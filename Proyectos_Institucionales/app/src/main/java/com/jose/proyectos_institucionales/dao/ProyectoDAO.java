package com.jose.proyectos_institucionales.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.jose.proyectos_institucionales.infraestructura.Conexion;
import com.jose.proyectos_institucionales.modelo.Proyecto;

import java.util.ArrayList;
import java.util.List;

public class ProyectoDAO {

    Conexion conex;

    public ProyectoDAO(Activity activity){
        conex = new Conexion(activity);
    }

    public boolean guardar(Proyecto proyecto){
        ContentValues registro = new ContentValues();
        registro.put("nombre", proyecto.getNombre());
        registro.put("idDirector", proyecto.getIdDirector());
        registro.put("fechaInicio", proyecto.getFechaInicio());
        registro.put("fechaFin", proyecto.getFechaFin());
        registro.put("porcentajeDesarrollado", proyecto.getPorcentajeDesarrollado());
        return conex.ejecutarInsert("proyecto", registro);
    }

    public Proyecto buscar(Integer id){
        Proyecto proyecto = null;
        String consulta = "select id, " +
                "nombre, " +
                "idDirector, " +
                "fechaInicio, " +
                "fechaFin, " +
                "porcentajeDesarrollado " +
                "from proyecto " +
                "where id= " + id ;
        Cursor temp = conex.ejecutarSearch(consulta);

        if (temp.getCount() > 0){
            temp.moveToFirst();
            proyecto = new Proyecto(
                    temp.getInt(0),
                    temp.getString(1),
                    temp.getInt(2),
                    temp.getString(3),
                    temp.getString(4),
                    temp.getInt(5)
            );
        }
        conex.cerrarConexion();
        return proyecto;
    }

    public boolean eliminar(Proyecto proyecto){
        String tabla = "proyecto";
        String condicion = "id = " + proyecto.getId();
        return conex.ejecutarDelete(tabla, condicion);
    }

    public boolean modificar(Proyecto proyecto){
        String tabla = "proyecto";
        String condicion = "id = " + proyecto.getId();

        ContentValues registro = new ContentValues();
        registro.put("nombre", proyecto.getNombre());
        registro.put("idDirector", proyecto.getIdDirector());
        registro.put("fechaInicio", proyecto.getFechaInicio());
        registro.put("fechaFin", proyecto.getFechaFin());
        registro.put("porcentajeDesarrollado", proyecto.getPorcentajeDesarrollado());

        return conex.ejecutarUpdate(tabla, condicion, registro);

    }

    public List<Proyecto> listarProyectosQueDirijo(Integer idDirector){
        List<Proyecto> listaProyectos = new ArrayList<Proyecto>();
        String consulta = "select id, " +
                "nombre, " +
                "idDirector, " +
                "fechaInicio, " +
                "fechaFin, " +
                "porcentajeDesarrollado " +
                "from proyecto " +
                "where idDirector= " + idDirector ;
        Cursor temp = conex.ejecutarSearch(consulta);

        if(temp.moveToFirst()){
            do{
                Proyecto proyecto = new Proyecto(
                        temp.getInt(0),
                        temp.getString(1),
                        temp.getInt(2),
                        temp.getString(3),
                        temp.getString(4),
                        temp.getInt(5)
                );
                listaProyectos.add(proyecto);
            } while (temp.moveToNext());
        }
        return listaProyectos;
    }

    public List<Proyecto> listarProyectosQueIntegro(Integer idUsuario){
        List<Proyecto> listaProyectos = new ArrayList<Proyecto>();
        String consulta = "select p.id, " +
                "p.nombre, " +
                "p.idDirector, " +
                "p.fechaInicio, " +
                "p.fechaFin, " +
                "p.porcentajeDesarrollado " +
                "from proyecto p " +
                "join integrante i on p.id = i.idProyecto " +
                "where idUsuario= " + idUsuario ;
        Cursor temp = conex.ejecutarSearch(consulta);

        if(temp.moveToFirst()){
            do{
                Proyecto proyecto = new Proyecto(
                        temp.getInt(0),
                        temp.getString(1),
                        temp.getInt(2),
                        temp.getString(3),
                        temp.getString(4),
                        temp.getInt(5)
                );
                listaProyectos.add(proyecto);
            } while (temp.moveToNext());
        }
        return listaProyectos;
    }

}
