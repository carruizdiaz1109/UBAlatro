package edu.fiuba.algo3;

public class Tarot {
    Puntaje puntaje;

    public Tarot(int valor, int multiplicador){
        this.puntaje = new Puntaje(valor, multiplicador);
    }

    public Puntaje modificarPuntaje(Puntaje unPuntaje) {
        return puntaje.sumarPuntaje(unPuntaje);
    }
}
