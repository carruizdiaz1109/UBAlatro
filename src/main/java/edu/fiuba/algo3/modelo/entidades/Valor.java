package edu.fiuba.algo3.modelo.entidades;

import java.util.Arrays;

public enum Valor {
    AS(1),
    DOS(2),
    TRES(3),
    CUATRO(4),
    CINCO(5),
    SEIS(6),
    SIETE(7),
    OCHO(8),
    NUEVE(9),
    DIEZ(10),
    JOTA(11),
    REINA(12),
    REY(13);

    private final int valor;

    Valor(int valor) {
        this.valor = valor;
    }

    public int valor(){
        return valor;
    }

    public static Valor obtenerValorDesdeString(String numeroStr) {
        try {
            int numero = Integer.parseInt(numeroStr);
            // Si el número es válido, devolver el valor correspondiente
            for (Valor v : Valor.values()) {
                if (v.valor == numero) {
                    return v;
                }
            }
        } catch (NumberFormatException e) {
            // Si no es un número, intentar obtener el valor por nombre (como AS, REY, etc.)
            switch (numeroStr.toUpperCase()) {
                case "AS":
                    return AS;
                case "JOTA":
                    return JOTA;
                case "REINA":
                    return REINA;
                case "REY":
                    return REY;
                default:
                    throw new IllegalArgumentException("Valor no reconocido: " + numeroStr);
            }
        } throw new IllegalArgumentException(
            "Valor no reconocido: " + numeroStr + ". Valores posibles: " +
                Arrays.toString(Valor.values())
        );
    }
}



