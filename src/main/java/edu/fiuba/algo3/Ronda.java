package edu.fiuba.algo3;

public class Ronda {
    private static int puntajeRonda = 300;

    private int puntajeMinimo;
    private int descartesDisponibles;
    private int jugadasDisponibles;
    private int puntajeAcumulado;
    private Jugador jugador;

    public Ronda(Jugador unJugador) {
        this.jugador = unJugador;
        this.puntajeMinimo = (puntajeRonda + 100);
        this.descartesDisponibles = 3;
        this.jugadasDisponibles = 5;
        this.puntajeAcumulado = 0;
    }

    public void iniciarRonda(){
        //this.jugador.iniciarRonda();
        Jugada unaJugada = this.jugador.jugar();
        this.puntajeAcumulado += unaJugada.calcularValor();
    }

     public boolean verificarPuntaje() {
        return (this.puntajeMinimo <= this.puntajeAcumulado);
    }


}
