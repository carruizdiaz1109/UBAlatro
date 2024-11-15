package edu.fiuba.algo3;

import edu.fiuba.algo3.jugadas.*;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private Mano manoActual;
    private Mazo mazo;
    private ArrayList<Tarot> cartasTarot;

    public Jugador(String nombre){
        this.nombre = nombre;
        this.manoActual = new Mano(new ArrayList<>());
        this.mazo = new Mazo();
        this.cartasTarot = new ArrayList<>();
    }

    public boolean esMismoNombre(String unNombre){
        return this.nombre.equals(unNombre);
    }

    public boolean esPosibleIniciarRonda(){
        return this.mazo.tieneCartas();
    }

    /*
    public void iniciarRonda(){
        if(!esPosibleIniciarRonda()){
            throw new ErrorMazoVacio();
        }
        this.manoActual = mazo.repartir();
    }
    */

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
        if (!this.cartasTarot.isEmpty()) {
            this.cartasTarot.get(indiceTarot).modificarPuntaje(cartaPoker);
        } else {
            throw new TarotsNoDisponiblesError("No hay tarots disponibles para jugar");

        }
    }

    public boolean esManoLlena(){
        return manoActual.manoLlena();
    }

    public void setMano(Mano unaMano){
        this.manoActual = unaMano;
    }
}
