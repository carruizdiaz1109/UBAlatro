package edu.fiuba.algo3.modelo.entidades.jugadas;

import edu.fiuba.algo3.modelo.entidades.*;
import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EscaleraReal extends Jugada {

    private static final List<Integer> ROYAL_STRAIGHT = List.of(10, 11, 12, 13, 14);

    public EscaleraReal(List<CartaPoker> cartas) {
        super(cartas, new Puntaje(100, 8));
    }

    @Override
    public boolean esJugada(List<CartaPoker> cartas) {
        if (cartas.size() != 5) return false; // Must have exactly 5 cards

        // Ensure all cards have the same suit
        Palo paloInicial = cartas.get(0).palo;
        for (CartaPoker carta : cartas) {
            if (!carta.esMismoPalo(cartas.get(0))) return false; // Different suit
        }

        // Generate all possible sequences from card values
        List<List<Integer>> posiblesSecuencias = generarSecuencias(cartas);

        // Check if any sequence forms a straight flush
        for (List<Integer> secuencia : posiblesSecuencias) {
            if (esEscaleraReal(secuencia)) {
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

    private boolean esEscaleraReal(List<Integer> valores) {
        Collections.sort(valores); // Sort values
        return valores.equals(ROYAL_STRAIGHT);
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
