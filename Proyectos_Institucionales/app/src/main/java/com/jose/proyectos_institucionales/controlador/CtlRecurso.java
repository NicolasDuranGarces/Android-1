package com.jose.proyectos_institucionales.controlador;

import android.app.Activity;

import com.jose.proyectos_institucionales.dao.RecursoDAO;
import com.jose.proyectos_institucionales.modelo.Recurso;

import java.util.List;

public class CtlRecurso {

    RecursoDAO dao;

    public CtlRecurso(Activity activity) {
        dao = new RecursoDAO(activity);
    }

    public boolean guardarRecurso(Integer idProyecto, String nombre, Integer cantidad, String descripcion, String ubicacion){
        Recurso recurso = new Recurso(idProyecto, nombre, cantidad, descripcion, ubicacion);
        return dao.guardar(recurso);
    }

    public Recurso buscarRecurso(Integer id){
        return dao.buscar(id);
    }

    public boolean eliminarRecurso(Integer id){
        Recurso recurso = new Recurso(id, 0, "", 0, "", "");
        return dao.eliminar(recurso);
    }

    public boolean modificarRecurso(Integer id, Integer idProyecto, String nombre, Integer cantidad, String descripcion, String ubicacion){
        Recurso recurso = new Recurso(id, idProyecto, nombre, cantidad, descripcion, ubicacion);
        return dao.modificar(recurso);
    }

    public List<Recurso> listarRecursosProyecto(Integer idProyecto){
        return dao.listarRecursosProyecto(idProyecto);
    }

    public List<Recurso> listarRecursosTarea(Integer idTarea){
        return dao.listarRecursosTarea(idTarea);
    }
}
