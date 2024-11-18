package edu.fiuba.algo3.comodines;

import edu.fiuba.algo3.Puntaje;
import edu.fiuba.algo3.Jugada;

public class EfectoJugada{

    private final Jugada jugadaAAplicar;
    private final Puntaje puntaje;

    public EfectoJugada(Jugada unaJugadaAAplicar, int valor, int multiplicador) {
        this.jugadaAAplicar = unaJugadaAAplicar;
        this.puntaje = new Puntaje(valor, multiplicador);
    }

    public void aplicar(Jugada unaJugada) {
        if (unaJugada == this.jugadaAAplicar) {
            unaJugada.aplicarComodin(this.puntaje);
        }
    }
}
