package edu.fiuba.algo3;

public class Mazo {
    private int cantidadCartas;

    public Mano repartir() {
        // Lógica para repartir cartas y devolver una nueva instancia de Mano
        return new Mano();
    }

    public CartaPoker darCarta() {
        // Lógica para devolver una carta del mazo
        return new CartaPoker(1, "test");
    }
}
