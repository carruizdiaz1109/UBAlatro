package edu.fiuba.algo3.modelo.entidades.jugadas;

import edu.fiuba.algo3.modelo.entidades.*;
import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;

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
        if (cartas.size() < 6) {
            // Crea una copia mutable de la lista de cartas para poder ordenarla
            List<CartaPoker> cartasOrdenadas = new ArrayList<>(cartas);
            cartasOrdenadas.sort(Collections.reverseOrder());

            // Verifica que las cartas sean del mismo palo y consecutivas
            for (int i = 0; i < cartasOrdenadas.size() - 1; i++) {
                if (!cartasOrdenadas.get(i).esMismoPalo(cartasOrdenadas.get(i + 1)) && (!cartasOrdenadas.get(0).esMismoValor(new CartaPoker(Valor.AS, Palo.PICAS)))) {
                    return false;
                } else {
                    if (cartasOrdenadas.get(i + 1).esMismoValor(new CartaPoker(Valor.DIEZ, Palo.PICAS))) {
                        cartasValidas = new ArrayList<>(cartas); // Asigna todas las cartas
                        return true;
                    }
                }
                return false;
            }

        }
        return  false;
    }

    @Override
    protected List<CartaPoker> seleccionarCartasValidas (List < CartaPoker > cartas) {
        // Si `esJugada` fue verdadero, `cartasValidas` ya debe contener todas las cartas de Escalera Real
        if (cartasValidas != null && cartasValidas.size() == 5) {
            return cartasValidas;
        }

        return List.of();  // Devuelve una lista vacía si no es Escalera Real
    }
}
