package edu.fiuba.algo3.modelo.entidades.cartas;

import edu.fiuba.algo3.modelo.entidades.Palo;
import edu.fiuba.algo3.modelo.entidades.Puntaje;
import edu.fiuba.algo3.modelo.entidades.Valor;

import java.util.Collections;
import java.util.List;

public class Numero extends CartaPoker {

    public Numero(Valor valor, Palo palo) {
        super(valor, palo);
    }

    @Override
    public Puntaje obtenerPuntaje() {
        return new Puntaje(valor.valor(), 1);
    }

    @Override
    public List<Integer> obtenerValoresPosibles() {
        return Collections.singletonList(this.valor.valor());
    }
}
