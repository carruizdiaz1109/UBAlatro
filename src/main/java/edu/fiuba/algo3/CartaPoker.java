package edu.fiuba.algo3;

public class CartaPoker {
    private static int contadorId = 1;

    private int valor;
    private String palo;
    private Puntaje puntaje;
    private int id;

    public CartaPoker(int valor, String palo){
        this.valor = valor;
        this.palo = palo;
        this.puntaje = new Puntaje( this.valor, 1);
        this.id = contadorId++;
    }

    public CartaPoker comprar() {
        return this;
    }

    public void modificarPuntaje(Puntaje unPuntaje) {
        this.puntaje = this.puntaje.sumarPuntaje(unPuntaje);
    }

    public int calcularPuntaje() {
        return this.puntaje.calcularPuntaje();
    }

    public boolean tieneId(int unId) {
        return (this.id == unId);
    }


}
