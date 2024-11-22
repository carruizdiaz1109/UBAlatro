package edu.fiuba.algo3.comodines;

import edu.fiuba.algo3.Jugada;
import edu.fiuba.algo3.Puntaje;

public class EfectoPuntaje extends Comodin {

    public EfectoPuntaje(Puntaje puntaje, String nombre, String descripcion,Aleatoreidad unaAleatoreidad) {
        super(puntaje,  nombre,  descripcion, unaAleatoreidad);
    }

    @Override
    public void aplicar(Jugada jugada) {
        if(seAplica()) {
            jugada.aplicarComodin(this.puntaje);
        }
    }
}
