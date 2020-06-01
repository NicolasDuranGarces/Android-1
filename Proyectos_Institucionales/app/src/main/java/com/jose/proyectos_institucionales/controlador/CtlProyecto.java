package com.jose.proyectos_institucionales.controlador;

import android.app.Activity;

import com.jose.proyectos_institucionales.dao.ProyectoDAO;
import com.jose.proyectos_institucionales.modelo.Proyecto;

import java.io.Serializable;
import java.util.List;

public class CtlProyecto  {

    ProyectoDAO dao;

    public CtlProyecto(Activity activity) {
        dao = new ProyectoDAO(activity);
    }

    public boolean guardarProyecto( String nombre, Integer idDirector, String fechaInicio, String fechaFin, Integer porcentajeDesarrollado){
        Proyecto proyecto = new Proyecto(nombre, idDirector, fechaInicio, fechaFin, porcentajeDesarrollado);
        return dao.guardar(proyecto);
    }

    public Proyecto buscarProyecto(Integer id){
        return dao.buscar(id);
    }

    public boolean eliminarProyecto(Integer id){
        Proyecto proyecto = new Proyecto(id, "", 0, "", "", 0);
        return dao.eliminar(proyecto);
    }

    public boolean modificarProyecto(Integer id, String nombre, Integer idDirector, String fechaInicio, String fechaFin, Integer porcentajeDesarrollado){
        Proyecto proyecto = new Proyecto(id, nombre, idDirector, fechaInicio, fechaFin, porcentajeDesarrollado);
        return dao.modificar(proyecto);
    }

    public List<Proyecto> listarProyectosQueDirijo(Integer idDirector){
        return dao.listarProyectosQueDirijo(idDirector);
    }

    public List<Proyecto> listarProyectosQueIntegro(Integer idIntegrante){
        return dao.listarProyectosQueIntegro(idIntegrante);
    }


}
