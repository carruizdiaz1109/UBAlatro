package edu.fiuba.algo3.jugadas;

import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.ConjuntoCartas;
import edu.fiuba.algo3.Jugada;
import edu.fiuba.algo3.Puntaje;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EscaleraColor extends Jugada {

    public EscaleraColor(List<CartaPoker> cartas) {
        super(cartas, new Puntaje(100, 8));
    }

    @Override
    public boolean esJugada(List<CartaPoker> cartas) {

        // Crea una copia mutable de la lista de cartas para poder ordenarla
        List<CartaPoker> cartasOrdenadas = new ArrayList<>(cartas);
        cartasOrdenadas.sort(null);

        ConjuntoCartas conjuntoOrdenado = new ConjuntoCartas(cartasOrdenadas);
        CartaPoker carta = cartasOrdenadas.get(0);

        ConjuntoCartas escalera = carta.obtenerEscalera();

        cartasValidas = new ArrayList<>(cartas);
        return escalera.compararCon(conjuntoOrdenado);
    }

    @Override
    protected List<CartaPoker> seleccionarCartasValidas(List<CartaPoker> cartas) {
        // Si `esJugada` fue verdadero, `cartasValidas` ya debe contener todas las cartas de EscaleraColor
        if (cartasValidas != null && cartasValidas.size() == 5) {
            return cartasValidas;
        }

        return List.of();  // Devuelve una lista vacía si no es EscaleraColor
    }

}
