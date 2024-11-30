package edu.fiuba.algo3.modelo.entidades;

import edu.fiuba.algo3.modelo.entidades.jugadas.Descarte;
import edu.fiuba.algo3.modelo.excepciones.NoHayDescarteDisponiblesError;
import edu.fiuba.algo3.modelo.excepciones.NoHayJugadasDisponiblesError;

import java.util.ArrayList;
import java.util.List;

public class Ronda {

    private final int numero;
    private final int puntajeMinimo; //puntajeASuperar
    private int descartesDisponibles; //descartes
    private int jugadasDisponibles; //manos
    private final Tienda tienda;
    private final List<Jugada> jugadas;


    public Ronda(int numero, int puntajeMinimo, int descartesDisponibles, int jugadasDisponibles, Tienda tienda) {
        this.numero = numero;
        this.puntajeMinimo = puntajeMinimo;
        this.descartesDisponibles = descartesDisponibles;
        this.jugadasDisponibles = jugadasDisponibles;
        this.jugadas = new ArrayList<Jugada>();
        this.tienda = tienda;
    }

    public boolean rondaSuperada() {
        return (this.puntajeMinimo <= calcularTotalRonda());
    }

    public void agregarJugada(Jugada unaJugada) {
        if (sePuedeSeguirJugando()) {
            this.jugadas.add(unaJugada);
            this.jugadasDisponibles--;
        }else{
            throw new NoHayJugadasDisponiblesError();
        }
    }

    public boolean sePuedeSeguirJugando() {
        return (this.jugadasDisponibles > 0 && !rondaSuperada());
    }

    public void agregarDescarte(Descarte unDescarte) {
        if (sePuedeSeguirJugando() && this.descartesDisponibles > 0) {
            this.jugadas.add(unDescarte);
            this.descartesDisponibles--;
        }else {
            throw new NoHayDescarteDisponiblesError();
        }
    }

    public int calcularTotalRonda () {
        int acumulador = 0;
        for (Jugada jugada : this.jugadas) {
            acumulador += jugada.calcularPuntaje();
        }
        return acumulador;
    }

    public int getPuntajeAcumulado() {
        return this.calcularTotalRonda();
    }

    public int getManosDisponibles() {
        return this.jugadasDisponibles;
    }

    public int getPuntajeObjetivo() {
        return this.puntajeMinimo;
    }

    public int getCantidadDescartes() {
        return this.descartesDisponibles;
    }
}
