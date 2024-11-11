package edu.fiuba.algo3;

import java.util.List;

public class Jugador {
    private String nombre;

    public Jugador(String unNombre){
        this.nombre = unNombre;
    }

    public void iniciarRonda(){}

    public Jugada jugar(){
        List<CartaPoker> cartas = List.of(new CartaPoker(1, Palo.PICAS)); // esto está hardcodeado
        return (new CartaAlta(cartas));                                         // acá hay que refactorizar después
    };

}
