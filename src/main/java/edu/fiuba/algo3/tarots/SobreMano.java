package edu.fiuba.algo3.tarots;

import edu.fiuba.algo3.Jugada;
import edu.fiuba.algo3.Puntaje;
import edu.fiuba.algo3.Tarot;

public class SobreMano extends Tarot {

    public SobreMano(String nombre, String descripcion, Puntaje efecto, String ejemplar) {
        super(nombre, descripcion, efecto, "mano", ejemplar);
    }

    public void aplicar(Jugada unaJugada) {
        if(sePuedeUtilizar(unaJugada)){
            unaJugada.aplicarTarot(this.puntaje);
        }
    }

    private boolean sePuedeUtilizar(Jugada unaJugada){
        return this.ejemplar.equalsIgnoreCase(unaJugada.getClass().getSimpleName());
    }


}

