package edu.fiuba.algo3.comodines;

import edu.fiuba.algo3.Puntaje;

public abstract class Comodin {
    private String nombre;
    private String descripcion;
    private final Puntaje efecto;

    /*
    public Comodin(String nombre, String descripcion, Puntaje efecto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.efecto = efecto;
    }
    */

    public Comodin(int valor, int multiplicador) {
        this.efecto = new Puntaje(valor, multiplicador);
    }

    public abstract void aplicar(Puntaje puntaje);
}
