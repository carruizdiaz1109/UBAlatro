package edu.fiuba.algo3.modelo.excepciones;

public class CartaNulaError extends RuntimeException {
    public CartaNulaError() {
        super("No se puede comparar con carta nula");
    }
}
