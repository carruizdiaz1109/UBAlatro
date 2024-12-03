package edu.fiuba.algo3.modelo.entidades.jugadas;

import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;
import edu.fiuba.algo3.modelo.entidades.Jugada;
import edu.fiuba.algo3.modelo.entidades.Puntaje;
import java.util.List;

public class Descarte extends Jugada {
    protected Puntaje puntajeComodin;

    public Descarte(List<CartaPoker> cartas) {
        super(cartas,"Descarte");
    }

    @Override
    public boolean esJugada(List<CartaPoker> cartas) {
        return false;
    }

    @Override
    protected List<CartaPoker> seleccionarCartasValidas(List<CartaPoker> cartas) {
        return cartas;
    }
}
