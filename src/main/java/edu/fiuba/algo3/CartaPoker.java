package edu.fiuba.algo3;

public class CartaPoker {
    private int valor;
    private String palo;
    private Puntaje puntaje;

    public CartaPoker(int valor, String palo){
        this.valor = valor;
        this.palo = palo;
        this.puntaje = new Puntaje( this.valor, 1);
    }

    public CartaPoker comprar() {
        return this;
    }
}
