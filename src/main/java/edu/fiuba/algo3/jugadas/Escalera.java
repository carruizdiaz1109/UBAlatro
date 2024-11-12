package edu.fiuba.algo3.jugadas;

import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.Jugada;
import edu.fiuba.algo3.Puntaje;

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
        // Ordena las cartas de menor a mayor según su valor
        Collections.sort(cartasOrdenadas);

        // Verifica que las cartas formen una secuencia consecutiva
        for (int i = 0; i < cartasOrdenadas.size() - 1; i++) {
            // Si no son consecutivas, no es una escalera
            if (!cartasOrdenadas.get(i).esConsecutiva(cartasOrdenadas.get(i + 1))) {
                return false;
            }
        }

        // Si todas las cartas forman una secuencia, es una Escalera
        cartasValidas = new ArrayList<>(cartasOrdenadas); // Asigna las cartas válidas
        return true;
    }

    @Override
    protected List<CartaPoker> seleccionarCartasValidas(List<CartaPoker> cartas) {
        // Crea una copia mutable de la lista de cartas para poder ordenarla
        List<CartaPoker> cartasOrdenadas = new ArrayList<>(cartas);
        // Ordena las cartas de menor a mayor
        Collections.sort(cartasOrdenadas);

        // En una escalera, todas las cartas son válidas si forman una secuencia
        return new ArrayList<>(cartasOrdenadas);
    }
}
