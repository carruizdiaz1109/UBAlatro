package edu.fiuba.algo3.tarots;

import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.Puntaje;
import edu.fiuba.algo3.Tarot;

public class SobreCarta extends Tarot {

    public SobreCarta(String nombre, String descripcion, Puntaje efecto) {
        super(nombre, descripcion, efecto, "carta", "cualquiera");
    }

    public void aplicar(CartaPoker unaCarta) {
        unaCarta.aplicarTarot(this.puntaje);
    }

}
