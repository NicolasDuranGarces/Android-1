package com.jose.proyectos_institucionales.controlador;

import android.app.Activity;

import com.jose.proyectos_institucionales.dao.CargoDAO;
import com.jose.proyectos_institucionales.modelo.Cargo;

import java.util.List;

public class CtlCargo {

    CargoDAO dao;

    public CtlCargo(Activity activity) {
        dao = new CargoDAO(activity);
    }

    public boolean guardarCargo(Integer idProyecto, String nombre, String descripcion, String horario, Integer idDirector){
        Cargo cargo = new Cargo(idProyecto, nombre, descripcion, horario, idDirector);
        return dao.guardar(cargo);
    }

    public Cargo buscarCargo(Integer id){
        return dao.buscar(id);
    }

    public boolean eliminarCargo(Integer id){
        Cargo cargo = new Cargo(id, 0, "", "", "", 0);
        return dao.eliminar(cargo);
    }

    public boolean modificarCargo(Integer id, Integer idProyecto, String nombre, String descripcion, String horario, Integer idDirector){
        Cargo cargo = new Cargo(id, idProyecto, nombre, descripcion, horario, idDirector);
        return dao.modificar(cargo);
    }

    public List<Cargo> listarCargosProyecto(Integer idProyecto){
        return dao.listarCargosProyecto(idProyecto);
    }

}
