package edu.fiuba.algo3;

public abstract class Comodin {

    protected Puntaje puntaje;

    public Comodin(int valor, int multiplicador) {
        this.puntaje = new Puntaje(valor, multiplicador);
    }

    public abstract void aplicar(Jugada jugada);
}
