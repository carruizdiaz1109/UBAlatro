package edu.fiuba.algo3.errores;

public class ErrorCartaNula extends RuntimeException {
    public ErrorCartaNula() {
        super("No se puede comparar con carta nula");
    }
}
