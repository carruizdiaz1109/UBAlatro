package edu.fiuba.algo3.modelo.entidades.comodines;

import edu.fiuba.algo3.modelo.entidades.Jugada;
import edu.fiuba.algo3.modelo.entidades.Puntaje;
import edu.fiuba.algo3.modelo.interfaces.Aleatoriedad;

public abstract class Comodin implements Aleatoriedad {

    protected String nombre;
    protected String descripcion;
    protected Puntaje puntaje;
    private Aleatoriedad aleatoriedad;

    public Comodin(Puntaje puntaje, String nombre, String descripcion, Aleatoriedad unaAleatoriedad) {
        this.puntaje = puntaje;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.aleatoriedad = unaAleatoriedad;
    }

    public boolean seAplica() {
        return this.aleatoriedad.seAplica();
    }

    public void setAleatoreidad(Aleatoriedad nuevaAleatoriedad) {
        this.aleatoriedad = nuevaAleatoriedad;
    }

    public abstract void aplicar(Jugada jugada);
}
