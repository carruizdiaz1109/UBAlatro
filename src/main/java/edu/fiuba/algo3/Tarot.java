package edu.fiuba.algo3;

public class Tarot {
    Puntaje puntaje;

    public Tarot(Puntaje unPuntaje){
        this.puntaje = unPuntaje;
    }

    public void modificarPuntaje(CartaPoker carta) {
        carta.modificarPuntaje(this.puntaje);
    }
}
