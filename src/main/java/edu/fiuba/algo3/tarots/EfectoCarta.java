package edu.fiuba.algo3.tarots;

import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.Puntaje;
import edu.fiuba.algo3.Tarot;

public class EfectoCarta extends Tarot {

    public EfectoCarta(String nombre, String descripcion, Puntaje efecto) {
        super(nombre, descripcion, efecto, "carta", "cualquiera");
    }

    @Override
    public void aplicar(Object objeto) {
        if (objeto instanceof CartaPoker) {
            aplicar((CartaPoker) objeto);
        } else {
            throw new IllegalArgumentException("El objeto no es una Carta");
        }
    }

    public void aplicar(CartaPoker unaCarta) {
        unaCarta.aplicarTarot(this.puntaje);
    }

}
