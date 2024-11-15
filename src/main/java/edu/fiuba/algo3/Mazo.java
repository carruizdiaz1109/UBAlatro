package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Mazo {
    private ArrayList<CartaPoker> cartas;
    private int cantidadCartas;

    public Mazo(){
        cartas = new ArrayList<>();
        inicializarMazo();
        mezclar();
    }

    public Mazo(ArrayList<CartaPoker> cartas){
        this.cartas = cartas;
        cantidadCartas = cartas.size();
    }

    protected void inicializarMazo(){
        this.cartas.clear();
        Palo[] palos = Palo.values();
        int[] valores = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        for (Palo palo : palos) {
            for (int valor : valores) {
                this.cartas.add(new CartaPoker(valor, palo));
                this.cantidadCartas++;
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

    public int getCantidadCartas() {
        return cantidadCartas;
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

    public boolean compararMazoCon(Mazo otroMazo) {
        if (this.cartas.size() != otroMazo.cartas.size()) {
            return false;
        }


        for (int i = 0; i < this.cartas.size(); i++) {
            CartaPoker carta1 = this.cartas.get(i);
            CartaPoker carta2 = otroMazo.cartas.get(i);


            if (!carta1.compararCartaCon(carta2)) {
                return false;
            }
        }
        return true;
    }


}
