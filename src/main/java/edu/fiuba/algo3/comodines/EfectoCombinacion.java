package edu.fiuba.algo3.comodines;

import edu.fiuba.algo3.Comodin;
import edu.fiuba.algo3.Jugada;
import java.util.ArrayList;
import java.util.List;

public class EfectoCombinacion extends Comodin {
    private final List<Comodin> combinaciones;

    public EfectoCombinacion(List <Comodin> lasCombinacines, int valor, int multiplicador) {
        super (valor, multiplicador);
        this.combinaciones = lasCombinacines;
    }


    public void aplicar(Jugada unaJugada) {
        for (Comodin comodin : this.combinaciones) {
            //unaJugada.aplicarComodin(comodin.puntaje);
        }
    }
}
