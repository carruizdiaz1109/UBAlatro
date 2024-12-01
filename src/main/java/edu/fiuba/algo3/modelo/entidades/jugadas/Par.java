package edu.fiuba.algo3.modelo.entidades.jugadas;

import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;
import edu.fiuba.algo3.modelo.entidades.Jugada;
import edu.fiuba.algo3.modelo.entidades.Puntaje;

import java.util.Collections;
import java.util.List;

public class Par extends Jugada {

    public Par(List<CartaPoker> cartas) {
        super(cartas, new Puntaje(10, 2));
    }

    @Override
    public boolean esJugada(List<CartaPoker> cartas) {
        cartas.sort(Collections.reverseOrder()); // Ordena de mayor a menor usando compareTo
        for (int i = 0; i < cartas.size() - 1; i++) {
            if (cartas.get(i).esMismoValor(cartas.get(i + 1))) {
                cartasValidas = List.of(cartas.get(i), cartas.get(i + 1));
                return true;
            }
        }
        return false;
    }

    @Override
    protected List<CartaPoker> seleccionarCartasValidas(List<CartaPoker> cartas) {
        cartas.sort(Collections.reverseOrder());
        for (int i = 0; i < cartas.size() - 1; i++) {
            if (cartas.get(i).esMismoValor(cartas.get(i + 1))) {
                return List.of(cartas.get(i), cartas.get(i + 1));
            }
        }
        return List.of();
    }
}
