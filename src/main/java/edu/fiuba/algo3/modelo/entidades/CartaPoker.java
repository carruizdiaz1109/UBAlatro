package edu.fiuba.algo3.modelo.entidades;

import edu.fiuba.algo3.modelo.excepciones.CartaNulaError;
import edu.fiuba.algo3.modelo.excepciones.PuntajeNuloError;

public class CartaPoker implements Comparable<CartaPoker>{

    private final Valor valor;
    private final Palo palo;
    private Puntaje puntaje;
    private final Puntaje puntajeComodin;

    public CartaPoker(Valor valor, Palo palo){
        this.valor = valor;
        this.palo = palo;
        this.puntaje = new Puntaje( this.valor.valor(), 1);
        this.puntajeComodin = new Puntaje(0,1);
    }

    public CartaPoker comprar() {
        return this;
    }

    public void modificarPuntaje(Puntaje unPuntaje) {
        verificarPuntajeNulo(unPuntaje);
        this.puntaje = this.puntaje.sumarPuntaje(unPuntaje);
    }

    public int calcularPuntaje() {
        return this.puntaje.calcularPuntaje();
    }

    public int sumarValorCon(int otroValor) { return this.valor.valor() + otroValor; }

    public boolean esMismoValor(CartaPoker otraCarta) {
        verificarCartaNula(otraCarta);
        return this.valor == otraCarta.valor;
    }

    public boolean esMismoPalo(CartaPoker otraCarta) {
        verificarCartaNula(otraCarta);
        return this.palo.equals(otraCarta.palo);
    }

    public boolean esConsecutiva(CartaPoker otraCarta) {
        verificarCartaNula(otraCarta);
        return Math.abs(this.valor.valor() - otraCarta.valor.valor()) == 1;
    }

    public boolean compararCartaCon(CartaPoker otraCarta) {
        verificarCartaNula(otraCarta);
        return (this.valor == otraCarta.valor && this.palo.equals(otraCarta.palo));
    }

    @Override
    public int compareTo(CartaPoker otraCarta) { return Integer.compare(this.valor.valor(), otraCarta.valor.valor()); }

    public ConjuntoCartas obtenerEscalera() {
        ConjuntoCartas escalera = new ConjuntoCartas();
        escalera.agregarCarta(this);

        Valor[] valores = Valor.values();
        int indexActual = this.valor.ordinal(); // Posición actual del valor en el enum

        for (int i = 1; i <= 4; i++) {
            int nuevoIndex = indexActual + i;
            if (nuevoIndex >= valores.length) {
                break; // Si supera el rango de valores, se frena
            }

            Valor nuevoValor = valores[nuevoIndex];
            CartaPoker nuevaCarta = new CartaPoker(nuevoValor, this.palo);
            escalera.agregarCarta(nuevaCarta);
        }

        return escalera;
    }

    public void aplicarComodin(Puntaje unPuntaje) {
        verificarPuntajeNulo(unPuntaje);
        this.puntajeComodin.sumarPuntaje(unPuntaje);
    }

    public void aplicarTarot(Puntaje unPuntaje) {
        verificarPuntajeNulo(unPuntaje);
        this.puntaje = unPuntaje;}

    public String getNombreArchivo() {
        String valorTexto = String.valueOf(valor.valor()); // Por ejemplo, "7" para un 7
        String paloTexto;

        switch (palo) {
            case CORAZONES:
                paloTexto = "C";
                break;
            case DIAMANTES:
                paloTexto = "D";
                break;
            case PICAS:
                paloTexto = "P";
                break;
            case TREBOLES:
                paloTexto = "T";
                break;
            default:
                throw new IllegalArgumentException("Palo desconocido: " + palo);
        }

        return valorTexto + paloTexto + ".png"; // Ejemplo: "7H.png"
    }

    private void verificarCartaNula(CartaPoker otraCarta) {
        if(otraCarta == null) throw new CartaNulaError();
    }

    public void verificarPuntajeNulo(Puntaje unPuntaje) {
        if(unPuntaje == null) throw new PuntajeNuloError();
    }
}

