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

    private final int numero;
    private final int puntajeMinimo; //puntajeASuperar
    private int descartesDisponibles; //descartes
    private int jugadasDisponibles; //manos
    private final Tienda tienda;
    private final List<Jugada> jugadas;

    private final StringProperty ultimaJugada = new SimpleStringProperty("");
    private final IntegerProperty puntajeAcumulado = new SimpleIntegerProperty();
    private final IntegerProperty manosDisponibles = new SimpleIntegerProperty();
    private final IntegerProperty puntajeObjetivo = new SimpleIntegerProperty();
    private final IntegerProperty cantidadDescartes = new SimpleIntegerProperty();
    private final IntegerProperty numeroRonda = new SimpleIntegerProperty();
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
        this.numeroRonda.set(numero);
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
        System.out.println("En ronda superada!!!! Total ronda: " + totalRonda);
        return (this.puntajeMinimo <= totalRonda);
    }

    public boolean sePuedeSeguirJugando() {
        return (this.jugadasDisponibles > 0 && this.estado == RondaEstado.EN_CURSO);
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
    }

    public void agregarDescarte(Descarte unDescarte) {
        if (sePuedeDescartar() && sePuedeSeguirJugando()) {
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
            int puntaje = jugada.getPuntaje().calcularPuntaje();
            String tipoJugada = jugada.getClass().getSimpleName();
            System.out.println("Calcular total ronda. Tipo de jugada: " + tipoJugada + ", Puntaje: " + puntaje);
            acumulador += puntaje;
        }

        System.out.println("acumulador: " + acumulador);
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
        int puntaje = calcularTotalRonda();;
        manosDisponibles.set(jugadasDisponibles);
        cantidadDescartes.set(descartesDisponibles);
        puntajeAcumulado.set(puntaje);

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

    public IntegerProperty numeroRondaProperty() {
        return numeroRonda;
    }

    public StringProperty ultimaJugadaProperty() { return ultimaJugada; }

    public String getUltimaJugada() { return ultimaJugada.get(); }

    public void setUltimaJugada(String ultimaJugada) { this.ultimaJugada.set(ultimaJugada); }

    public boolean sePuedeDescartar() {
        return (this.descartesDisponibles > 0);
    }

    public Tienda getTienda(){
        return  this.tienda;
    }
}
