package com.jose.proyectos_institucionales.controlador;

import android.app.Activity;

import com.jose.proyectos_institucionales.dao.ComentarioDAO;
import com.jose.proyectos_institucionales.modelo.Comentario;

import java.util.List;

public class CtlComentario {

    ComentarioDAO dao;

    public CtlComentario(Activity activity) {
        dao = new ComentarioDAO(activity);
    }

    public boolean guardarComentario(Integer idActividad, String titulo, String observacion){
        Comentario comentario = new Comentario(idActividad, titulo, observacion);
        return dao.guardar(comentario);
    }

    public Comentario buscarComentario(Integer id){
        return dao.buscar(id);
    }

    public boolean eliminarComentario(Integer id){
        Comentario comentario = new Comentario(id, 0, "", "");
        return dao.eliminar(comentario);
    }

    public boolean modificarComentario(Integer id, Integer idActividad, String titulo, String observacion){
        Comentario comentario = new Comentario(id, idActividad, titulo, observacion);
        return dao.modificar(comentario);
    }

    public List<Comentario> listarComentariosActividad(Integer idActividad){
        return dao.listarComentariosActividad(idActividad);
    }
    
}
