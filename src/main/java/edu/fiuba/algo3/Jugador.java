package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private int puntaje;
    private Mano manoActual;
    private Mazo mazo;
    private ArrayList<Tarot> cartasTarot;

    public Jugador(String nombre){
        this.nombre = nombre;
        this.puntaje = 0;
        this.manoActual = new Mano(new ArrayList<>());
        this.mazo = new Mazo();
        this.cartasTarot = new ArrayList<>();
    }

    public boolean esMismoPuntaje(int unPuntaje){
        return this.puntaje == unPuntaje;
    }

    public boolean esMismoNombre(String unNombre){
        return nombre.equals(unNombre);
    }

    public void iniciarRonda(){
        manoActual = mazo.repartir();
    }

    public Jugada jugar(){
        List<CartaPoker> cartas = List.of(new CartaPoker(1, Palo.PICAS)); // esto está hardcodeado
        return (new CartaAlta(cartas));                                         // acá hay que refactorizar después
    };

    public void aniadirTarots(Tarot cartaTarot) {
        if (this.cartasTarot.size() < 2) {
            this.cartasTarot.add(cartaTarot);
        }
    }

    public void utilizarTarot(int indiceTarot, CartaPoker cartaPoker) {
        this.cartasTarot.get(indiceTarot).modificarPuntaje(cartaPoker);
    }
}



