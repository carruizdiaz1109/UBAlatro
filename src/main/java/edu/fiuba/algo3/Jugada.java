package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;
import edu.fiuba.algo3.jugadas.*;

public abstract class Jugada {
    private final Puntaje sumaValores;
    private Puntaje puntaje;
    protected List<CartaPoker> cartas;
    protected List<CartaPoker> cartasValidas;
    private Puntaje puntajeComodin;
    protected Jugada siguiente;

    public Jugada(List<CartaPoker> cartas, Puntaje puntaje) {
        this.cartas = cartas;
        this.sumaValores = new Puntaje (0,1);
        this.puntaje = puntaje;
        this.cartasValidas = new ArrayList<>();
        this.puntajeComodin = new Puntaje(0,1);
    }

    public abstract boolean esJugada(List<CartaPoker> cartas);

    protected abstract List<CartaPoker> seleccionarCartasValidas(List<CartaPoker> cartas);

    public static Jugada crearJugada(List<CartaPoker> cartas) {
        Jugada escaleraReal = new EscaleraReal(cartas);
        Jugada escaleraColor = new EscaleraColor(cartas);
        Jugada poker = new Poker(cartas);
        Jugada fullHouse = new FullHouse(cartas);
        Jugada color = new Color(cartas);
        Jugada escalera = new Escalera(cartas);
        Jugada trio = new Trio(cartas);
        Jugada doblePar = new DoblePar(cartas);
        Jugada par = new Par(cartas);
        Jugada cartaAlta = new CartaAlta(cartas);

        // Configurar la cadena
        escaleraReal.setSiguiente(escaleraColor);
        escaleraColor.setSiguiente(poker);
        poker.setSiguiente(fullHouse);
        fullHouse.setSiguiente(color);
        color.setSiguiente(escalera);
        escalera.setSiguiente(trio);
        trio.setSiguiente(doblePar);
        doblePar.setSiguiente(par);
        par.setSiguiente(cartaAlta);

        // Evaluar cartas desde el primer eslabón de la cadena
        return escaleraReal.evaluar(cartas);
    }

    public void setSiguiente(Jugada siguiente) {
        this.siguiente = siguiente;
    }

    public Jugada evaluar(List<CartaPoker> cartas) {
        if (esJugada(cartas)) {
            return this;
        } else if (siguiente != null) {
            return siguiente.evaluar(cartas);
        }
        return null; // Por seguridad
    }


    public void sumarValores() {
        int sumaPuntajes = 0;
        for (CartaPoker carta : cartasValidas) {
            sumaPuntajes += carta.calcularPuntaje();
        }
        this.sumaValores.incrementarPuntos(sumaPuntajes);
    }


    public int calcularValor() {
        sumarValores();
        this.puntaje = this.puntaje.sumarPuntaje(this.sumaValores);
        return puntaje.calcularPuntaje();
    }

    public int calcularPuntaje() {
        sumarValores();
        this.puntaje = this.puntaje.sumarPuntaje(this.sumaValores);
        this.puntaje = this.puntaje.sumarPuntaje(this.puntajeComodin);
        return this.puntaje.calcularPuntaje();
    }

    public void aplicarComodin(Puntaje unPuntaje) {
        this.puntajeComodin = this.puntajeComodin.sumarPuntaje(unPuntaje);
    }
}