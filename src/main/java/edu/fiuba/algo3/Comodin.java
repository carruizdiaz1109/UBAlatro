package edu.fiuba.algo3;

public abstract class Comodin {

    protected String nombre;
    protected String descripcion;
    protected Puntaje puntaje;

    public Comodin(Puntaje puntaje, String nombre, String descripcion) {
        this.puntaje = puntaje;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public abstract void aplicar(Jugada jugada);
}
