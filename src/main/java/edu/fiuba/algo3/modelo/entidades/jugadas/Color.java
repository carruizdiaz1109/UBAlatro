package edu.fiuba.algo3.modelo.entidades.jugadas;

import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;
import edu.fiuba.algo3.modelo.entidades.Jugada;
import edu.fiuba.algo3.modelo.entidades.Puntaje;

import java.util.List;
import java.util.ArrayList;

public class Color extends Jugada {

    public Color(List<CartaPoker> cartas) {
        super(cartas, new Puntaje(35, 4));
    }

    @Override
    public boolean esJugada(List<CartaPoker> cartas) {
        // Crea una copia mutable de la lista de cartas para evitar modificar la lista original
        List<CartaPoker> cartasOrdenadas = new ArrayList<>(cartas);

        // Verifica que todas las cartas sean del mismo palo
        for (int i = 0; i < cartasOrdenadas.size() - 1; i++) {
            if (!cartasOrdenadas.get(i).esMismoPalo(cartasOrdenadas.get(i + 1))) {
                return false;
            }
        }

        // Si todas las cartas son del mismo palo, es un Color
        cartasValidas = seleccionarCartasValidas(cartas); // Asigna todas las cartas

        return true;
    }

    @Override
    protected List<CartaPoker> seleccionarCartasValidas(List<CartaPoker> cartas) {
        // Si `esJugada` fue verdadero, `cartasValidas` ya debe contener todas las cartas de Color
        if (cartas != null && cartas.size() == 5) {
            return cartas;
        }

        return List.of();  // Devuelve una lista vacía si no es Color
    }
}
