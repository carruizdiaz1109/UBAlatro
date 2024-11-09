package edu.fiuba.algo3;

import java.awt.*;

public class Balatro {
    private int rondas;
    private int puntajeActual;
    private int rondaActual;

    private Jugador jugador;
    private Ronda ronda;
    private Panel panel;
    private Tienda tienda;

    public Balatro()
    {
        this.jugador = new Jugador(String nombre);
        this.panel = new Panel();
        this.tienda = new Tienda();
    }

    public void iniciarJuego()
    {
        this.ronda = new Ronda();
        iniciarRonda();
    }
    public void verificarPuntaje() //Deberia mostrar el puntaje actual? o devolver el puntaje actual y compararlo con el puntaje meta
    {
        System.out.println("Puntaje actual del jugador: " + puntajeActual);
        //return this.puntajeActual;
    }
    public void mostrarTienda()
    {
        tienda.mostrarCartas();
    }
    private void iniciarRonda()
    {
        ronda.iniciarRonda(puntajeActual);
    }
}
