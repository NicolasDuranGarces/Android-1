package com.jose.proyectos_institucionales.modelo;

public class Reunion {

    private Integer id;
    private Integer idProyecto;
    private String coordenadaLatitud;
    private String coordenadaLongitud;
    private String sitio;
    private String tematica;

    public Reunion(Integer id, Integer idProyecto, String coordenadaLatitud, String coordenadaLongitud, String sitio, String tematica) {
        this.id = id;
        this.idProyecto = idProyecto;
        this.coordenadaLatitud = coordenadaLatitud;
        this.coordenadaLongitud = coordenadaLongitud;
        this.sitio = sitio;
        this.tematica = tematica;
    }

    public Reunion(Integer idProyecto, String coordenadaLatitud, String coordenadaLongitud, String sitio, String tematica) {
        this.idProyecto = idProyecto;
        this.coordenadaLatitud = coordenadaLatitud;
        this.coordenadaLongitud = coordenadaLongitud;
        this.sitio = sitio;
        this.tematica = tematica;
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

    public String getCoordenadaLatitud() {
        return coordenadaLatitud;
    }

    public void setCoordenadaLatitud(String coordenadaLatitud) {
        this.coordenadaLatitud = coordenadaLatitud;
    }

    public String getCoordenadaLongitud() {
        return coordenadaLongitud;
    }

    public void setCoordenadaLongitud(String coordenadaLongitud) {
        this.coordenadaLongitud = coordenadaLongitud;
    }

    public String getSitio() {
        return sitio;
    }

    public void setSitio(String sitio) {
        this.sitio = sitio;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }
}
