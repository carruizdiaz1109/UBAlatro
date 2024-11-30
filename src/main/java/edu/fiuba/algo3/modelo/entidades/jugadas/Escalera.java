package edu.fiuba.algo3.modelo.entidades.jugadas;

import edu.fiuba.algo3.modelo.entidades.CartaPoker;
import edu.fiuba.algo3.modelo.entidades.Jugada;
import edu.fiuba.algo3.modelo.entidades.Puntaje;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class Escalera extends Jugada {

    public Escalera(List<CartaPoker> cartas) {
        super(cartas, new Puntaje(30, 4));
    }

    @Override
    public boolean esJugada(List<CartaPoker> cartas) {
        // Crea una copia mutable de la lista de cartas para poder ordenarla
        List<CartaPoker> cartasOrdenadas = new ArrayList<>(cartas);
        cartasOrdenadas.sort(Collections.reverseOrder());

        // Verifica que las cartas formen una secuencia consecutiva
        for (int i = 0; i < cartasOrdenadas.size() - 1; i++) {
            if (!cartasOrdenadas.get(i).esConsecutiva(cartasOrdenadas.get(i + 1))) {
                return false;
            }
        }

        // Si todas las cartas forman una secuencia, es una Escalera
        cartasValidas = new ArrayList<>(cartas); // Asigna todas las cartas
        return true;
    }

    @Override
    protected List<CartaPoker> seleccionarCartasValidas(List<CartaPoker> cartas) {
        // Si `esJugada` fue verdadero, `cartasValidas` ya debe contener todas las cartas de Escalera
        if (cartasValidas != null && cartasValidas.size() == 5) {
            return cartasValidas;
        }

        return List.of();  // Devuelve una lista vacía si no es Escalera
    }
}
