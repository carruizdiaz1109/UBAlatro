package edu.fiuba.algo3.modelo.entidades;

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
        this.multiplicador = this.multiplicador * incremento;
    }

    public int calcularPuntaje() { return this.multiplicador * this.puntos; }

    public Puntaje sumarPuntaje(Puntaje unPuntaje) {
        int nuevoPuntos = this.puntos + unPuntaje.puntos;
        int nuevoMultiplicador = this.multiplicador * unPuntaje.multiplicador;
        return new Puntaje(nuevoPuntos, nuevoMultiplicador);
    }

    public boolean compararPuntajecon(int otroPuntaje){
        return (calcularPuntaje() == otroPuntaje);
    }

    public boolean compararPuntajecon(Puntaje otroPuntaje){
        return (this.puntos == otroPuntaje.puntos && this.multiplicador == otroPuntaje.multiplicador);
    }

    public String mostrarPuntos() {
        return ("puntos: " + this.puntos);
    }

    public String mostrarMultiplicador() {
        return ("multiplicador: " + this.multiplicador);
    }

    public int obtenerPuntos() { return this.puntos; }

    public int obtenerMultiplicador() { return this.multiplicador; }
}
