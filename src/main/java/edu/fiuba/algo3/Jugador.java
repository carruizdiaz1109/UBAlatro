package edu.fiuba.algo3;

import java.util.ArrayList;

public class Jugador {

    private final String nombre;
    private final Mano manoActual;
    private final Mazo mazo;
    private final ArrayList<Tarot> cartasTarot;
    private Ronda rondaActual;

    public Jugador(String nombre, Mazo mazo){
        this.nombre = nombre;
        this.mazo = mazo;
        this.manoActual = new Mano(this.mazo);
        this.cartasTarot = new ArrayList<Tarot>();
    }

    public boolean esPosibleIniciarRonda(){
        return this.mazo.tieneCartas();
    }

    public void iniciarRonda(Ronda rondaActual){
        if(!esPosibleIniciarRonda()){
            throw new ErrorMazoVacio();
        }
        this.rondaActual = rondaActual;
        this.manoActual.rellenarse();
    }

    public void jugar(){
        Jugada unaJugada = this.manoActual.jugar();
        this.rondaActual.agregarJugada(unaJugada);
    }

    public void aniadirTarots(Tarot cartaTarot) {
        if (this.cartasTarot.size() < 2) {
            this.cartasTarot.add(cartaTarot);
        }
    }

    public void utilizarTarot(Tarot tarotaAplicar, CartaPoker cartaPoker) {
        if (!this.cartasTarot.isEmpty() && this.cartasTarot.contains(tarotaAplicar)) {
            cartaPoker.activarTarot(tarotaAplicar);
        } else {
            throw new TarotsNoDisponiblesError("No hay tarots disponibles para jugar");
        }
    }

}
