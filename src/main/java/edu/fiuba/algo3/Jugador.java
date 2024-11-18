package edu.fiuba.algo3;

import edu.fiuba.algo3.comodines.EfectoJugada;

import javax.xml.catalog.CatalogResolver;
import java.util.ArrayList;

public class Jugador {

    private final String nombre;
    protected Mano manoActual;
    protected Mazo mazo;
    private final ArrayList<Tarot> cartasTarot;
    private final ArrayList<Comodin> comodines;
    protected Ronda rondaActual;

    public Jugador(String nombre, Mazo mazo){
        this.nombre = nombre;
        this.mazo = mazo;
        this.manoActual = new Mano(this.mazo);
        this.cartasTarot = new ArrayList<Tarot>();
        this.comodines = new ArrayList<Comodin>();
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
        aplicarComodin(unaJugada);
        this.rondaActual.agregarJugada(unaJugada);
    }

    public void seleccionarCarta(CartaPoker unaCarta) {
        this.manoActual.seleccionarCarta(unaCarta);
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

    public void aniadirComodin(Comodin unComodin) {
        this.comodines.add(unComodin);
    }

    public void aplicarComodin(Jugada unaJugada) {
        for (Comodin comodin : this.comodines) {
           comodin.aplicar(unaJugada);
        }
    }

}
