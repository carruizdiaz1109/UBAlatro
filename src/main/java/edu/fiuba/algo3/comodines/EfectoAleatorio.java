package edu.fiuba.algo3.comodines;

import edu.fiuba.algo3.Jugada;
import edu.fiuba.algo3.Comodin;
import edu.fiuba.algo3.Puntaje;

import java.util.Random;

public class EfectoAleatorio extends Comodin {
    private final int probabilidadActivacion;

    public EfectoAleatorio(int probabilidad, Puntaje puntaje, String nombre, String descripcion) {
        super(puntaje, nombre, descripcion);
        this.probabilidadActivacion = probabilidad;
    }

    @Override
    public void aplicar(Jugada jugada) {
        if (seAplica()) {
            jugada.aplicarComodin(this.puntaje);
        }
    }

    public boolean seAplica() {
        Random random = new Random();
        int chance = random.nextInt(this.probabilidadActivacion)+1;
        return chance == 1;
    }
}
