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

    public boolean esCantidadDeCartasSuficiente() {
        return cantidadCartas > 0;
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

    public ArrayList<CartaPoker> rellenar(int cantidad){
        ArrayList<CartaPoker> cartasRellenadas = new ArrayList<>();

        for(int i = 0 ; i < cantidad && cantidadCartas != 0; i++){
            cartasRellenadas.add(darCarta());
        }

        return cartasRellenadas;
    }

    public Mano repartir(){
        ArrayList<CartaPoker> cartasRepartidas = rellenar(8);
        Mano mano = new Mano(cartasRepartidas);
        return mano;
    }

    public void guardarCarta(CartaPoker carta) {
        cartas.add(carta);
        cantidadCartas++;
    }

}
