package edu.fiuba.algo3;

public class Jugador {
    private String nombre;

    public Jugador(String unNombre){
        this.nombre = unNombre;
    }

    public void iniciarRonda(){}

    public Jugada jugar(){
        return (new Jugada());
    };

}
