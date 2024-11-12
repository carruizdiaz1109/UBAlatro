package edu.fiuba.algo3.jugadas;

import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.Jugada;
import edu.fiuba.algo3.Puntaje;

import java.util.List;

public class Color extends Jugada {

    public Color(List<CartaPoker> cartas) {
        super(cartas, new Puntaje(35, 4));
    }

    @Override
    public boolean esJugada(List<CartaPoker> cartas) {
        return false;
    }

    @Override
    protected List<CartaPoker> seleccionarCartasValidas(List<CartaPoker> cartas) { return cartas; }
}
