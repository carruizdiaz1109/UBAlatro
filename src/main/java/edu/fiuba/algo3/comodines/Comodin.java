package edu.fiuba.algo3.comodines;

import edu.fiuba.algo3.Puntaje;

public abstract class Comodin {
    private String nombre;
    private String descripcion;

    public Comodin(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public abstract void aplicar(Puntaje puntaje, Object contexto);
}
