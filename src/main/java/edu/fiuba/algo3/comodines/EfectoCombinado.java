package edu.fiuba.algo3.comodines;

import edu.fiuba.algo3.Comodin;
import edu.fiuba.algo3.Jugada;
import edu.fiuba.algo3.Puntaje;

import java.util.List;

public class EfectoCombinado extends Comodin {
    private final List<Comodin> combinaciones;

    public EfectoCombinado(List <Comodin> lasCombinacines, String nombre, String descripcion) {
        super (new Puntaje(0, 1),  nombre, descripcion);
        this.combinaciones = lasCombinacines;
    }


    public void aplicar(Jugada unaJugada) {
        for (Comodin comodin : this.combinaciones) {
            comodin.aplicar(unaJugada);
        }
    }
}
