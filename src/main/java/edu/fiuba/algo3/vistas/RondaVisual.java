package edu.fiuba.algo3.vistas;
import edu.fiuba.algo3.modelo.entidades.Ronda;

import javafx.scene.control.Label;


public class RondaVisual {

    private final Label lblPuntajeAcumulado;
    private final Label lblManosDisponibles;
    private final Label lblPuntajeObjetivo;
    private final Label lblCantidadDescartes;

    private final Ronda ronda;

    public RondaVisual(Ronda ronda, Label lblPuntajeAcumulado, Label lblManosDisponibles,
                       Label lblPuntajeObjetivo, Label lblCantidadDescartes) {
        this.ronda = ronda;
        this.lblPuntajeAcumulado = lblPuntajeAcumulado;
        this.lblManosDisponibles = lblManosDisponibles;
        this.lblPuntajeObjetivo = lblPuntajeObjetivo;
        this.lblCantidadDescartes = lblCantidadDescartes;
        actualizarVista();
    }

    public void actualizarVista() {
        lblPuntajeAcumulado.setText(String.valueOf(ronda.getPuntajeAcumulado()));
        lblManosDisponibles.setText(String.valueOf(ronda.getManosDisponibles()));
        lblPuntajeObjetivo.setText(String.valueOf(ronda.getPuntajeObjetivo()));
        lblCantidadDescartes.setText(String.valueOf(ronda.getCantidadDescartes()));
    }
}
