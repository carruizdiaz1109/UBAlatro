package edu.fiuba.algo3;

public abstract class Comodin {

    protected String nombre;
    protected String descripcion;
    protected Puntaje puntaje;

    public Comodin(int valor, int multiplicador, String nombre, String descripcion) {
        this.puntaje = new Puntaje(valor, multiplicador);
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public abstract void aplicar(Jugada jugada);
}
