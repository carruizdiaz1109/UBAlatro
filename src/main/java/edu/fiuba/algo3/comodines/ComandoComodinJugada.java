package edu.fiuba.algo3.comodines;

import edu.fiuba.algo3.Puntaje;
import edu.fiuba.algo3.Jugada;

public class ComandoComodinJugada implements ComandoComodin {
    private final Jugada jugada;
    private final Puntaje puntaje;

    public ComandoComodinJugada(Jugada jugada, int valor, int multiplicador) {
        this.jugada = jugada;
        this.puntaje = new Puntaje(valor, multiplicador);
    }

    @Override
    public void ejecutar() {
        jugada.aplicarComodin(puntaje);
    }
}

