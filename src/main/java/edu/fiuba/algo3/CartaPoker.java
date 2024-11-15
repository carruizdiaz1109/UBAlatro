package edu.fiuba.algo3;

public class CartaPoker implements Comparable<CartaPoker>{

    private static int contadorId = 1;

    private int valor;
    private Palo palo;
    private Puntaje puntaje;

    public CartaPoker(int valor, Palo palo){
        this.valor = valor;
        this.palo = palo;
        this.puntaje = new Puntaje( this.valor, 1);
    }

    public CartaPoker comprar() {
        return this;
    }

    public void modificarPuntaje(Puntaje unPuntaje) {
        this.puntaje = this.puntaje.sumarPuntaje(unPuntaje);
    }

    public Puntaje calcularPuntaje() {
        return this.puntaje.calcularPuntaje();
    }

    public boolean esMayorA(CartaPoker otraCarta) {
        return (this.valor > otraCarta.valor);
    }

    public int sumarValorCon(int otroValor) { return this.valor + otroValor; }

    public boolean esMismoValor(CartaPoker otraCarta) { return this.valor == otraCarta.valor; }

    public boolean esMismoPalo(CartaPoker otraCarta) { return this.palo.equals(otraCarta.palo); }

    public boolean esConsecutiva(CartaPoker otraCarta) { return Math.abs(this.valor - otraCarta.valor) == 1; }

    public boolean compararCartaCon(CartaPoker otraCarta) {
        return (this.valor == otraCarta.valor && this.palo.equals(otraCarta.palo));
    }


    @Override
    public int compareTo(CartaPoker otraCarta) { return Integer.compare(this.valor, otraCarta.valor); }
}
