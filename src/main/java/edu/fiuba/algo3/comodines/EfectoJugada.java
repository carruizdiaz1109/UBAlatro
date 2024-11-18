package edu.fiuba.algo3.comodines;

import edu.fiuba.algo3.Puntaje;
import edu.fiuba.algo3.Jugada;

public class EfectoJugada {

    private final Class <? extends Jugada> jugadaAAplicar;
    private final Puntaje puntaje;

    public EfectoJugada(Class <? extends Jugada> unaJugadaAAplicar, int valor, int multiplicador) {
        this.jugadaAAplicar = unaJugadaAAplicar;
        this.puntaje = new Puntaje(valor, multiplicador);
    }

    public void aplicar(Jugada unaJugada) {
        if (this.jugadaAAplicar.isInstance(unaJugada)) {
            unaJugada.aplicarComodin(this.puntaje);
        }
    }
}
