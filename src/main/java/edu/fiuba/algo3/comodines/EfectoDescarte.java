package edu.fiuba.algo3.comodines;

import edu.fiuba.algo3.Puntaje;

public class EfectoDescarte {
    private int puntos;
    private int multiplicador;

    public EfectoDescarte(int puntos, int multiplicador) {
        this.puntos = puntos;
        this.multiplicador = multiplicador;
    }

    public void aplicar(Puntaje puntaje, int cantidadDescartes) {
        int incrementoPuntos = puntos * cantidadDescartes;
        int incrementoMultiplicador = multiplicador * cantidadDescartes;

        puntaje.incrementarPuntos(incrementoPuntos);
        puntaje.incrementarMultiplicador(incrementoMultiplicador); //
    }
}


