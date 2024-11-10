package edu.fiuba.algo3;

public class Balatro {
    private int rondas;
    private Ronda rondaActual;
    private Jugador jugador;

    public Balatro(String nombreJugador) {
        this.rondas = 8;
        this.jugador = new Jugador(nombreJugador);

    }

    public void iniciarJuego() {
        int i = 0;
        this.rondaActual = crearRonda();
        while ( i < this.rondas && verificarResultado()){
            this.rondaActual.iniciarRonda();
            this.rondaActual = crearRonda();
        }

    }

    protected Ronda crearRonda() {
        return new Ronda(this.jugador);
    }

   public boolean verificarResultado(){
        return (this.rondaActual.verificarResultado());
    }

}
