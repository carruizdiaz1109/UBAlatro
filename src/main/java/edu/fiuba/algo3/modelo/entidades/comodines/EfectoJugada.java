package edu.fiuba.algo3.modelo.entidades.comodines;

import edu.fiuba.algo3.modelo.entidades.Jugada;
import edu.fiuba.algo3.modelo.entidades.Puntaje;
import edu.fiuba.algo3.modelo.interfaces.Aleatoriedad;

public class EfectoJugada extends Comodin {

    private final Class <? extends Jugada> jugadaAAplicar;

    public EfectoJugada(Class <? extends Jugada> unaJugadaAAplicar, Puntaje puntaje, String nombre, String descripcion, Aleatoriedad unaAleatoriedad) {
        super(puntaje, nombre, descripcion, unaAleatoriedad);
        this.jugadaAAplicar = unaJugadaAAplicar;
    }

    @Override
    public void aplicar(Jugada unaJugada) {
        if (this.jugadaAAplicar.isInstance(unaJugada)) {
            unaJugada.aplicarComodin(this.puntaje);
        }
    }
}
