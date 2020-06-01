package com.jose.proyectos_institucionales.modelo;

public class Cargo {

    private Integer id;
    private Integer idProyecto;
    private String nombre;
    private String descripcion;
    private String horario;
    private Integer salario;

    public Cargo() {
    }

    public Cargo(Integer id, Integer idProyecto, String nombre, String descripcion, String horario, Integer salario) {
        this.id = id;
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.horario = horario;
        this.salario = salario;
    }

    public Cargo(Integer idProyecto, String nombre, String descripcion, String horario, Integer salario) {
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.horario = horario;
        this.salario = salario;
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

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Integer getSalario() {
        return salario;
    }

    public void setSalario(Integer salario) {
        this.salario = salario;
    }
}
