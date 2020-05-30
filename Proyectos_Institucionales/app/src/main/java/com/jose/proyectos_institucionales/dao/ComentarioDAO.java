package com.jose.proyectos_institucionales.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.jose.proyectos_institucionales.infraestructura.Conexion;
import com.jose.proyectos_institucionales.modelo.Comentario;

import java.util.ArrayList;
import java.util.List;

public class ComentarioDAO {

    Conexion conex;

    public ComentarioDAO(Activity activity){
        conex = new Conexion(activity);
    }

    public boolean guardar(Comentario comentario){
        ContentValues registro = new ContentValues();
        registro.put("id", comentario.getId());
        registro.put("idActividad", comentario.getIdActividad());
        registro.put("titulo", comentario.getTitulo());
        registro.put("observacion", comentario.getObservacion());

        return conex.ejecutarInsert("comentario", registro);
    }

    public Comentario buscar(Integer id){
        Comentario comentario = null;
        String consulta = "select id, " +
                "idActividad, " +
                "titulo, " +
                "observacion " +
                "from comentario " +
                "where id= " + id ;
        Cursor temp = conex.ejecutarSearch(consulta);

        if (temp.getCount() > 0){
            temp.moveToFirst();
            comentario = new Comentario(
                    temp.getInt(0),
                    temp.getInt(1),
                    temp.getString(2),
                    temp.getString(3)
            );
        }
        conex.cerrarConexion();
        return comentario;
    }

    public boolean eliminar(Comentario comentario){
        String tabla = "comentario";
        String condicion = "id = " + comentario.getId();
        return conex.ejecutarDelete(tabla, condicion);
    }

    public boolean modificar(Comentario comentario){
        String tabla = "comentario";
        String condicion = "id = " + comentario.getId();

        ContentValues registro = new ContentValues();
        registro.put("idActividad", comentario.getIdActividad());
        registro.put("titulo", comentario.getTitulo());
        registro.put("observacion", comentario.getObservacion());

        return conex.ejecutarUpdate(tabla, condicion, registro);

    }

    public List<Comentario> listarComentariosActividad(Integer idActividad){
        List<Comentario> listaComentarios = new ArrayList<Comentario>();
        String consulta = "select id, " +
                "idActividad, " +
                "titulo, " +
                "observacion " +
                "from comentario " +
                "where idActividad= " + idActividad ;
        Cursor temp = conex.ejecutarSearch(consulta);

        if(temp.moveToFirst()){
            do{
                Comentario comentario = new Comentario(
                        temp.getInt(0),
                        temp.getInt(1),
                        temp.getString(2),
                        temp.getString(3)
                );
                listaComentarios.add(comentario);
            } while (temp.moveToNext());
        }
        return listaComentarios;
    }
    
}
