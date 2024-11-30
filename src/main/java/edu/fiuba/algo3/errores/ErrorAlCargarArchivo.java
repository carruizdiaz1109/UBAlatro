package edu.fiuba.algo3.errores;

public class ErrorAlCargarArchivo extends RuntimeException {
    public ErrorAlCargarArchivo(Throwable causa) {
        super("Error al cargar las rondas desde el archivo JSONError al cargar las rondas desde el archivo JSON", causa);
    }
}
