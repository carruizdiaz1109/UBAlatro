package edu.fiuba.algo3.modelo.entidades.tarots;

import edu.fiuba.algo3.modelo.excepciones.TarotDistintaJugadaError;
import edu.fiuba.algo3.modelo.entidades.Jugada;
import edu.fiuba.algo3.modelo.entidades.Puntaje;
import edu.fiuba.algo3.modelo.entidades.ConfiguracionCadena;

import java.lang.module.Configuration;

public class TarotJugada extends Tarot {

    private final String ejemplar;

    public TarotJugada(String nombre, String descripcion, Puntaje efecto, String ejemplar) {
        super(nombre, descripcion, efecto, "mano", ejemplar);
        this.ejemplar = ejemplar;
    }

    @Override
    public void aplicar(Object objeto) {
        if (objeto instanceof Jugada) {
            aplicar();
        } else {
            throw new IllegalArgumentException("El objeto no es una Jugada");
        }
    }

    public void aplicar() {
        ConfiguracionCadena.setPuntaje(ejemplar, efecto);
    }

}


