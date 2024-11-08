package edu.fiuba.algo3;

import java.util.List;

public class Mano {

    private List<CartaPoker> cartas;
    private int cantidadCartas;
    private int capacidad;

    public Mano(List<CartaPoker> cartas) {
        this.cartas = cartas;
        this.cantidadCartas = cartas.size();
        this.capacidad = 8;
    }

    public void recibirCarta(CartaPoker carta) {
        if (cantidadCartas < capacidad) {
            cartas.add(carta);
            cantidadCartas++;
        }
    }

    public boolean manoLlena() {
        return cantidadCartas == capacidad;
    }
    /*
    public seleccionarCarta() {

    }

    public ordenarCartas() {

    }

    public descartar(List<CartaPoker> cartas) {

    }
    */
}
