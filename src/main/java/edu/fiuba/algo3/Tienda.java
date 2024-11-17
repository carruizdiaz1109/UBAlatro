package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;

public class Tienda {
    private final List<CartaPoker> cartasAComprar;
    private final List<Tarot> tarotsAComprar;
    private final List<Comodin> comodinesAComprar;

    public Tienda () {
        this.cartasAComprar = new ArrayList<CartaPoker>();
        this.tarotsAComprar = new ArrayList<Tarot>();
        this.comodinesAComprar = new ArrayList<Comodin>();
    }
}
