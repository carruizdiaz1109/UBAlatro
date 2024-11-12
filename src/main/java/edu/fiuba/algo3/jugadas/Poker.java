package edu.fiuba.algo3.jugadas;

import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.Jugada;
import edu.fiuba.algo3.Puntaje;

import java.util.List;

public class Poker extends Jugada {

    public Poker(List<CartaPoker> cartas) {
        super(cartas, new Puntaje(60, 7));
    }

    @Override
    public boolean esJugada(List<CartaPoker> cartas) {
        return true;
    }

    @Override
    protected List<CartaPoker> seleccionarCartasValidas(List<CartaPoker> cartas) { return cartas; }
}
