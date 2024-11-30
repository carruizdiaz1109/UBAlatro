package edu.fiuba.algo3.modelo.excepciones;

public class CargarArchivoError extends RuntimeException {
    public CargarArchivoError(Throwable causa) {
        super("Error al cargar las rondas desde el archivo JSONError al cargar las rondas desde el archivo JSON", causa);
    }
}
