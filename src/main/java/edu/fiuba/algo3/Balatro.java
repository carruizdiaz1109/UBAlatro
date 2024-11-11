package edu.fiuba.algo3;

public class Balatro {
    private int rondas;
    private Ronda rondaActual;
    private Jugador jugador;

    public Balatro(String nombreJugador) {
        this.rondas = 8;
        this.jugador = new Jugador(nombreJugador);
        this.rondaActual = null;
    }

    public void iniciarJuego() {
        int i = 0;
        this.rondaActual = this.crearRonda();
        while ( i < this.rondas && this.verificarResultado()){
            this.rondaActual.iniciarRonda();
            if (this.verificarResultado()) {
                this.rondaActual = this.crearRonda();
            }
            i++;
        }
    }

    protected Ronda crearRonda() {
        return (new Ronda(this.jugador));
    }

   public boolean verificarResultado(){
        return (this.rondaActual.verificarPuntaje());
    }

}
