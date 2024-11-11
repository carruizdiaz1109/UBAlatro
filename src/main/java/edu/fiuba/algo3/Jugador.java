package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private int puntaje;
    private Mano manoActual;
    private Mazo mazo;

    public Jugador(String nombre){
        this.nombre = nombre;
        this.puntaje = 0;
        this.manoActual = new Mano(new ArrayList<>());
        this.mazo = new Mazo();
    }

    public boolean esMismoPuntaje(int unPuntaje){
        return this.puntaje == unPuntaje;
    }

    public boolean esMismoNombre(String unNombre){
        return nombre.equals(unNombre);
    }

    public void iniciarRonda(){
        manoActual = mazo.repartir();
    }

    public Jugada jugar(int indice){
        List<CartaPoker> cartasSeleccionadas = new ArrayList<>();
        CartaPoker cartaSeleccionada = manoActual.seleccionarCarta(indice);
        cartasSeleccionadas.add(cartaSeleccionada);
        return (new Jugada(cartasSeleccionadas));
    }
}
