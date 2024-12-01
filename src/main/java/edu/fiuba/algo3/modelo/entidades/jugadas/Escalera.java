package edu.fiuba.algo3.modelo.entidades.jugadas;

import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;
import edu.fiuba.algo3.modelo.entidades.Jugada;
import edu.fiuba.algo3.modelo.entidades.Puntaje;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class Escalera extends Jugada {

    public Escalera(List<CartaPoker> cartas) {
        super(cartas, new Puntaje(30, 4));
    }

    @Override
    public boolean esJugada(List<CartaPoker> cartas) {
        if (cartas.size() != 5) return false; // Must have exactly 5 cards

        // Generate all possible sequences from card values
        List<List<Integer>> posiblesSecuencias = generarSecuencias(cartas);

        // Check if any sequence forms a straight
        for (List<Integer> secuencia : posiblesSecuencias) {
            if (esEscalera(secuencia)) {
                cartasValidas = new ArrayList<>(cartas); // Save valid cards
                return true;
            }
        }
        return false;
    }

    @Override
    protected List<CartaPoker> seleccionarCartasValidas(List<CartaPoker> cartas) {
        return cartasValidas != null ? cartasValidas : List.of();
    }

    private boolean esEscalera(List<Integer> valores) {
        Collections.sort(valores); // Sort values
        for (int i = 0; i < valores.size() - 1; i++) {
            if (valores.get(i + 1) - valores.get(i) != 1) return false; // Not consecutive
        }
        return true;
    }

    private List<List<Integer>> generarSecuencias(List<CartaPoker> cartas) {
        List<List<Integer>> secuencias = new ArrayList<>();
        generarSecuenciasRecursivamente(cartas, 0, new ArrayList<>(), secuencias);
        return secuencias;
    }

    private void generarSecuenciasRecursivamente(List<CartaPoker> cartas, int index, List<Integer> actual, List<List<Integer>> secuencias) {
        if (index == cartas.size()) {
            secuencias.add(new ArrayList<>(actual));
            return;
        }
        for (int valor : cartas.get(index).obtenerValoresPosibles()) {
            actual.add(valor);
            generarSecuenciasRecursivamente(cartas, index + 1, actual, secuencias);
            actual.remove(actual.size() - 1); // Backtrack
        }
    }
}