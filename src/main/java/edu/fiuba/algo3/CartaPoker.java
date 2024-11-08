package edu.fiuba.algo3;

public class CartaPoker {
    private int valor;
    private String palo;
    private Puntaje puntaje;
    int id;

    public CartaPoker(int valor, String palo, int unId){
        this.valor = valor;
        this.palo = palo;
        this.puntaje = new Puntaje( this.valor, 1);
        this.id = unId;
    }

    public CartaPoker comprar() {
        return this;
    }

    public void modificarPuntaje(int incremento) {
        this.puntaje.incrementarPuntos(incremento);
    }

    public int calcularPuntaje() {
        return this.puntaje.calcularPuntaje();
    }

    public boolean tieneId(int unId) {
        return (this.id == unId);
    }


}
