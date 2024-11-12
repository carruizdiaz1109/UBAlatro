package edu.fiuba.algo3.jugadas;

import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.Jugada;
import edu.fiuba.algo3.Puntaje;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EscaleraReal extends Jugada {
    private CartaPoker cartaAlta;

    public EscaleraReal(List<CartaPoker> cartas) {
        super(cartas, new Puntaje(100, 8));
    }


    @Override
    public boolean esJugada(List<CartaPoker> cartas) {
        // Crea una copia mutable de la lista de cartas para poder ordenarla
        List<CartaPoker> cartasOrdenadas = new ArrayList<>(cartas);
        cartasOrdenadas.sort(Collections.reverseOrder());

        // Verifica que las cartas sean del mismo palo y consecutivas
        for (int i = 0; i < cartasOrdenadas.size() - 1; i++) {
            // Verifica si las cartas son consecutivas y del mismo palo
            if (!cartasOrdenadas.get(i).esConsecutiva(cartasOrdenadas.get(i + 1)) ||
                    !cartasOrdenadas.get(i).esMismoPalo(cartasOrdenadas.get(i + 1))) {
                return false;
            }
        }

        // Verifica que las cartas sean exactamente 10, J, Q, K, A (valores 10, 11, 12, 13, 14)
        List<Integer> valores = new ArrayList<>();
        for (CartaPoker carta : cartasOrdenadas) {
            valores.add(carta.sumarValorCon(0));  // Obtiene el valor de la carta
        }

        // Para la escalera real, los valores deben ser 10, 11, 12, 13, 14 (correspondientes a 10, J, Q, K, A)
        Collections.sort(valores);
        if (valores.equals(List.of(10, 11, 12, 13, 14))) {
            cartasValidas = new ArrayList<>(cartas); // Asigna todas las cartas
            return true;
        }

        return false; // Si no cumple la condición de Escalera Real
    }

    @Override
    protected List<CartaPoker> seleccionarCartasValidas(List<CartaPoker> cartas) {
        // Si `esJugada` fue verdadero, `cartasValidas` ya debe contener todas las cartas de Escalera Real
        if (cartasValidas != null && cartasValidas.size() == 5) {
            return cartasValidas;
        }

        return List.of();  // Devuelve una lista vacía si no es Escalera Real
    }
}
