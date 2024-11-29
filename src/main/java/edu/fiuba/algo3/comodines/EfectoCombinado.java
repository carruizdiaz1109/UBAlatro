package edu.fiuba.algo3.comodines;

import edu.fiuba.algo3.Jugada;
import edu.fiuba.algo3.Puntaje;

import java.util.ArrayList;
import java.util.List;

public class EfectoCombinado extends Comodin {
    private final List<Comodin> combinaciones;

    public EfectoCombinado(String nombre, String descripcion, Aleatoreidad unaAleatoreidad) {
        super (new Puntaje(0, 1),  nombre, descripcion,unaAleatoreidad);
        this.combinaciones = new ArrayList<>();
    }

    public void agregar (Comodin comodin) {
        this.combinaciones.add(comodin);
    }

    public void eliminar (Comodin comodin) {
        this.combinaciones.remove(comodin);
    }

    public void aplicar(Jugada unaJugada) {
        for (Comodin comodin : this.combinaciones) {
            comodin.aplicar(unaJugada);
        }
    }
}
