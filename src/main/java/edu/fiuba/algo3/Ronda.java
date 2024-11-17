package edu.fiuba.algo3;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ronda {

    private final int numero;
    private final int puntajeMinimo;
    private final int descartesDisponibles;
    private final int jugadasDisponibles;

    public Ronda(int numero, int puntajeMinimo, int descartesDisponibles, int jugadasDisponibles) {
        this.numero = numero;
        this.puntajeMinimo = puntajeMinimo;
        this.descartesDisponibles = descartesDisponibles;
        this.jugadasDisponibles = jugadasDisponibles;
    }

    /*
    public void iniciarRonda(){
        this.jugador.iniciarRonda();
        Jugada unaJugada = this.jugador.jugar();
        this.puntajeAcumulado += unaJugada.calcularValor();
    }

     public boolean verificarPuntaje() {
        return (this.puntajeMinimo <= this.puntajeAcumulado);
    }*/
}
