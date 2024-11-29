package edu.fiuba.algo3.comodines;

import edu.fiuba.algo3.Jugada;
import edu.fiuba.algo3.Puntaje;

public class EfectoJugada extends Comodin {

    private final Class <? extends Jugada> jugadaAAplicar;

    public EfectoJugada(Class <? extends Jugada> unaJugadaAAplicar, Puntaje puntaje, String nombre, String descripcion, Aleatoreidad unaAleatoreidad) {
        super(puntaje, nombre, descripcion, unaAleatoreidad);
        this.jugadaAAplicar = unaJugadaAAplicar;
    }

    @Override
    public void aplicar(Jugada unaJugada) {
        if (this.jugadaAAplicar.isInstance(unaJugada)) {
            unaJugada.aplicarComodin(this.puntaje);
        }
    }
}
