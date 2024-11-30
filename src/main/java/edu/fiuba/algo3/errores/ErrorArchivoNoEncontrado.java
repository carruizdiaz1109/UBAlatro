package edu.fiuba.algo3.errores;

public class ErrorArchivoNoEncontrado extends RuntimeException {
    public ErrorArchivoNoEncontrado() {
        super("El archivo no fue encontrado");
    }
}
