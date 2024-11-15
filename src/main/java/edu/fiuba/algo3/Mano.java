package edu.fiuba.algo3;

import java.util.ArrayList;

public class Mano extends ConjuntoCartas{

    private ArrayList<CartaPoker> seleccionadas;
    private int capacidad;

    public Mano() {
        super();
        this.seleccionadas = new ArrayList<CartaPoker>();
        this.capacidad = 8;
    }

    public Mano(ArrayList<CartaPoker> cartas) {
        super();
        this.seleccionadas = new ArrayList<CartaPoker>();
        this.capacidad = 8;
    }

    public boolean manoLlena() {
        return cartas.size() == capacidad;
    }

    public void rellenarse(Mazo mazo) {
        while (!manoLlena() && mazo.tieneCartas()) {
            this.agregarCarta(mazo.darCarta());
        }
    }

    public void seleccionarCarta(int indice) {
        if (indice >= 0 && indice < cartas.size()) {
            CartaPoker carta = cartas.get(indice);
            if (!seleccionadas.contains(carta)) {
                seleccionadas.add(carta);
            }
        }
    }

    public void deseleccionarCarta(int indice) {
        if (indice >= 0 && indice < cartas.size()) {
            CartaPoker carta = cartas.get(indice);
            seleccionadas.remove(carta);
        }
    }

    /*
    public void ordenarCartas() {

    }
    */

    public void descartar() {
        if(!seleccionadas.isEmpty()) seleccionadas.clear();
    }
}
