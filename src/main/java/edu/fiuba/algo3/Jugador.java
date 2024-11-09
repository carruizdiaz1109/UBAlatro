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

    public int getPuntaje(){
        return puntaje;
    }

    public String getNombre(){
        return nombre;
    }

    public Mano getManoActual(){
        return manoActual;
    }

    public void iniciarRonda(){
        List<CartaPoker> cartas = mazo.rellenar(8);
        manoActual = new Mano(cartas);
    }

    public Jugada jugar(){
        List<CartaPoker> cartasSeleccionadas = new ArrayList<>();
        CartaPoker cartaSeleccionada = manoActual.seleccionarCarta();
        cartasSeleccionadas.add(cartaSeleccionada);
        return (new Jugada(cartasSeleccionadas));
    }

    public List<CartaPoker> descartarCartas(){
        List<CartaPoker> cartasDescartadas = manoActual.descartar();
        return cartasDescartadas;
    }

    public void comprarCartas(int cantidad){
        List<CartaPoker> cartasCompradas = mazo.rellenar(cantidad);
        for (CartaPoker carta : cartasCompradas){
            manoActual.recibirCarta(carta);
        }
    }

}