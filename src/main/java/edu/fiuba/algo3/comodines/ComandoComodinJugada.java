package edu.fiuba.algo3.comodines;

import edu.fiuba.algo3.Puntaje;
import edu.fiuba.algo3.Jugada;

public class ComandoComodinJugada implements ComandoComodin {
    private Jugada jugada;
    private Puntaje puntaje;

    public ComandoComodinJugada(Jugada jugada, int valor, int multiplicador) {
        this.jugada = jugada;
        int asd = jugada.calcularPuntaje();
        //this.jugada = jugada;
        this.puntaje = new Puntaje(0, 3);
        this.puntaje.sumarPuntaje(this.puntaje);
        //this.jugada.aplicarComodin();
        //this.puntaje.incrementarMultiplicador(multiplicador);
    }

    @Override
    public void ejecutar() {
        jugada.aplicarComodin(puntaje);
    }
}

