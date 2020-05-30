package com.jose.proyectos_institucionales.controlador;

import android.app.Activity;

import com.jose.proyectos_institucionales.dao.IntegranteDAO;
import com.jose.proyectos_institucionales.modelo.Integrante;

import java.util.List;

public class CtlIntegrante {

    IntegranteDAO dao;

    public CtlIntegrante(Activity activity) {
        dao = new IntegranteDAO(activity);
    }

    public boolean guardarIntegrante(Integer id, Integer idProyecto, Integer idUsuario, Integer idCargo){
        Integrante integrante = new Integrante(id, idProyecto, idUsuario, idCargo);
        return dao.guardar(integrante);
    }

    public Integrante buscarIntegrante(Integer id){
        return dao.buscar(id);
    }

    public boolean eliminarIntegrante(Integer id){
        Integrante integrante = new Integrante(id, 0, 0, 0);
        return dao.eliminar(integrante);
    }

    public boolean modificarIntegrante(Integer id, Integer idProyecto, Integer idUsuario, Integer idCargo){
        Integrante integrante = new Integrante(id, idProyecto, idUsuario, idCargo);
        return dao.modificar(integrante);
    }

    public List<Integrante> listarIntegrantesProyecto(Integer idProyecto){
        return dao.listarIntegrantesProyecto(idProyecto);
    }
    
}
