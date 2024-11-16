package edu.fiuba.algo3;

public class CartaPoker implements Comparable<CartaPoker>{

    private static int contadorId = 1;

    private Valor valor;
    private Palo palo;
    private Puntaje puntaje;
    private Tarot tarot;

    public CartaPoker(Valor valor, Palo palo){
        this.valor = valor;
        this.palo = palo;
        this.puntaje = new Puntaje( this.valor.valor(), 1);
        this.tarot = new Tarot();
    }

    public CartaPoker comprar() {
        return this;
    }

    public void modificarPuntaje(Puntaje unPuntaje) {
        this.puntaje = this.puntaje.sumarPuntaje(unPuntaje);
    }

    public int calcularPuntaje() {
        return this.puntaje.calcularValor();
    }

    public boolean esMayorA(CartaPoker otraCarta) {
        return (this.valor.valor() > otraCarta.valor.valor());
    }

    public int sumarValorCon(int otroValor) { return this.valor.valor() + otroValor; }

    public boolean esMismoValor(CartaPoker otraCarta) { return this.valor == otraCarta.valor; }

    public boolean esMismoPalo(CartaPoker otraCarta) { return this.palo.equals(otraCarta.palo); }

    public boolean esConsecutiva(CartaPoker otraCarta) { return Math.abs(this.valor.valor() - otraCarta.valor.valor()) == 1; }

    public boolean compararCartaCon(CartaPoker otraCarta) {
        return (this.valor == otraCarta.valor && this.palo.equals(otraCarta.palo));
    }

    public void activarTarot(Tarot tarot) {
        this.puntaje = tarot.modificarPuntaje(puntaje);
        this.tarot = tarot;
    }

    @Override
    public int compareTo(CartaPoker otraCarta) { return Integer.compare(this.valor.valor(), otraCarta.valor.valor()); }
}
