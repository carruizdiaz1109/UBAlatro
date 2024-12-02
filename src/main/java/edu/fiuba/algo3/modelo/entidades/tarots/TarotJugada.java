package edu.fiuba.algo3.modelo.entidades.tarots;

import edu.fiuba.algo3.modelo.excepciones.TarotDistintaJugadaError;
import edu.fiuba.algo3.modelo.entidades.Jugada;
import edu.fiuba.algo3.modelo.entidades.Puntaje;

public class TarotJugada extends Tarot {

    public TarotJugada(String nombre, String descripcion, Puntaje efecto, String ejemplar) {
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
            throw new TarotDistintaJugadaError();
        }
    }

    private boolean sePuedeUtilizar(Jugada unaJugada){
        return this.ejemplar.equalsIgnoreCase(unaJugada.getClass().getSimpleName());
    }

}


