package edu.fiuba.algo3;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Ronda {

    private final int numero;
    private final int puntajeMinimo;
    private final int descartesDisponibles;
    private  int jugadasDisponibles;
    private final List<Jugada> jugadas;

    public Ronda(int numero, int puntajeMinimo, int descartesDisponibles, int jugadasDisponibles) {
        this.numero = numero;
        this.puntajeMinimo = puntajeMinimo;
        this.descartesDisponibles = descartesDisponibles;
        this.jugadasDisponibles = jugadasDisponibles;
        this.jugadas = new ArrayList<Jugada>();

    }

    /*
    public void iniciarRonda(){
        this.jugador.iniciarRonda();
        Jugada unaJugada = this.jugador.jugar();
        this.puntajeAcumulado += unaJugada.calcularValor();
    }

     public boolean verificarPuntaje() {
        return (this.puntajeMinimo <= this.calcularValor);
    }*/

    public void agregarJugada(Jugada unaJugada) {
        if (jugadasDisponibles > 0) {
            this.jugadas.add(unaJugada);
            this.jugadasDisponibles--;
        }
    }

    public boolean estadoRonda() {
        return (this.jugadasDisponibles <=  0);
    }
}
