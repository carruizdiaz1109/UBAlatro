package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Mazo {
    private List<CartaPoker> cartas;
    private int cantidadCartas;

    public Mazo(){
        cartas = new ArrayList<>();
        inicializarMazo();
        mezclar();
    }

    protected void inicializarMazo(){
        cartas.clear();
        Palo[] palos = Palo.values();
        int[] valores = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        for (Palo palo : palos) {
            for (int valor : valores) {
                cartas.add(new CartaPoker(valor, palo));
                cantidadCartas++;
            }
        }
    }

    public int getCantidadCartas() {
        return cantidadCartas;
    }

    protected void mezclar() {
        Collections.shuffle(cartas);
    }

    public CartaPoker darCarta() {
        CartaPoker carta = cartas.get(0);
        cartas.remove(0);
        return carta;
    }
}
