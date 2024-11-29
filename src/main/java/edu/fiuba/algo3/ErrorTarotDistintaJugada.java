package edu.fiuba.algo3;

public class ErrorTarotDistintaJugada extends RuntimeException {
  public ErrorTarotDistintaJugada() {
    super("El tarot no se puede utilizar en esta jugada.");
  }
}
