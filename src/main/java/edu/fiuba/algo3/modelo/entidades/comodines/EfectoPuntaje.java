package edu.fiuba.algo3.modelo.entidades.comodines;

import edu.fiuba.algo3.modelo.entidades.Jugada;
import edu.fiuba.algo3.modelo.entidades.Puntaje;
import edu.fiuba.algo3.modelo.interfaces.Aleatoriedad;

public class EfectoPuntaje extends Comodin {

    public EfectoPuntaje(Puntaje puntaje, String nombre, String descripcion, Aleatoriedad unaAleatoriedad) {
        super(puntaje,  nombre,  descripcion, unaAleatoriedad);
    }

    @Override
    public void aplicar(Jugada jugada) {
        if(seAplica()) {
            jugada.aplicarComodin(this.puntaje);
        }
    }
}
