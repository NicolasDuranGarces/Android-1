package com.jose.proyectos_institucionales.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.jose.proyectos_institucionales.infraestructura.Conexion;
import com.jose.proyectos_institucionales.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    Conexion conex;

    public UsuarioDAO(Activity activity) {
        conex = new Conexion(activity);
    }

    public boolean guardar(Usuario usuario) {
        ContentValues registro = new ContentValues();
        registro.put("numeroDocumento", usuario.getNumeroDocumento());
        registro.put("nombres", usuario.getNombres());
        registro.put("apellidos", usuario.getApellidos());
        registro.put("fechaNacimiento", usuario.getFechaNacimiento());
        registro.put("clave", usuario.getClave());
        registro.put("email", usuario.getEmail());

        return conex.ejecutarInsert("usuario", registro);
    }

    public Usuario buscar(String numeroDocumento) {
        Usuario usuario = null;
        String consulta = "select id, " +
                "numeroDocumento, " +
                "nombres, " +
                "apellidos, " +
                "fechaNacimiento, " +
                "clave, " +
                "email " +
                "from usuario " +
                "where numeroDocumento= '" + numeroDocumento + "'";
        Cursor temp = conex.ejecutarSearch(consulta);

        if (temp.getCount() > 0) {
            temp.moveToFirst();
            usuario = new Usuario(
                    temp.getInt(0),
                    temp.getString(1),
                    temp.getString(2),
                    temp.getString(3),
                    temp.getString(4),
                    temp.getString(5),
                    temp.getString(6)
            );
        }
        conex.cerrarConexion();
        return usuario;
    }


    public Boolean verificarExistencia(String numeroDocumento) {
        Usuario usuario = null;
        String consulta = "select id, " +
                "numeroDocumento, " +
                "nombres, " +
                "apellidos, " +
                "fechaNacimiento, " +
                "clave, " +
                "email " +
                "from usuario " +
                "where numeroDocumento= '" + numeroDocumento + "'";
        Cursor temp = conex.ejecutarSearch(consulta);

        if (temp.getCount() > 0) {
            return true;
        }
            conex.cerrarConexion();
            return false;

    }

    public boolean eliminar(Usuario usuario) {
        String tabla = "usuario";
        String condicion = "id = " + usuario.getId();
        return conex.ejecutarDelete(tabla, condicion);
    }


    public boolean modificar(Usuario usuario) {
        String tabla = "usuario";
        String condicion = "id = " + usuario.getId();

        ContentValues registro = new ContentValues();
        registro.put("numeroDocumento", usuario.getNumeroDocumento());
        registro.put("nombres", usuario.getNombres());
        registro.put("apellidos", usuario.getApellidos());
        registro.put("fechaNacimiento", usuario.getFechaNacimiento());
        registro.put("clave", usuario.getClave());
        registro.put("email", usuario.getEmail());

        return conex.ejecutarUpdate(tabla, condicion, registro);

    }

    public List<Usuario> listar() {
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        String consulta = "select id, " +
                "numeroDocumento, " +
                "nombres, " +
                "apellidos, " +
                "fechaNacimiento, " +
                "clave, " +
                "email " +
                "from usuario";
        Cursor temp = conex.ejecutarSearch(consulta);

        if (temp.moveToFirst()) {
            do {
                Usuario usuario = new Usuario(
                        temp.getInt(0),
                        temp.getString(1),
                        temp.getString(2),
                        temp.getString(3),
                        temp.getString(4),
                        temp.getString(5),
                        temp.getString(6)
                );
                listaUsuarios.add(usuario);
            } while (temp.moveToNext());
        }
        return listaUsuarios;
    }

}
