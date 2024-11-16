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

    public Mazo(ArrayList<CartaPoker> cartas){
        this.cartas = cartas;
    }

    protected void inicializarMazo(){
        this.cartas.clear();
        Palo[] palos = Palo.values();
        Valor[] valores = Valor.values();
        for (Palo palo : palos) {
            for (Valor valor : valores) {
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
