package edu.fiuba.algo3.modelo.interfaces;

import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;

import java.util.List;

public interface Evaluable {

    boolean esJugada(List<CartaPoker> cartas);
}
