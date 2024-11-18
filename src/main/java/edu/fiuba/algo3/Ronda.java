package edu.fiuba.algo3;

import edu.fiuba.algo3.comodines.GestorComodines;

import java.util.ArrayList;
import java.util.List;

public class Ronda {

    private final int numero;
    private final int puntajeMinimo;
    private final int descartesDisponibles;
    private int jugadasDisponibles;
    private final List<Jugada> jugadas;


    public Ronda(int numero, int puntajeMinimo, int descartesDisponibles, int jugadasDisponibles) {
        this.numero = numero;
        this.puntajeMinimo = puntajeMinimo;
        this.descartesDisponibles = descartesDisponibles;
        this.jugadasDisponibles = jugadasDisponibles;
        this.jugadas = new ArrayList<Jugada>();

    }

     public boolean verificarPuntaje() {
        return (this.puntajeMinimo <= calcularTotalRonda());
    }

    public void agregarJugada(Jugada unaJugada) {
        if (estadoRonda()) {
            this.jugadas.add(unaJugada);
            this.jugadasDisponibles--;
        }
    }

    public boolean estadoRonda() {
        return (this.jugadasDisponibles <= 0);
    }

    public int calcularTotalRonda () {
        int acumulador = 0;
        for (Jugada jugada : this.jugadas) {
            acumulador += jugada.calcularValor();
        }
        return acumulador;
    }
}
