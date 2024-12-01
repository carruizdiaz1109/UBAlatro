package edu.fiuba.algo3.modelo.entidades.jugadas;

import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;
import edu.fiuba.algo3.modelo.entidades.Puntaje;
import java.util.List;

public class EscaleraColor extends Escalera {

    public EscaleraColor(List<CartaPoker> cartas) {
        super(cartas);
        this.puntaje = new Puntaje(100, 8);
    }

    @Override
    public boolean esJugada(List<CartaPoker> cartas) {
        if (!allCardsSameSuit(cartas)) return false;
        return super.esJugada(cartas);
    }

    private boolean allCardsSameSuit(List<CartaPoker> cartas) {
        for (CartaPoker carta : cartas) {
            if (!carta.esMismoPalo(cartas.get(0))) return false;
        }
        return true;
    }
}