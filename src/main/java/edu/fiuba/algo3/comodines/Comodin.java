package edu.fiuba.algo3.comodines;

import edu.fiuba.algo3.Puntaje;

import java.util.Random;

public abstract class Comodin {
    private String nombre;
    private String descripcion;
    //private int probabilidadRuptura;

    public Comodin(String nombre, String descripcion, int probabilidadRuptura) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        //this.probabilidadRuptura = probabilidadRuptura;
    }

    public abstract void aplicar(Puntaje puntaje, Object contexto);

    /*public boolean romperse() {
        Random random = new Random();
        return random.nextInt(probabilidadRuptura) == 0;
    }*/
}
