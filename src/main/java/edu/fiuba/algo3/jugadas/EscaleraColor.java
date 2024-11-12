package edu.fiuba.algo3.jugadas;

import edu.fiuba.algo3.CartaPoker;
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
        cartasOrdenadas.sort(Collections.reverseOrder());

        // Verifica que todas las cartas sean del mismo palo y formen una secuencia consecutiva
        for (int i = 0; i < cartasOrdenadas.size() - 1; i++) {
            CartaPoker cartaActual = cartasOrdenadas.get(i);
            CartaPoker cartaSiguiente = cartasOrdenadas.get(i + 1);

            // Comprueba que las cartas sean del mismo palo y consecutivas
            if (!cartaActual.esMismoPalo(cartaSiguiente) || !cartaActual.esConsecutiva(cartaSiguiente)) {
                return false;
            }
        }

        // Si todas las cartas son del mismo palo y forman una secuencia, es una EscaleraColor
        cartasValidas = new ArrayList<>(cartas); // Asigna todas las cartas
        return true;
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
