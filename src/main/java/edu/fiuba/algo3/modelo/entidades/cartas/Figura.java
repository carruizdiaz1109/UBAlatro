package edu.fiuba.algo3.modelo.entidades.cartas;

import edu.fiuba.algo3.modelo.entidades.Palo;
import edu.fiuba.algo3.modelo.entidades.Puntaje;
import edu.fiuba.algo3.modelo.entidades.Valor;

public class Figura extends CartaPoker {

    public Figura(Valor valor, Palo palo) {
        super(valor, palo);
    }

    @Override
    public Puntaje obtenerPuntaje() {
        return new Puntaje(10, 1);
    }
}