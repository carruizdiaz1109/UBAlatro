package edu.fiuba.algo3.jugadas;

import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.Jugada;
import edu.fiuba.algo3.Puntaje;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DoblePar extends Jugada {

    public DoblePar(List<CartaPoker> cartas) {
        super(cartas, new Puntaje(20, 2));
    }

    @Override
    public boolean esJugada(List<CartaPoker> cartas) {
        cartasValidas = seleccionarCartasValidas(cartas);
        return cartasValidas.size() == 4;
    }

    @Override
    protected List<CartaPoker> seleccionarCartasValidas(List<CartaPoker> cartas) {
        cartas.sort(Collections.reverseOrder());
        List<CartaPoker> paresEncontrados = new ArrayList<>();

        for (int i = 0; i < cartas.size() - 1; i++) {
            if (cartas.get(i).esMismoValor(cartas.get(i + 1)) && !paresEncontrados.contains(cartas.get(i))) {
                paresEncontrados.add(cartas.get(i));
                paresEncontrados.add(cartas.get(i + 1));
                i++;
                if (paresEncontrados.size() == 4) {
                    break;
                }
            }
        }
        return paresEncontrados;
    }
}
