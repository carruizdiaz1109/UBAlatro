package edu.fiuba.algo3.modelo.entidades.tarots;

import edu.fiuba.algo3.modelo.excepciones.TarotDistintaJugadaError;
import edu.fiuba.algo3.modelo.entidades.Jugada;
import edu.fiuba.algo3.modelo.entidades.Puntaje;

import java.lang.reflect.Field;

public class TarotJugada extends Tarot {

    private final Class<? extends Jugada> claseJugada; // Class of the associated Jugada

    public TarotJugada(String nombre, String descripcion, Puntaje puntaje, String ejemplar,
                       Class<? extends Jugada> claseJugada) {
        super(nombre, descripcion, puntaje, "mano", ejemplar);
        this.claseJugada = claseJugada;
    }

    @Override
    public void aplicar(Object objeto) {
        if (objeto instanceof Jugada) {
            aplicar((Jugada) objeto);
        } else {
            throw new IllegalArgumentException("El objeto no es una Jugada");
        }
    }

    public void aplicar(Jugada unaJugada) {
        // Check if the class of the provided Jugada is compatible
        if (!claseJugada.isInstance(unaJugada)) {
            throw new TarotDistintaJugadaError();
        }

        try {
            Field puntajeField = claseJugada.getDeclaredField("puntaje");
            puntajeField.setAccessible(true);
            puntajeField.set(null, this.puntaje);

            System.out.println("Puntaje de clase actualizado para " + claseJugada.getSimpleName());

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error al modificar el puntaje de clase: " + e.getMessage(), e);
        }
    }
}


