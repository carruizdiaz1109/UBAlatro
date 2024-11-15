package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;

public abstract class ConjuntoCartas {
    protected List<CartaPoker> cartas;

    public ConjuntoCartas() {
        this.cartas = new ArrayList<>();
    }

    public ConjuntoCartas(List<CartaPoker> cartas) {
        this.cartas = cartas;
    }

    public List<CartaPoker> getCartas() {
        return cartas;
    }

    public boolean tieneCartas() {
        return !cartas.isEmpty();
    }

    public void agregarCarta(CartaPoker carta) {
        cartas.add(carta);
    }

    public void eliminarCartas() {
        cartas.clear();
    }
}
