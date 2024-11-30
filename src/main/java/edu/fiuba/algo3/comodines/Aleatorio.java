package edu.fiuba.algo3.comodines;

import java.util.Random;

public class Aleatorio implements Aleatoreidad {
    private final int chance; // Probabilidad como "1 en chance"

    public Aleatorio(int chance) {
        if (chance <= 0) {
            throw new IllegalArgumentException("La probabilidad debe ser mayor que 0.");
        }
        this.chance = chance;
    }

    @Override
    public boolean seAplica() {
        Random random = new Random();
        return random.nextInt(chance) == 0; // 1 en 'chance'
    }
}
