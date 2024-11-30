package edu.fiuba.algo3.modelo.excepciones;

public class TarotDistintaJugadaError extends RuntimeException {
  public TarotDistintaJugadaError() {
    super("El tarot no se puede utilizar en esta jugada.");
  }
}
