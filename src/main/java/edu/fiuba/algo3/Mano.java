package edu.fiuba.algo3;

import java.util.ArrayList;

public class Mano {

    private ArrayList<CartaPoker> cartas;
    private ArrayList<CartaPoker> seleccionadas;
    private int cantidadCartas;
    private int capacidad;

    public Mano() {
        this.cartas = new ArrayList<CartaPoker>();
        this.seleccionadas = new ArrayList<CartaPoker>();
        this.cantidadCartas = 0;
        this.capacidad = 8;
    }

    public void eliminarCartas() {
        cartas.clear();
    }

    public void agregarCarta(CartaPoker carta) {
        if (cantidadCartas < capacidad) {
            cartas.add(carta);
            cantidadCartas++;
        }
    }

    public boolean manoLlena() {
        return cantidadCartas == capacidad;
    }

    public void seleccionarCarta(CartaPoker carta) {
        seleccionadas.add(carta);
    }

    public void deseleccionarCarta(CartaPoker carta) {
        seleccionadas.remove(carta);
    }

    public void ordenarCartas() {

    }

    public void descartar(ArrayList<CartaPoker> cartas) {

    }
}
