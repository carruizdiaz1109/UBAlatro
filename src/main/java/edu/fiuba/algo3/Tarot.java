package edu.fiuba.algo3;

public class Tarot {
    Puntaje puntaje;

    public Tarot(){
        this.puntaje = new Puntaje(0, 1);
    }

    public Tarot(Puntaje puntaje){
        this.puntaje = puntaje;
    }

    public Puntaje modificarPuntaje(Puntaje unPuntaje) {
        return puntaje.sumarPuntaje(unPuntaje);
    }
}
