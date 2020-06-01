package com.jose.proyectos_institucionales.modelo;

import java.io.Serializable;

public class Proyecto implements Serializable {

    private Integer id;
    private String nombre;
    private Integer idDirector;
    private String fechaInicio;
    private String fechaFin;
    private Integer porcentajeDesarrollado;

    public Proyecto(Integer id, String nombre, Integer idDirector, String fechaInicio, String fechaFin, Integer porcentajeDesarrollado) {
        this.id = id;
        this.nombre = nombre;
        this.idDirector = idDirector;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.porcentajeDesarrollado = porcentajeDesarrollado;
    }

    public Proyecto(String nombre, Integer idDirector, String fechaInicio, String fechaFin, Integer porcentajeDesarrollado) {
        this.nombre = nombre;
        this.idDirector = idDirector;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.porcentajeDesarrollado = porcentajeDesarrollado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(Integer idDirector) {
        this.idDirector = idDirector;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getPorcentajeDesarrollado() {
        return porcentajeDesarrollado;
    }

    public void setPorcentajeDesarrollado(Integer porcentajeDesarrollado) {
        this.porcentajeDesarrollado = porcentajeDesarrollado;
    }
}
