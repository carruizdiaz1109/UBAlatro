package edu.fiuba.algo3.comodines;

import edu.fiuba.algo3.Jugada;
import edu.fiuba.algo3.Comodin;

import java.util.Random;

public class EfectoAleatorio extends Comodin {
    private final int probabilidadActivacion;

    public EfectoAleatorio(int probabilidad, int valor, int multiplicador) {
        super(valor, multiplicador);
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
