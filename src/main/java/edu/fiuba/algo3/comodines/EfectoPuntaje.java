package edu.fiuba.algo3.comodines;

import edu.fiuba.algo3.Comodin;
import edu.fiuba.algo3.Jugada;
import edu.fiuba.algo3.Puntaje;

public class EfectoPuntaje extends Comodin {

    public EfectoPuntaje(Puntaje puntaje, String nombre, String descripcion) {
        super(puntaje,  nombre,  descripcion);
    }

    @Override
    public void aplicar(Jugada jugada) {
        jugada.aplicarComodin(this.puntaje);
    }
}
