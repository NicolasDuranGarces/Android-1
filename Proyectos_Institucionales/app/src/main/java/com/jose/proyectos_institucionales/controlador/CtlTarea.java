package com.jose.proyectos_institucionales.controlador;

import android.app.Activity;

import com.jose.proyectos_institucionales.dao.TareaDAO;
import com.jose.proyectos_institucionales.modelo.Tarea;

import java.util.List;

public class CtlTarea {

    TareaDAO dao;

    public CtlTarea(Activity activity) {
        dao = new TareaDAO(activity);
    }

    public boolean guardarTarea(Integer idActividad, String nombre, String descripcion, String fechaInicio, String fechaFin, Integer porcentajeDesarrollado){
        Tarea tarea = new Tarea(idActividad, nombre, descripcion, fechaInicio, fechaFin, porcentajeDesarrollado);
        return dao.guardar(tarea);
    }

    public Tarea buscarTarea(Integer id){
        return dao.buscar(id);
    }

    public boolean eliminarTarea(Integer id){
        Tarea tarea = new Tarea(id, 0, "", "", "", "", 0);
        return dao.eliminar(tarea);
    }

    public boolean modificarTarea(Integer id, Integer idActividad, String nombre, String descripcion, String fechaInicio, String fechaFin, Integer porcentajeDesarrollado){
        Tarea tarea = new Tarea(id, idActividad, nombre, descripcion, fechaInicio, fechaFin, porcentajeDesarrollado);
        return dao.modificar(tarea);
    }

    public List<Tarea> listarTareasActividad(Integer idActividad){
        return dao.listarTareasActividad(idActividad);
    }


}
