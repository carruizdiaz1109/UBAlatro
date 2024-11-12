package edu.fiuba.algo3.jugadas;

import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.Jugada;
import edu.fiuba.algo3.Puntaje;

import java.util.List;

public class EscaleraColor extends Jugada {

    public EscaleraColor(List<CartaPoker> cartas) {
        super(cartas, new Puntaje(100, 8));
    }

    @Override
    public boolean esJugada(List<CartaPoker> cartas) {
        return false;
    }

    @Override
    protected List<CartaPoker> seleccionarCartasValidas(List<CartaPoker> cartas) { return cartas; }
}
