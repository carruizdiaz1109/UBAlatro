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

    public Mano(ArrayList<CartaPoker> cartas) {
        this.cartas = cartas;
        this.seleccionadas = new ArrayList<CartaPoker>();
        this.cantidadCartas = cartas.size();
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

    public boolean compararManoCon(Mano otraMano) {
        if (this.cartas.size() != otraMano.cartas.size()) {
            return false;
        }

        for (int i = 0; i < otraMano.cartas.size(); i++) {
            CartaPoker carta1 = this.cartas.get(i);
            CartaPoker carta2 = otraMano.cartas.get(i);

            if (!carta1.compararCartaCon(carta2)){
                return false;
            }
        }

        return true;
    }

}
