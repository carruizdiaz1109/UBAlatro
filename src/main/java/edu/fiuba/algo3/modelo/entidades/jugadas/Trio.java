package edu.fiuba.algo3.modelo.entidades.jugadas;

import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;
import edu.fiuba.algo3.modelo.entidades.Jugada;
import edu.fiuba.algo3.modelo.entidades.Puntaje;

import java.util.Collections;
import java.util.List;

public class Trio extends Jugada {
    public Trio(List<CartaPoker> cartas) {
        super(cartas, "trio");
    }

    @Override
    public boolean esJugada(List<CartaPoker> cartas) {
        cartas.sort(Collections.reverseOrder()); // Ordena de mayor a menor usando compareTo

        for (int i = 0; i < cartas.size() - 2; i++) {
            if (cartas.get(i).esMismoValor(cartas.get(i + 1)) && cartas.get(i).esMismoValor(cartas.get(i + 2))) {
                cartasValidas = List.of(cartas.get(i), cartas.get(i + 1), cartas.get(i + 2));
                return true;
            }
        }
        return false;
    }

    @Override
    protected List<CartaPoker> seleccionarCartasValidas(List<CartaPoker> cartas) {
        cartas.sort(Collections.reverseOrder());
        for (int i = 0; i < cartas.size() - 2; i++) {
            if (cartas.get(i).esMismoValor(cartas.get(i + 1)) && cartas.get(i).esMismoValor(cartas.get(i + 2))) {
                return List.of(cartas.get(i), cartas.get(i + 1), cartas.get(i + 2));
            }
        }
        return List.of();
    }
}
