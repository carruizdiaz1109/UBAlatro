package edu.fiuba.algo3.comodines;

import edu.fiuba.algo3.Jugada;
import edu.fiuba.algo3.Puntaje;

public class EfectoPuntaje {

    private final Jugada jugada;
    private final Puntaje puntaje;

    public EfectoPuntaje(Jugada jugada, int valor, int multiplicador) {
        this.jugada = jugada;
        this.puntaje = new Puntaje(valor, multiplicador);
    }

    public void aplicar(Jugada jugada) {
        jugada.aplicarComodin(this.puntaje);
    }

}
