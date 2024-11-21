package edu.fiuba.algo3.comodines;


import edu.fiuba.algo3.Comodin;
import edu.fiuba.algo3.Jugada;
import edu.fiuba.algo3.Puntaje;


public class EfectoDescarte extends Comodin {

    private final Class<? extends Jugada> descarte;

    public EfectoDescarte(Class<? extends Jugada> unDescarte, Puntaje puntaje, String nombre, String descripcion) {
        super(puntaje, nombre, descripcion);
        this.descarte = unDescarte;
    }

    @Override
    public void aplicar(Jugada unDescarte) {
        if (this.descarte.isInstance(unDescarte)) {
            unDescarte.aplicarComodin(this.puntaje);
        }
    }

}
