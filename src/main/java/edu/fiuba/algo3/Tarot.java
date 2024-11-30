package edu.fiuba.algo3;


public abstract class Tarot {
    private String nombre;
    private String descripcion;
    protected Puntaje puntaje;
    private String sobre;
    protected String ejemplar;


    public Tarot(String nombre, String descripcion, Puntaje puntaje, String sobre, String ejemplar) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.puntaje = puntaje;
        this.sobre = sobre;
        this.ejemplar = ejemplar;
    }

    public abstract void aplicar(Object objeto);

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

}

