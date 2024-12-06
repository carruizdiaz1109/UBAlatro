package edu.fiuba.algo3.modelo.entidades;

import edu.fiuba.algo3.modelo.entidades.jugadas.Descarte;
import edu.fiuba.algo3.modelo.excepciones.NoHayDescarteDisponiblesError;
import edu.fiuba.algo3.modelo.excepciones.NoHayJugadasDisponiblesError;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ronda {

    private final int puntajeMinimo; //puntajeASuperar
    private int descartesDisponibles; //Descartes
    private int jugadasDisponibles; //manos
    private final Tienda tienda;
    private final List<Jugada> jugadas;

    private final IntegerProperty puntajeAcumulado = new SimpleIntegerProperty();
    private final StringProperty ultimaJugada = new SimpleStringProperty("");
    private final IntegerProperty puntajeUltimaJugada = new SimpleIntegerProperty();
    private final IntegerProperty ptsUltimaJugada = new SimpleIntegerProperty();
    private final IntegerProperty multUltimaJugada = new SimpleIntegerProperty();
    private final IntegerProperty manosDisponibles = new SimpleIntegerProperty();
    private final IntegerProperty puntajeObjetivo = new SimpleIntegerProperty();
    private final IntegerProperty cantidadDescartes = new SimpleIntegerProperty();
    private final IntegerProperty numeroRonda = new SimpleIntegerProperty();
    private RondaEstado estado;

    public Ronda(int numero, int puntajeMinimo, int descartesDisponibles, int jugadasDisponibles, Tienda tienda) {
        this.puntajeMinimo = puntajeMinimo;
        this.descartesDisponibles = descartesDisponibles;
        this.jugadasDisponibles = jugadasDisponibles;
        this.jugadas = new ArrayList<>();
        this.tienda = tienda;

        this.puntajeAcumulado.set(0); // Puntaje inicial es 0
        this.manosDisponibles.set(jugadasDisponibles);
        this.puntajeObjetivo.set(puntajeMinimo);
        this.cantidadDescartes.set(descartesDisponibles);
        this.numeroRonda.set(numero);
        this.estado = RondaEstado.EN_CURSO;
    }

    public enum RondaEstado {
        GANADA,
        PERDIDA,
        EN_CURSO
    }

    public boolean rondaSuperada() {
        int totalRonda = calcularTotalRonda();
        return (this.puntajeMinimo <= totalRonda);
    }

    public boolean sePuedeSeguirJugando() {
        return (this.jugadasDisponibles > 0 && this.estado == RondaEstado.EN_CURSO);
    }

    public void agregarJugada(Jugada unaJugada) {
        if (sePuedeSeguirJugando()) {
            this.puntajeUltimaJugada.set(unaJugada.calcularPuntaje());
            this.ptsUltimaJugada.set(unaJugada.obtenerPuntaje().obtenerPuntos());
            this.multUltimaJugada.set(unaJugada.obtenerPuntaje().obtenerMultiplicador());
            this.jugadas.add(unaJugada);
            this.jugadasDisponibles--;
            actualizarPropiedades();
        } else {
            this.estado = RondaEstado.PERDIDA;
            throw new NoHayJugadasDisponiblesError();
        }
    }

    public void agregarDescarte(Descarte unDescarte) {
        if (sePuedeDescartar() && sePuedeSeguirJugando()) {
            this.puntajeUltimaJugada.set(unDescarte.calcularPuntaje());
            this.ptsUltimaJugada.set(unDescarte.obtenerPuntaje().obtenerPuntos());
            this.multUltimaJugada.set(unDescarte.obtenerPuntaje().obtenerMultiplicador());
            this.jugadas.add(unDescarte);
            this.descartesDisponibles--;
            actualizarPropiedades();
        } else {
            throw new NoHayDescarteDisponiblesError();
        }
    }

    public int calcularTotalRonda () {
        int acumulador = 0;
        for (Jugada jugada : this.jugadas) {
            int puntaje = jugada.obtenerPuntaje().calcularPuntaje();
            String tipoJugada = jugada.getClass().getSimpleName();
            System.out.println("Calcular total ronda. Tipo de jugada: " + tipoJugada + ", Puntaje: " + puntaje);
            acumulador += puntaje;
        }

        System.out.println("acumulador: " + acumulador);
        return acumulador;
    }

    private void actualizarPropiedades() {
        int puntaje = calcularTotalRonda();
        manosDisponibles.set(jugadasDisponibles);
        cantidadDescartes.set(descartesDisponibles);
        puntajeAcumulado.set(puntaje);
        puntajeUltimaJugada.set(puntajeUltimaJugada.get());
    }

    public IntegerProperty puntajeAcumuladoProperty() {
        return puntajeAcumulado;
    }

    public IntegerProperty puntajeUltimaJugadaProperty() {
        return puntajeUltimaJugada;
    }

    public IntegerProperty ptsUltimaJugadaProperty() {
        return ptsUltimaJugada;
    }

    public IntegerProperty multUltimaJugadaProperty() {
        return multUltimaJugada;
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

    public IntegerProperty numeroRondaProperty() {
        return numeroRonda;
    }

    public StringProperty ultimaJugadaProperty() { return ultimaJugada; }

    public void setUltimaJugada(String ultimaJugada) { this.ultimaJugada.set(ultimaJugada); }

    public boolean sePuedeDescartar() {
        return (this.descartesDisponibles > 0);
    }

    public Tienda getTienda(){
        return  this.tienda;
    }
}
