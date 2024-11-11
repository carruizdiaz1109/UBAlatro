package edu.fiuba.algo3;

import edu.fiuba.algo3.CartaPoker;
import java.util.List;

public abstract class Jugada {
    private int sumaCartas;
    private Puntaje puntaje;
    protected List<CartaPoker> cartas;

    public Jugada(List<CartaPoker> cartas) {
        this.cartas = cartas;
    }

    public abstract boolean esJugada(List<CartaPoker> cartas);

    public static Jugada crearJugada(List<CartaPoker> cartas) {
        List<Jugada> posiblesJugadas = List.of(

        );

        for (Jugada jugada : posiblesJugadas) {
            if (jugada.esJugada(cartas)) {
                return jugada; // Devuelve la primer jugada que coincide
            }
        }
        // Por defecto se devuelve carta alta
        return new CartaAlta(cartas);
    }
}