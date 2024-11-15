package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Mazo extends ConjuntoCartas {

    public Mazo(){
        super();
        inicializarMazo();
        mezclar();
    }

    protected void inicializarMazo(){
        this.cartas.clear();
        Palo[] palos = Palo.values();
        int[] valores = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        for (Palo palo : palos) {
            for (int valor : valores) {
                this.cartas.add(new CartaPoker(valor, palo));
            }
        }
    }

    protected void mezclar() {
        Collections.shuffle(cartas);
    }

    public CartaPoker darCarta() {
        if (cartas.isEmpty()) {
            throw new ErrorMazoVacio();
        }
        return cartas.remove(0);
    }
}
