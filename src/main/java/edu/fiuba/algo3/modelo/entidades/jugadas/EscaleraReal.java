package edu.fiuba.algo3.modelo.entidades.jugadas;

import edu.fiuba.algo3.modelo.entidades.*;
import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;

import java.util.List;
import java.util.Collections;

public class EscaleraReal extends EscaleraColor {

    private static final List<Integer> ROYAL_STRAIGHT = List.of(10, 11, 12, 13, 14);

    public EscaleraReal(List<CartaPoker> cartas, String nombreJugada) {
        super(cartas, nombreJugada);
    }

    @Override
    protected boolean esEscalera(List<Integer> valores) {
        Collections.sort(valores);
        return valores.equals(ROYAL_STRAIGHT);
    }
}
