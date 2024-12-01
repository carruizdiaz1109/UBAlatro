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
    private RondaEstado estado;

    private final IntegerProperty puntajeAcumulado = new SimpleIntegerProperty();
    private final IntegerProperty manosDisponibles = new SimpleIntegerProperty();
    private final IntegerProperty puntajeObjetivo = new SimpleIntegerProperty();
    private final IntegerProperty cantidadDescartes = new SimpleIntegerProperty();

    public RondaEstado getEstado() { return estado; }

    public enum RondaEstado {
        GANADA,
        PERDIDA,
        EN_CURSO,
        //SIN_EMPEZAR se puede usar para algo o está de mas?
    }

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

    public boolean rondaSuperada() {
        return (this.puntajeMinimo <= calcularTotalRonda());
    }

    public RondaEstado agregarJugada(Jugada unaJugada) {
        if (this.jugadasDisponibles > 0) {
            this.jugadas.add(unaJugada);
            this.jugadasDisponibles--;
            this.manosDisponibles.set(this.jugadasDisponibles);
            this.puntajeAcumulado.set(calcularTotalRonda());
        } else {
            throw new NoHayJugadasDisponiblesError();
        }
        return estado;
    }

    public boolean sePuedeSeguirJugando() {
        return (this.jugadasDisponibles > 0);
    }

    public RondaEstado agregarDescarte(Descarte unDescarte) {
        if (this.descartesDisponibles > 0) {
            this.jugadas.add(unDescarte);
            this.descartesDisponibles--;
            this.cantidadDescartes.set(this.descartesDisponibles); // Actualizar el property
            this.puntajeAcumulado.set(calcularTotalRonda()); // Act
        }else {
            throw new NoHayDescarteDisponiblesError();
        }
        return estado;
    }

    public int calcularTotalRonda () {
        int acumulador = 0;
        for (Jugada jugada : this.jugadas) {
            int puntaje = jugada.calcularPuntaje();
            String tipoJugada = jugada.getClass().getSimpleName(); // Obtener el nombre de la clase
            System.out.println("Tipo de jugada: " + tipoJugada + ", Puntaje: " + puntaje); // Log para depurar
            acumulador += puntaje;
        }
        this.puntajeAcumulado.set(acumulador);
        verificarEstadoRonda(acumulador);
        return acumulador;
    }

    public RondaEstado verificarEstadoRonda(int puntajeRonda) {
        if (estado == RondaEstado.EN_CURSO) {
            if (puntajeRonda >= puntajeMinimo) {
                estado = RondaEstado.GANADA;
            } else if (jugadasDisponibles <= 0) {
                estado = RondaEstado.PERDIDA;
            }
        }
        return estado;
    }

    // Métodos para acceder a los properties
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
