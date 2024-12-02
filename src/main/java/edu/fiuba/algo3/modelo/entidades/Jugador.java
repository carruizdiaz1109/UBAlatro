package edu.fiuba.algo3.modelo.entidades;

import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;
import edu.fiuba.algo3.modelo.entidades.comodines.Comodin;
import edu.fiuba.algo3.modelo.entidades.jugadas.Descarte;
import edu.fiuba.algo3.modelo.entidades.tarots.Tarot;
import edu.fiuba.algo3.modelo.excepciones.MazoVacioError;
import edu.fiuba.algo3.modelo.excepciones.TarotsNoDisponiblesError;

import java.util.ArrayList;

public class Jugador {

    private final String nombre;
    protected Mano manoActual;
    protected Mazo mazo;
    private final ArrayList<Tarot> tarots;
    private final ArrayList<Comodin> comodines;
    protected Ronda rondaActual;

    public Jugador(String nombre, Mazo mazo){
        this.nombre = nombre;
        this.mazo = mazo;
        this.manoActual = new Mano(this.mazo);
        this.tarots = new ArrayList<Tarot>();
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
        System.out.println();
        System.out.println(unaJugada.getClass());
        this.rondaActual.agregarJugada(unaJugada);
        this.manoActual.rellenarse();
    }

    public void seleccionarCarta(ArrayList<CartaPoker> cartasASeleccionar) {
        this.manoActual.seleccionarCartas(cartasASeleccionar);
    }

    public void aniadirTarot(Tarot unTarot) {
        if (this.tarots.size() < 2) {
            this.tarots.add(unTarot);
        }
    }

    public void aniadirComodin(Comodin unComodin) {
        if (this.comodines.size() < 5) {
            this.comodines.add(unComodin);
        }
    }

    public void aniadirCartaPoker(CartaPoker cartaPoker) { this.mazo.agregarCartaPoker(cartaPoker); }

    public void eliminarTarot(Tarot unTarot) {
        if (tarots.contains(unTarot)) {
            tarots.remove(unTarot);
            System.out.println("Tarot eliminado del inventario: " + unTarot.getNombre());
        } else {
            System.out.println("Error: El tarot no está en el inventario.");
        }
    }

    public void eliminarComodin(Comodin unComodin) {
        if (comodines.contains(unComodin)) {
            comodines.remove(unComodin);
            System.out.println("Comodin eliminado del inventario: " + unComodin.getNombre());
        } else {
            System.out.println("Error: El comodin no está en el inventario.");
        }
    }

    public void utilizarTarot(Tarot tarotaAplicar, CartaPoker cartaPoker) {
        if (!this.tarots.isEmpty() && this.tarots.contains(tarotaAplicar)) {
            tarotaAplicar.aplicar(cartaPoker);
        } else {
            throw new TarotsNoDisponiblesError("No hay tarots disponibles para jugar");
        }
    }

    public void aplicarComodin(Jugada unaJugada) {
        for (Comodin comodin : this.comodines) {
           comodin.aplicar(unaJugada);
        }
    }

    public void descartar() {
        Descarte unDesarte = this.manoActual.descartar();
        if (unDesarte != null) {
        aplicarComodin(unDesarte);
        this.rondaActual.agregarDescarte(unDesarte);
        this.manoActual.rellenarse();
        }
    }

    public Mano getManoActual() {
        return this.manoActual;
    }

    public ArrayList<Comodin> obtenerComodines() {
        return this.comodines;
    }

    public ArrayList<Tarot> obtenerTarots() {
        return this.tarots;
    }
}
