package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Mazo extends ConjuntoCartas {

    public Mazo(){
        super();
    }

    public Mazo(ArrayList<CartaPoker> cartas){
        this.cartas = cartas;
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
