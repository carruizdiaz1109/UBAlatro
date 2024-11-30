package edu.fiuba.algo3.modelo.entidades.jugadas;

import edu.fiuba.algo3.modelo.entidades.CartaPoker;
import edu.fiuba.algo3.modelo.entidades.Jugada;
import edu.fiuba.algo3.modelo.entidades.Puntaje;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;


public class FullHouse extends Jugada {

    public FullHouse(List<CartaPoker> cartas) {
        super(cartas, new Puntaje(40, 4));
    }

    @Override
    public boolean esJugada(List<CartaPoker> cartas) {
        List<CartaPoker> cartasOrdenadas = new ArrayList<>(cartas);
        cartasOrdenadas.sort(Collections.reverseOrder());

        // Almacena las cartas válidas encontradas
        List<CartaPoker> triple = new ArrayList<>();
        List<CartaPoker> par = new ArrayList<>();

        // Recorre la lista y busca un grupo de tres cartas del mismo valor (triple)
        for (int i = 0; i < cartas.size() - 2; i++) {
            if (cartas.get(i).esMismoValor(cartas.get(i + 1)) && cartas.get(i).esMismoValor(cartas.get(i + 2))) {
                triple = List.of(cartas.get(i), cartas.get(i + 1), cartas.get(i + 2));
                break;
            }
        }

        // Si no se encontró una triple, no es FullHouse
        if (triple.isEmpty()) {
            return false;
        }

        // Busca un par distinto de las cartas de la triple
        for (int i = 0; i < cartas.size() - 1; i++) {
            if (!triple.contains(cartas.get(i)) && cartas.get(i).esMismoValor(cartas.get(i + 1))) {
                par = List.of(cartas.get(i), cartas.get(i + 1));
                break;
            }
        }

        // Si se encontró una triple y un par, es un FullHouse
        if (!par.isEmpty()) {
            this.cartasValidas = new ArrayList<>();
            this.cartasValidas.addAll(triple);
            this.cartasValidas.addAll(par);
            return true;
        }

        return false;
    }

    @Override
    protected List<CartaPoker> seleccionarCartasValidas(List<CartaPoker> cartas) {
        // Si `esJugada` fue verdadero, `cartasValidas` ya debe contener todas las cartas de FullHouse
        if (this.cartasValidas != null && this.cartasValidas.size() == 5) {
            return this.cartasValidas;
        }

        return List.of();  // Devuelve una lista vacía si no es FullHouse
    }
}
