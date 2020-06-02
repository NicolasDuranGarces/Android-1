package com.jose.proyectos_institucionales.modelo;

import java.io.Serializable;

public class Tarea implements Serializable {

    private Integer id;
    private Integer idActividad;
    private String nombre;
    private String descripcion;
    private String fechaInicio;
    private String fechaFin;
    private Integer porcentajeDesarrollado;

    public Tarea(Integer id, Integer idActividad, String nombre, String descripcion, String fechaInicio, String fechaFin, Integer porcentajeDesarrollado) {
        this.id = id;
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.porcentajeDesarrollado = porcentajeDesarrollado;
    }

    public Tarea(Integer idActividad, String nombre, String descripcion, String fechaInicio, String fechaFin, Integer porcentajeDesarrollado) {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
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
