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

    public boolean compararCon(ConjuntoCartas otroConjunto) {
        if (this.cartas.size() != otroConjunto.cartas.size()) {
            return false;
        }
        for (int i = 0; i < this.cartas.size(); i++) {
            CartaPoker carta1 = this.cartas.get(i);
            CartaPoker carta2 = otroConjunto.cartas.get(i);
            if (!carta1.compararCartaCon(carta2)) {
                return false;
            }
        }
        return true;
    }
}
