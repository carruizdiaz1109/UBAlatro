package edu.fiuba.algo3;


import edu.fiuba.algo3.tarots.SobreCarta;
import edu.fiuba.algo3.tarots.SobreMano;

import java.util.List;

public abstract class Tarot {
    private String nombre;
    private String descripcion;
    protected Puntaje puntaje;
    private String sobre;
    protected String ejemplar;


    public Tarot(String nombre, String descripcion, Puntaje puntaje, String sobre, String ejemplar) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.puntaje = puntaje;
        this.sobre = sobre;
        this.ejemplar = ejemplar;
    }

    public void aplicar(Jugada unaJugada){
        throw new UnsupportedOperationException("Este método no se aplica a esta clase");
    }


    public void aplicar(CartaPoker unaCarta){
        throw new UnsupportedOperationException("Este método no se aplica a esta clase");
    }

}

