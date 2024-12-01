package edu.fiuba.algo3.modelo.entidades;

import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;
import edu.fiuba.algo3.modelo.entidades.comodines.Comodin;
import edu.fiuba.algo3.modelo.entidades.jugadas.Descarte;
import edu.fiuba.algo3.modelo.excepciones.MazoVacioError;
import edu.fiuba.algo3.modelo.excepciones.TarotsNoDisponiblesError;

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
            throw new MazoVacioError();
        }
        this.rondaActual = rondaActual;
        this.manoActual.rellenarse();
    }

    public void jugar(){
        Jugada unaJugada = this.manoActual.jugar();
        if (this.comodines.size()>0) {
            aplicarComodin(unaJugada);
        }
        System.out.println(unaJugada.getClass());
        this.rondaActual.agregarJugada(unaJugada);
        this.manoActual.rellenarse();
    }

    public void seleccionarCarta(ArrayList<CartaPoker> cartasASeleccionar) {
        this.manoActual.seleccionarCartas(cartasASeleccionar);
    }

    public void aniadirTarots(Tarot cartaTarot) {
        if (this.cartasTarot.size() < 2) {
            this.cartasTarot.add(cartaTarot);
        }
    }

    public void utilizarTarot(Tarot tarotaAplicar, CartaPoker cartaPoker) {
        if (!this.cartasTarot.isEmpty() && this.cartasTarot.contains(tarotaAplicar)) {
            tarotaAplicar.aplicar(cartaPoker);
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

    public void descartar() {
        Descarte unDesarte = this.manoActual.descartar();
        aplicarComodin(unDesarte);
        this.rondaActual.agregarDescarte(unDesarte);
        this.manoActual.rellenarse();
    }

    public Mano getManoActual() {
        return this.manoActual;
    }

    public ArrayList<Comodin> obtenerComodines() {
        return this.comodines;
    }

    public ArrayList<Tarot> obtenerTarots() {
        return this.cartasTarot;
    }
}
