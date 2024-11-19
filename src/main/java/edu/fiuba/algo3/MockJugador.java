package edu.fiuba.algo3;

//estoy probando cosas para que corran los test pero puede volar esto
public class MockJugador extends Jugador {
    private boolean rondaIniciada = false;

    public MockJugador(String nombre, Mazo mazo) {
        super(nombre, mazo);
    }

    @Override
    public void iniciarRonda(Ronda ronda) {
        this.rondaIniciada = true;
    }

    public boolean RondaIniciada() {
        return rondaIniciada;
    }
}
