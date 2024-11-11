package edu.fiuba.algo3;

import java.util.List;

public abstract class Jugada {
    private int sumaValores;
    private Puntaje puntaje;
    protected List<CartaPoker> cartas;

    public Jugada(List<CartaPoker> cartas) {
        this.cartas = cartas;
    }

    public abstract boolean esJugada(List<CartaPoker> cartas);

    public static Jugada crearJugada(List<CartaPoker> cartas) {
        List<Jugada> posiblesJugadas = List.of(
            // acá van todas las jugadas posibles sin incluir carta alta
        );

        for (Jugada jugada : posiblesJugadas) {
            if (jugada.esJugada(cartas)) {
                return jugada; // Devuelve la primer jugada que coincide
            }
        }
        // Por defecto se devuelve carta alta
        return new CartaAlta(cartas);
    }

    public void sumarValores() {
        for (CartaPoker carta : cartas) {
            sumaValores += carta.sumarValorCon(sumaValores);
        }

    }
    public int calcularPuntaje() {
        sumarValores();
        puntaje.incrementarPuntos(sumaValores);
        return puntaje.calcularPuntaje();
    }
}