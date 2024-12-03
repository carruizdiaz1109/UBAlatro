package edu.fiuba.algo3.modelo.entidades.tarots;

import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;
import edu.fiuba.algo3.modelo.entidades.Puntaje;

public class TarotCarta extends Tarot {

    public TarotCarta(String nombre, String descripcion, Puntaje efecto) {
        super(nombre, descripcion, efecto, "carta", "cualquiera");
    }

    @Override
    public void aplicar(Object objeto) {
        System.out.println("Se llama el metodo aplicar exitosamente");
        if (objeto instanceof CartaPoker) {
            System.out.println("Se recibió corectamente la carta seleccionada");
            aplicar((CartaPoker) objeto);
        } else {
            throw new IllegalArgumentException("El objeto no es una Carta");
        }
    }

    public void aplicar(CartaPoker unaCarta) {
        System.out.println("Puntaje Tarot: " + this.puntaje.calcularPuntaje());
        System.out.println("Puntaje Carta antes del tarot: " + unaCarta.getPuntaje().calcularPuntaje());
        unaCarta.aplicarTarot(this.puntaje);
        System.out.println("Puntaje Carta después del tarot: " + unaCarta.getPuntaje().calcularPuntaje());
    }
}
