package edu.fiuba.algo3.modelo.entidades.comodines;

import edu.fiuba.algo3.modelo.entidades.Jugada;
import edu.fiuba.algo3.modelo.entidades.Puntaje;
import edu.fiuba.algo3.modelo.interfaces.Aleatoriedad;
import edu.fiuba.algo3.modelo.interfaces.Comprable;

public abstract class Comodin implements Aleatoriedad, Comprable {

    protected String nombre;
    protected String descripcion;
    protected Puntaje puntaje;
    private Aleatoriedad aleatoreidad;

    public Comodin(Puntaje puntaje, String nombre, String descripcion, Aleatoriedad unaAleatoreidad) {
        this.puntaje = puntaje;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.aleatoreidad = unaAleatoreidad;
    }

    public boolean seAplica() {
        return this.aleatoreidad.seAplica();
    }

    public void setAleatoreidad(Aleatoriedad nuevaAleatoreidad) {
        this.aleatoreidad = nuevaAleatoreidad;
    }

    public abstract void aplicar(Jugada jugada);

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public Comprable comprar() { return this; }
}
