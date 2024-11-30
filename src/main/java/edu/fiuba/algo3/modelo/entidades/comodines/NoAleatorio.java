package edu.fiuba.algo3.modelo.entidades.comodines;

import edu.fiuba.algo3.modelo.interfaces.Aleatoriedad;

public class NoAleatorio implements Aleatoriedad {

    public NoAleatorio() {}

    @Override
    public boolean seAplica() {
        return true; // Siempre se aplica
    }
}
