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
        this.cartas = cartas;
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

    public void seleccionarCarta(CartaPoker carta) { seleccionadas.add(carta); }

    public void deseleccionarCarta(int indice) {
        if (indice >= 0 && indice < cartas.size()) {
            CartaPoker carta = cartas.get(indice);
            seleccionadas.remove(carta);
        }
    }

    public ArrayList<CartaPoker> obtenerCartasSeleccionadas() {
        return this.seleccionadas;
    }

    /*
    public void ordenarCartas() {

    }
    */

    public void descartar() {
        if(!seleccionadas.isEmpty()) seleccionadas.clear();
    }

    public boolean compararSeleccionadasCon(ArrayList<CartaPoker> otrasSeleccionadas) {
        if (this.seleccionadas.size() != otrasSeleccionadas.size()) {
            return false;
        }
        for (int i = 0; i < this.seleccionadas.size(); i++) {
            CartaPoker carta1 = this.seleccionadas.get(i);
            CartaPoker carta2 = otrasSeleccionadas.get(i);
            if (!carta1.compararCartaCon(carta2)) {
                return false;
            }
        }
        return true;
    }
}
