package edu.fiuba.algo3.comodines;

import edu.fiuba.algo3.Comodin;
import edu.fiuba.algo3.Jugada;

public class EfectoPuntaje extends Comodin {

    public EfectoPuntaje(int valor, int multiplicador,String nombre, String descripcion) {
        super(valor, multiplicador,  nombre,  descripcion);
    }

    @Override
    public void aplicar(Jugada jugada) {
        jugada.aplicarComodin(this.puntaje);
    }
}
