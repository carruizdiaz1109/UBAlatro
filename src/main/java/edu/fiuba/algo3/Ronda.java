package edu.fiuba.algo3;

import edu.fiuba.algo3.jugadas.Descarte;

import java.util.ArrayList;
import java.util.List;

public class Ronda {

    private final int numero;
    private final int puntajeMinimo;
    private int descartesDisponibles;
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
        return (this.jugadasDisponibles > 0);
    }

    public void agregarDescarte(Descarte unDescarte) {
        if (estadoRonda() && this.descartesDisponibles > 0) {
            this.jugadas.add(unDescarte);
            this.descartesDisponibles--;
        }
    }

    public int calcularTotalRonda () {
        int acumulador = 0;
        for (Jugada jugada : this.jugadas) {
            acumulador += jugada.calcularPuntaje();
        }
        return acumulador;
    }
}
