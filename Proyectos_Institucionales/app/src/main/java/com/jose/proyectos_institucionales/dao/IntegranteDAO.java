package com.jose.proyectos_institucionales.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.jose.proyectos_institucionales.infraestructura.Conexion;
import com.jose.proyectos_institucionales.modelo.Integrante;

import java.util.ArrayList;
import java.util.List;

public class IntegranteDAO {

    Conexion conex;

    public IntegranteDAO(Activity activity){
        conex = new Conexion(activity);
    }

    public boolean guardar(Integrante integrante){
        ContentValues registro = new ContentValues();
        registro.put("id", integrante.getId());
        registro.put("idProyecto", integrante.getIdProyecto());
        registro.put("idUsuario", integrante.getIdUsuario());
        registro.put("idCargo", integrante.getIdCargo());

        return conex.ejecutarInsert("integrante", registro);
    }

    public Integrante buscar(Integer id){
        Integrante integrante = null;
        String consulta = "select id, " +
                "idProyecto, " +
                "idUsuario, " +
                "idCargo " +
                "from integrante " +
                "where id= " + id ;
        Cursor temp = conex.ejecutarSearch(consulta);

        if (temp.getCount() > 0){
            temp.moveToFirst();
            integrante = new Integrante(
                    temp.getInt(0),
                    temp.getInt(1),
                    temp.getInt(2),
                    temp.getInt(3)
            );
        }
        conex.cerrarConexion();
        return integrante;
    }

    public boolean eliminar(Integrante integrante){
        String tabla = "integrante";
        String condicion = "id = " + integrante.getId();
        return conex.ejecutarDelete(tabla, condicion);
    }

    public boolean modificar(Integrante integrante){
        String tabla = "integrante";
        String condicion = "id = " + integrante.getId();

        ContentValues registro = new ContentValues();
        registro.put("idProyecto", integrante.getIdProyecto());
        registro.put("idUsuario", integrante.getIdUsuario());
        registro.put("idCargo", integrante.getIdCargo());

        return conex.ejecutarUpdate(tabla, condicion, registro);

    }

    public List<Integrante> listarIntegrantesProyecto(Integer idProyecto){
        List<Integrante> listaIntegrantes = new ArrayList<Integrante>();
        String consulta = "select id, " +
                "idProyecto, " +
                "idUsuario, " +
                "idCargo " +
                "from integrante " +
                "where idProyecto= " + idProyecto ;
        Cursor temp = conex.ejecutarSearch(consulta);

        if(temp.moveToFirst()){
            do{
                Integrante integrante = new Integrante(
                        temp.getInt(0),
                        temp.getInt(1),
                        temp.getInt(2),
                        temp.getInt(3)
                );
                listaIntegrantes.add(integrante);
            } while (temp.moveToNext());
        }
        return listaIntegrantes;
    }
    
}
