package com.jose.proyectos_institucionales.controlador;

import android.app.Activity;

import com.jose.proyectos_institucionales.dao.ActividadDAO;
import com.jose.proyectos_institucionales.modelo.Actividad;

import java.util.List;

public class CtlActividad {

    ActividadDAO dao;

    public CtlActividad(Activity activity) {
        dao = new ActividadDAO(activity);
    }

    public boolean guardarActividad(Integer id, Integer idProyecto, String nombre, String descripcion, Integer idResponsable, String fechaInicio, String fechaFin, Integer porcentajeDesarrollado){
        Actividad actividad = new Actividad(id, idProyecto, nombre, descripcion, idResponsable, fechaInicio, fechaFin, porcentajeDesarrollado);
        return dao.guardar(actividad);
    }

    public Actividad buscarActividad(Integer id){
        return dao.buscar(id);
    }

    public boolean eliminarActividad(Integer id){
        Actividad actividad = new Actividad(id, 0, "", "", 0, "", "", 0);
        return dao.eliminar(actividad);
    }

    public boolean modificarActividad(Integer id, Integer idProyecto, String nombre, String descripcion, Integer idResponsable, String fechaInicio, String fechaFin, Integer porcentajeDesarrollado){
        Actividad actividad = new Actividad(id, idProyecto, nombre, descripcion, idResponsable, fechaInicio, fechaFin, porcentajeDesarrollado);
        return dao.modificar(actividad);
    }

    public List<Actividad> listarActividadesProyecto(Integer idProyecto){
        return dao.listarActividadesProyecto(idProyecto);
    }

    public List<Actividad> listarMisActividadesProyecto(Integer idProyecto, Integer idResponsable){
        return dao.listarMisActividadesProyecto(idProyecto, idResponsable);
    }


}
