package edu.fiuba.algo3.jugadas;

import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.Jugada;
import edu.fiuba.algo3.Puntaje;

import java.util.Collections;
import java.util.List;

public class CartaAlta extends Jugada {

    public CartaAlta(List<CartaPoker> cartas) {
        super(cartas, new Puntaje(5, 1));
    }

    @Override
    public boolean esJugada(List<CartaPoker> cartas) {
        cartasValidas = seleccionarCartasValidas(cartas);
        return true;
    }

    @Override
    protected List<CartaPoker> seleccionarCartasValidas(List<CartaPoker> cartas) {
        cartas.sort(Collections.reverseOrder());
        return List.of(cartas.get(0));
    }
}
