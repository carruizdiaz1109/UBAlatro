package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;
import edu.fiuba.algo3.jugadas.*;

public abstract class Jugada {
    private int sumaValores;
    private Puntaje puntaje;
    protected List<CartaPoker> cartas;
    protected List<CartaPoker> cartasValidas;

    public Jugada(List<CartaPoker> cartas, Puntaje puntaje) {
        this.cartas = cartas;
        this.sumaValores = 0;
        this.puntaje = puntaje;
        this.cartasValidas = new ArrayList<>();
    }

    public abstract boolean esJugada(List<CartaPoker> cartas);

    protected abstract List<CartaPoker> seleccionarCartasValidas(List<CartaPoker> cartas);

    public static Jugada crearJugada(ArrayList<CartaPoker> cartas) {
        List<Jugada> posiblesJugadas = List.of(
            new EscaleraReal(cartas),
            new EscaleraColor(cartas),
            new Poker(cartas),
            new FullHouse(cartas),
            new Color(cartas),
            new Escalera(cartas),
            new Trio(cartas),
            new DoblePar(cartas),
            new Par(cartas)
        );

        for (Jugada jugada : posiblesJugadas) {
            if (jugada.esJugada(cartas)) {
                return jugada;
            }
        }
        // Por defecto se devuelve carta alta
        return new CartaAlta(cartas);
    }

    public void sumarValores() {
        this.sumaValores = 0;
        for (CartaPoker carta : cartasValidas) {
            System.out.println("Suma actual antes de agregar carta: " + sumaValores);
            System.out.println("Valor de la carta: " + carta.sumarValorCon(0));
            sumaValores += carta.sumarValorCon(sumaValores);
            System.out.println("Suma acumulada después de agregar carta: " + sumaValores);
        }

    }
    public int calcularPuntaje() {
        sumarValores();
        System.out.println("Puntaje antes de incrementar: " + puntaje.calcularPuntaje());
        puntaje.incrementarPuntos(sumaValores);
        System.out.println("Puntaje después de incrementar: " + puntaje.calcularPuntaje());
        return puntaje.calcularPuntaje();
    }
}