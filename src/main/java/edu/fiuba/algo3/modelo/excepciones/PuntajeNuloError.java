package edu.fiuba.algo3.modelo.excepciones;

public class PuntajeNuloError extends RuntimeException {
    public PuntajeNuloError() {
        super("El puntaje no puede ser nulo");
    }
}
