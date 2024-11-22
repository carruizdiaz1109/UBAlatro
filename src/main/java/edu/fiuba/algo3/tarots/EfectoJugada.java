package edu.fiuba.algo3.tarots;

import edu.fiuba.algo3.ErrorTarotDistintaJugada;
import edu.fiuba.algo3.Jugada;
import edu.fiuba.algo3.Puntaje;
import edu.fiuba.algo3.Tarot;

public class EfectoJugada extends Tarot {

    public EfectoJugada(String nombre, String descripcion, Puntaje efecto, String ejemplar) {
        super(nombre, descripcion, efecto, "mano", ejemplar);
    }

    @Override
    public void aplicar(Object objeto) {
        if (objeto instanceof Jugada) {
            aplicar((Jugada) objeto);
        } else {
            throw new IllegalArgumentException("El objeto no es una Jugada");
        }
    }

    public void aplicar(Jugada unaJugada) {
        if(sePuedeUtilizar(unaJugada)){
            unaJugada.aplicarTarot(this.puntaje);
        }else{
            throw new ErrorTarotDistintaJugada();
        }
    }

    private boolean sePuedeUtilizar(Jugada unaJugada){
        return this.ejemplar.equalsIgnoreCase(unaJugada.getClass().getSimpleName());
    }

}


