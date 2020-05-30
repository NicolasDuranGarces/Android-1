package com.jose.proyectos_institucionales.controlador;

import android.app.Activity;

import com.jose.proyectos_institucionales.dao.ReunionDAO;
import com.jose.proyectos_institucionales.modelo.Reunion;

import java.util.List;

public class CtlReunion {

    ReunionDAO dao;

    public CtlReunion(Activity activity) {
        dao = new ReunionDAO(activity);
    }

    public boolean guardarReunion(Integer id, Integer idProyecto, String coordenadaLatitud, String coordenadaLongitud, String sitio, String tematica){
        Reunion reunion = new Reunion(id, idProyecto, coordenadaLatitud, coordenadaLongitud, sitio, tematica);
        return dao.guardar(reunion);
    }

    public Reunion buscarReunion(Integer id){
        return dao.buscar(id);
    }

    public boolean eliminarReunion(Integer id){
        Reunion reunion = new Reunion(id, 0, "", "", "", "");
        return dao.eliminar(reunion);
    }

    public boolean modificarReunion(Integer id, Integer idProyecto, String coordenadaLatitud, String coordenadaLongitud, String sitio, String tematica){
        Reunion reunion = new Reunion(id, idProyecto, coordenadaLatitud, coordenadaLongitud, sitio, tematica);
        return dao.modificar(reunion);
    }

    public List<Reunion> listarReunionesProyecto(Integer idProyecto){
        return dao.listarReunionesProyecto(idProyecto);
    }
    
}
