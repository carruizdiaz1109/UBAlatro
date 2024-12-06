package edu.fiuba.algo3.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;
import edu.fiuba.algo3.modelo.entidades.jugadas.*;
import edu.fiuba.algo3.modelo.interfaces.Evaluable;

public abstract class Jugada implements Evaluable {
    protected Puntaje puntaje;
    protected List<CartaPoker> cartas;
    protected List<CartaPoker> cartasValidas;
    private Puntaje puntajeComodin;
    protected Jugada siguiente;

    public Jugada(List<CartaPoker> cartas, String nombreJugada) {
        this.cartas = cartas;
        ConfiguracionJugadas instancia = ConfiguracionJugadas.getInstancia();
        this.puntaje = instancia.getPuntuacion(nombreJugada);
        this.cartasValidas = new ArrayList<>();
        this.puntajeComodin = new Puntaje(0,1);
    }

    protected abstract List<CartaPoker> seleccionarCartasValidas(List<CartaPoker> cartas);

    private static Jugada configurarCadena(List<CartaPoker> cartas) {
        Jugada escaleraReal = new EscaleraReal(cartas, "escalera real");
        Jugada escaleraColor = new EscaleraColor(cartas, "escalera color");
        Jugada poker = new Poker(cartas);
        Jugada fullHouse = new FullHouse(cartas);
        Jugada color = new Color(cartas);
        Jugada escalera = new Escalera(cartas, "escalera");
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

        return escaleraReal;
    }

    public static Jugada crearJugada(List<CartaPoker> cartas) {
        // Configurar la cadena y evaluar
        Jugada inicioCadena = configurarCadena(cartas);
        return inicioCadena.evaluar(cartas);
    }

    private void setSiguiente(Jugada siguiente) {
        this.siguiente = siguiente;
    }

    public Jugada evaluar(List<CartaPoker> cartas) {
        if (esJugada(cartas)) {
            return this;
        } else if (this.siguiente != null) {
            return this.siguiente.evaluar(cartas);
        }
        return null; // Por seguridad
    }

    public void sumarValores() {
        for (CartaPoker carta : cartasValidas) {
            this.puntaje.incrementarPuntos(carta.calcularPuntaje());
        }
    }

    public int calcularPuntaje() {
        sumarValores();
        this.puntaje = this.puntaje.sumarPuntaje(this.puntajeComodin);
        return this.puntaje.calcularPuntaje();
    }

    public void aplicarComodin(Puntaje unPuntaje) {
        this.puntajeComodin = this.puntajeComodin.sumarPuntaje(unPuntaje);
    }

    public Puntaje obtenerPuntaje() { return this.puntaje; }
}