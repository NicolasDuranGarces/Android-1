package com.jose.proyectos_institucionales.modelo;

public class Integrante {

    private Integer id;
    private Integer idProyecto;
    private Integer idUsuario;
    private Integer idCargo;

    public Integrante(Integer id, Integer idProyecto, Integer idUsuario, Integer idCargo) {
        this.id = id;
        this.idProyecto = idProyecto;
        this.idUsuario = idUsuario;
        this.idCargo = idCargo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }
}
