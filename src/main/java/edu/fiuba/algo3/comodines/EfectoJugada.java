package edu.fiuba.algo3.comodines;

import edu.fiuba.algo3.Comodin;
import edu.fiuba.algo3.Jugada;

public class EfectoJugada extends Comodin {

    private final Class <? extends Jugada> jugadaAAplicar;

    public EfectoJugada(Class <? extends Jugada> unaJugadaAAplicar, int valor, int multiplicador) {
        super(valor, multiplicador);
        this.jugadaAAplicar = unaJugadaAAplicar;
    }

    @Override
    public void aplicar(Jugada unaJugada) {
        if (this.jugadaAAplicar.isInstance(unaJugada)) {
            unaJugada.aplicarComodin(this.puntaje);
        }
    }
}
