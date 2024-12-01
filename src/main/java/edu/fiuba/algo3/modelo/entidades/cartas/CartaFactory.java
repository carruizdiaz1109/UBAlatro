package edu.fiuba.algo3.modelo.entidades.cartas;

import edu.fiuba.algo3.modelo.entidades.Palo;
import edu.fiuba.algo3.modelo.entidades.Valor;

public class CartaFactory {

    public static CartaPoker crearCarta(Valor valor, Palo palo) {
        if (valor == Valor.AS) {
            return new As(valor, palo);
        } else if (valor.ordinal() >= 10) {
            return new Figura(valor, palo);
        } else {
            return new Numero(valor, palo);
        }
    }
}

