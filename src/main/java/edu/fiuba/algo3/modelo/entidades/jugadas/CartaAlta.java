package edu.fiuba.algo3.modelo.entidades.jugadas;

import edu.fiuba.algo3.modelo.entidades.CartaPoker;
import edu.fiuba.algo3.modelo.entidades.Jugada;
import edu.fiuba.algo3.modelo.entidades.Puntaje;

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
