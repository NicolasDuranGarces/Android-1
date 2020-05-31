package com.jose.proyectos_institucionales.controlador;

import android.app.Activity;

import com.jose.proyectos_institucionales.dao.UsuarioDAO;
import com.jose.proyectos_institucionales.modelo.Usuario;

import java.util.List;

public class CtlUsuario {

    UsuarioDAO dao;

    public CtlUsuario(Activity activity) {
        dao = new UsuarioDAO(activity);
    }

    public boolean guardarUsuario(String numeroDocumento, String nombres, String apellidos, String fechaNacimiento, String clave, String email){
        Usuario usuario = new Usuario(numeroDocumento, nombres, apellidos, fechaNacimiento, clave, email);
        return dao.guardar(usuario);
    }

    public Usuario buscarUsuarioCedula(String numeroDocumento){
        return dao.buscar(numeroDocumento);
    }

    public Usuario buscarUsuarioCorreo(String correo){
        return dao.buscarCorreo(correo);
    }

    public boolean eliminarUsuario(Integer id){
        Usuario usuario = new Usuario(id, "", "", "", "", "", "");
        return dao.eliminar(usuario);
    }

    public boolean modificarUsuario(Integer id, String numeroDocumento, String nombres, String apellidos, String fechaNacimiento, String clave, String email){
        Usuario usuario = new Usuario(id, numeroDocumento, nombres, apellidos, fechaNacimiento, clave, email);
        return dao.modificar(usuario);
    }

    public List<Usuario> listarUsuario(){
        return dao.listar();
    }

}
