package edu.fiuba.algo3;

public enum Valor {
    AS(11),
    DOS(2),
    TRES(3),
    CUATRO(4),
    CINCO(5),
    SEIS(6),
    SIETE(7),
    OCHO(8),
    NUEVE(9),
    DIEZ(10),
    JOTA(10),
    REINA(10),
    REY(10);

    private int valor;

    Valor(int valor) {
        this.valor = valor;
    }

    public int valor(){
        return valor;
    }

}



