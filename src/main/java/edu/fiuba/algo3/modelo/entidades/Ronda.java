package edu.fiuba.algo3.modelo.entidades;

import edu.fiuba.algo3.modelo.entidades.jugadas.Descarte;
import edu.fiuba.algo3.modelo.excepciones.NoHayDescarteDisponiblesError;
import edu.fiuba.algo3.modelo.excepciones.NoHayJugadasDisponiblesError;
import edu.fiuba.algo3.modelo.entidades.Tienda;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Ronda {

    private final int numero;
    private final int puntajeMinimo; //puntajeASuperar
    private int descartesDisponibles; //descartes
    private int jugadasDisponibles; //manos
    private final Tienda tienda;
    private final List<Jugada> jugadas;

    private final IntegerProperty puntajeAcumulado = new SimpleIntegerProperty();
    private final IntegerProperty manosDisponibles = new SimpleIntegerProperty();
    private final IntegerProperty puntajeObjetivo = new SimpleIntegerProperty();
    private final IntegerProperty cantidadDescartes = new SimpleIntegerProperty();
    private RondaEstado estado;

    public Ronda(int numero, int puntajeMinimo, int descartesDisponibles, int jugadasDisponibles, Tienda tienda) {
        this.numero = numero;
        this.puntajeMinimo = puntajeMinimo;
        this.descartesDisponibles = descartesDisponibles;
        this.jugadasDisponibles = jugadasDisponibles;
        this.jugadas = new ArrayList<Jugada>();
        this.tienda = tienda;

        this.puntajeAcumulado.set(0); // Puntaje inicial es 0
        this.manosDisponibles.set(jugadasDisponibles);
        this.puntajeObjetivo.set(puntajeMinimo);
        this.cantidadDescartes.set(descartesDisponibles);
        this.estado = RondaEstado.EN_CURSO;
    }

    public RondaEstado getEstado() { return estado; }

    public enum RondaEstado {
        GANADA,
        PERDIDA,
        EN_CURSO
    }

    public boolean rondaSuperada() {
        int totalRonda = calcularTotalRonda();
        System.out.println("Total ronda: " + totalRonda);
        return (this.puntajeMinimo <= totalRonda);
    }

    public boolean sePuedeSeguirJugando() {
        return (this.jugadasDisponibles > 1 && this.estado == RondaEstado.EN_CURSO);
    }

    public void agregarJugada(Jugada unaJugada) {
        if (sePuedeSeguirJugando()) {
            this.jugadas.add(unaJugada);
            this.jugadasDisponibles --;
            actualizarPropiedades();
        } else {
            this.estado = RondaEstado.PERDIDA;
            throw new NoHayJugadasDisponiblesError();
        }
        verificarEstadoRonda();
    }

    public void agregarDescarte(Descarte unDescarte) {
        if (this.descartesDisponibles > 0 && sePuedeSeguirJugando()) {
            this.jugadas.add(unDescarte);
            this.descartesDisponibles--;
            actualizarPropiedades();
        }else {
            throw new NoHayDescarteDisponiblesError();
        }
        verificarEstadoRonda();
    }

    public int calcularTotalRonda () {
        int acumulador = 0;
        for (Jugada jugada : this.jugadas) {
            int puntaje = jugada.calcularPuntaje();
            String tipoJugada = jugada.getClass().getSimpleName();
            System.out.println("Tipo de jugada: " + tipoJugada + ", Puntaje: " + puntaje);
            acumulador += puntaje;
        }
        return acumulador;
    }

    public void verificarEstadoRonda() {
        if (rondaSuperada()) {
            this.estado = RondaEstado.GANADA;
        } else if(!sePuedeSeguirJugando()) {
            this.estado = RondaEstado.PERDIDA;
        }
    }

    private void actualizarPropiedades() {
        manosDisponibles.set(jugadasDisponibles);
        cantidadDescartes.set(descartesDisponibles);
        puntajeAcumulado.set(calcularTotalRonda());
    }

    public IntegerProperty puntajeAcumuladoProperty() {
        return puntajeAcumulado;
    }

    public IntegerProperty manosDisponiblesProperty() {
        return manosDisponibles;
    }

    public IntegerProperty puntajeObjetivoProperty() {
        return puntajeObjetivo;
    }

    public IntegerProperty cantidadDescartesProperty() {
        return cantidadDescartes;
    }

    public Tienda getTienda(){
        return  this.tienda;
    }
}
