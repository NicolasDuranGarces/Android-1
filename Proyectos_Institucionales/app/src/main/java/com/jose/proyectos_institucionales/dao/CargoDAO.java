package com.jose.proyectos_institucionales.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.jose.proyectos_institucionales.infraestructura.Conexion;
import com.jose.proyectos_institucionales.modelo.Cargo;

import java.util.ArrayList;
import java.util.List;

public class CargoDAO {

    Conexion conex;

    public CargoDAO(Activity activity){
        conex = new Conexion(activity);
    }

    public boolean guardar(Cargo cargo){
        ContentValues registro = new ContentValues();
        registro.put("idProyecto", cargo.getIdProyecto());
        registro.put("nombre", cargo.getNombre());
        registro.put("descripcion", cargo.getDescripcion());
        registro.put("horario", cargo.getHorario());
        registro.put("idDirector", cargo.getIdDirector());

        return conex.ejecutarInsert("cargo", registro);
    }

    public Cargo buscar(Integer id){
        Cargo cargo = null;
        String consulta = "select id, " +
                "idProyecto, " +
                "nombre, " +
                "descripcion, " +
                "horario, " +
                "idDirector " +
                "from cargo " +
                "where id= " + id ;
        Cursor temp = conex.ejecutarSearch(consulta);

        if (temp.getCount() > 0){
            temp.moveToFirst();
            cargo = new Cargo(
                    temp.getInt(0),
                    temp.getInt(1),
                    temp.getString(2),
                    temp.getString(3),
                    temp.getString(4),
                    temp.getInt(5)
            );
        }
        conex.cerrarConexion();
        return cargo;
    }

    public boolean eliminar(Cargo cargo){
        String tabla = "cargo";
        String condicion = "id = " + cargo.getId();
        return conex.ejecutarDelete(tabla, condicion);
    }

    public boolean modificar(Cargo cargo){
        String tabla = "cargo";
        String condicion = "id = " + cargo.getId();

        ContentValues registro = new ContentValues();
        registro.put("idProyecto", cargo.getIdProyecto());
        registro.put("nombre", cargo.getNombre());
        registro.put("descripcion", cargo.getDescripcion());
        registro.put("horario", cargo.getHorario());
        registro.put("idDirector", cargo.getIdDirector());

        return conex.ejecutarUpdate(tabla, condicion, registro);

    }

    public List<Cargo> listarCargosProyecto(Integer idProyecto){
        List<Cargo> listaCargos = new ArrayList<Cargo>();
        String consulta = "select id, " +
                "idProyecto, " +
                "nombre, " +
                "descripcion, " +
                "horario, " +
                "idDirector " +
                "from cargo " +
                "where idProyecto= " + idProyecto ;
        Cursor temp = conex.ejecutarSearch(consulta);

        if(temp.moveToFirst()){
            do{
                Cargo cargo = new Cargo(
                        temp.getInt(0),
                        temp.getInt(1),
                        temp.getString(2),
                        temp.getString(3),
                        temp.getString(4),
                        temp.getInt(5)
                );
                listaCargos.add(cargo);
            } while (temp.moveToNext());
        }
        return listaCargos;
    }
    
}
