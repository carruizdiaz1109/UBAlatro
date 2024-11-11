package edu.fiuba.algo3;

public class Tarot {
    private int incremento;

    public Tarot(int unIncremento){
        this.incremento = unIncremento;
    }

    public void modificarPuntaje(CartaPoker carta) {
        carta.modificarPuntaje(this.incremento);
    }
}
