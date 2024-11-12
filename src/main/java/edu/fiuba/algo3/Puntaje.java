package edu.fiuba.algo3;

public class Puntaje {
    private int multiplicador;
    private int puntos;

    public Puntaje(int unosPuntos, int unMultiplicador) {
        this.multiplicador = unMultiplicador;
        this.puntos = unosPuntos;
    }

    public void incrementarPuntos(int incremento){
        this.puntos = this.puntos + incremento;
    }

    public void incrementarMultiplicador( int incremento) {
        this.multiplicador = this.multiplicador + incremento;
    }

   public int calcularPuntaje() {
        return this.multiplicador * this.puntos;
    }

    public Puntaje sumarPuntaje(Puntaje unPuntaje) {
        unPuntaje.incrementarPuntos(this.puntos);
        //unPuntaje.incrementarMultiplicador(this.multiplicador);
        return unPuntaje;
    }
}
