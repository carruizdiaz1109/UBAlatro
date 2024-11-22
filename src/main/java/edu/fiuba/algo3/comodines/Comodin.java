package edu.fiuba.algo3.comodines;

import edu.fiuba.algo3.Jugada;
import edu.fiuba.algo3.Puntaje;

public abstract class Comodin implements Aleatoreidad {

    protected String nombre;
    protected String descripcion;
    protected Puntaje puntaje;
    private final Aleatoreidad aleatoreidad;

    public Comodin(Puntaje puntaje, String nombre, String descripcion, Aleatoreidad unaAleatoreidad) {
        this.puntaje = puntaje;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.aleatoreidad = unaAleatoreidad;
    }

    public boolean seAplica() {
        return this.aleatoreidad.seAplica();
    }

    public void setAleatoreidad(Aleatoreidad nuevaAleatoreidad) {
        this.aleatoreidad = nuevaAleatoreidad;
    }

    public abstract void aplicar(Jugada jugada);
}
