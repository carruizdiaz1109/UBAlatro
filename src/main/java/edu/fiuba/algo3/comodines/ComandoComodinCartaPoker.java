package edu.fiuba.algo3.comodines;


import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.Puntaje;

public class ComandoComodinCartaPoker implements ComandoComodin {
    private final CartaPoker carta;
    private final Puntaje puntaje;

    public ComandoComodinCartaPoker(CartaPoker carta, int valor, int multiplicador) {
        this.carta = carta;
        this.puntaje = new Puntaje(valor, multiplicador);
    }

    @Override
    public void ejecutar() {
        carta.aplicarComodin(puntaje);
    }
}

