package edu.fiuba.algo3.modelo.entidades.jugadas;

import edu.fiuba.algo3.modelo.entidades.CartaPoker;
import edu.fiuba.algo3.modelo.entidades.Jugada;
import edu.fiuba.algo3.modelo.entidades.Puntaje;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Poker extends Jugada {

    public Poker(List<CartaPoker> cartas) {
        super(cartas, new Puntaje(60, 7));
    }

    @Override
    public boolean esJugada(List<CartaPoker> cartas) {
        // Crea una copia mutable de las cartas para poder ordenarlas
        List<CartaPoker> cartasOrdenadas = new ArrayList<>(cartas);
        cartasOrdenadas.sort(Collections.reverseOrder()); // Ordena las cartas de mayor a menor por valor

        // Contamos cuántas cartas tienen el mismo valor consecutivo
        int conteo = 1; // Inicia el conteo con 1 ya que ya estamos en la primera carta
        for (int i = 1; i < cartasOrdenadas.size(); i++) {
            if (cartasOrdenadas.get(i).esMismoValor(cartasOrdenadas.get(i - 1))) {
                conteo++;
            } else {
                conteo = 1; // Reinicia el conteo si el valor de la carta cambia
            }

            // Si encontramos 4 cartas del mismo valor, es un Poker
            if (conteo == 4) {
                cartasValidas = new ArrayList<>();
                // Recopilamos todas las cartas del mismo valor
                for (CartaPoker carta : cartasOrdenadas) {
                    if (carta.esMismoValor(cartasOrdenadas.get(i))) {
                        cartasValidas.add(carta);
                    }
                }
                return true;
            }
        }

        return false; // Si no se encuentra un Poker, retorna false
    }

    @Override
    protected List<CartaPoker> seleccionarCartasValidas(List<CartaPoker> cartas) {
        // En Poker, todas las cartas que forman la jugada son válidas
        return cartas;
    }
}
