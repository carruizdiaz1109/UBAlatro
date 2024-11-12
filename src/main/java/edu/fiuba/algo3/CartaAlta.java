package edu.fiuba.algo3;

import java.util.List;

public class CartaAlta extends Jugada{
    private CartaPoker cartaAlta;

    public CartaAlta(List<CartaPoker> cartas) {
        super(cartas);
        this.cartaAlta = encontrarCartaAlta(cartas);
    }

    private CartaPoker encontrarCartaAlta(List<CartaPoker> cartas) {
        CartaPoker max = cartas.get(0);
        for (CartaPoker carta : cartas) {
            if (carta.esMayorA(max)) {
                max = carta;
            }
        }
        return max;
    }

    public CartaPoker getCartaAlta() {
        return cartaAlta;
    }

    @Override
    public boolean esJugada(List<CartaPoker> cartas) {
        return true;
    }

    public int calcularPuntaje() {
        return cartaAlta.calcularPuntaje();
    }
}
