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
        String[] palos = {"Pica", "Diamantes", "Corazones", "Treboles"};
        int[] valores = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        for (String palo : palos) {
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
        if (cantidadCartas == 0) {
            throw new ErrorMazoVacio();
        }
        cantidadCartas--;
        return cartas.remove(0);
    }

    public List<CartaPoker> getCartas() {
        return cartas;
    }

    public List<CartaPoker> rellenar(int cantidad){
        List<CartaPoker> cartasRellenadas = new ArrayList<>();

        for(int i = 0 ; i < cantidad && cantidadCartas != 0; i++){
            cartasRellenadas.add(darCarta());
        }

        return cartasRellenadas;
    }
}
