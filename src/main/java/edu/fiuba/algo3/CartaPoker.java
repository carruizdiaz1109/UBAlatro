package edu.fiuba.algo3;

public class CartaPoker {
    private int valor;
    private String palo;
    private int puntosAdicionales;
    private int multiplicadorAdicional;

    public CartaPoker(int valor, String palo){
        this.valor = valor;
        this.palo = palo;
        this.puntosAdicionales = 0;
        this.multiplicadorAdicional = 0;
    }
    public CartaPoker comprar() {
        return this;
    }
}
