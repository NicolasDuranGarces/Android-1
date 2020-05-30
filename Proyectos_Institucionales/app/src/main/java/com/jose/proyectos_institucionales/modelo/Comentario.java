package com.jose.proyectos_institucionales.modelo;

public class Comentario {

    private Integer id;
    private Integer idActividad;
    private String titulo;
    private String observacion;

    public Comentario(Integer id, Integer idActividad, String titulo, String observacion) {
        this.id = id;
        this.idActividad = idActividad;
        this.titulo = titulo;
        this.observacion = observacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
