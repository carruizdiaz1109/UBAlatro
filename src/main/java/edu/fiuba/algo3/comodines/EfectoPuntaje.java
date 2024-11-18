package edu.fiuba.algo3.comodines;

import edu.fiuba.algo3.Puntaje;

public class EfectoPuntaje {
    private int puntos;
    private int multiplicador;

    public EfectoPuntaje(int puntos, int multiplicador) {
        this.puntos = puntos;
        this.multiplicador = multiplicador;
    }

    public void aplicar(Puntaje puntaje) {
        puntaje.incrementarPuntos(this.puntos);
        puntaje.incrementarMultiplicador(this.multiplicador);
    }

}
