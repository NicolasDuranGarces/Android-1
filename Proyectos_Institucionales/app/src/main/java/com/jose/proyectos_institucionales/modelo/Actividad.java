package com.jose.proyectos_institucionales.modelo;

import java.io.Serializable;

public class Actividad implements Serializable {

    private Integer id;
    private Integer idProyecto;
    private String nombre;
    private String descripcion;
    private Integer idResponsable;
    private String fechaInicio;
    private String fechaFin;
    private Integer porcentajeDesarrollado;

    public Actividad(Integer id, Integer idProyecto, String nombre, String descripcion, Integer idResponsable, String fechaInicio, String fechaFin, Integer porcentajeDesarrollado) {
        this.id = id;
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idResponsable = idResponsable;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.porcentajeDesarrollado = porcentajeDesarrollado;
    }

    public Actividad(Integer idProyecto, String nombre, String descripcion, Integer idResponsable, String fechaInicio, String fechaFin, Integer porcentajeDesarrollado) {
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idResponsable = idResponsable;
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

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(Integer idResponsable) {
        this.idResponsable = idResponsable;
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
