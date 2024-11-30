package edu.fiuba.algo3.modelo.excepciones;

public class ArchivoNoEncontradoError extends RuntimeException {
    public ArchivoNoEncontradoError() {
        super("El archivo no fue encontrado");
    }
}
