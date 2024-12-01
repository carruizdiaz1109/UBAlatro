package edu.fiuba.algo3.vistas;
import edu.fiuba.algo3.modelo.entidades.Ronda;

import javafx.scene.control.Label;


public class RondaVisual {

       private final Ronda ronda;

    public RondaVisual(Ronda ronda, Label lblPuntajeAcumulado, Label lblManosDisponibles,
                       Label lblPuntajeObjetivo, Label lblCantidadDescartes) {
        lblPuntajeAcumulado.textProperty().bind(ronda.puntajeAcumuladoProperty().asString());
        lblManosDisponibles.textProperty().bind(ronda.manosDisponiblesProperty().asString());
        lblPuntajeObjetivo.textProperty().bind(ronda.puntajeObjetivoProperty().asString());
        lblCantidadDescartes.textProperty().bind(ronda.cantidadDescartesProperty().asString());
        this.ronda = ronda;
    }

}
