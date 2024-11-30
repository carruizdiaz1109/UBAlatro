package edu.fiuba.algo3.errores;

public class ErrorPuntajeNulo extends RuntimeException {
    public ErrorPuntajeNulo() {
        super("El puntaje no puede ser nulo");
    }
}
