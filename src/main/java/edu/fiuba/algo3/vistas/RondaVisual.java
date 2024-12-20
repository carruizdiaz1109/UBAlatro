package edu.fiuba.algo3.vistas;
import edu.fiuba.algo3.modelo.entidades.Ronda;

import javafx.scene.control.Label;


public class RondaVisual {

    private final Ronda ronda;

    public RondaVisual(Ronda ronda, Label lblPuntajeAcumulado, Label lblJugada, Label lblPuntajeJugada, Label lblPts, Label lblMult,
                       Label lblManosDisponibles, Label lblPuntajeObjetivo, Label lblCantidadDescartes, Label lblRonda) {
        lblPuntajeAcumulado.textProperty().bind(ronda.puntajeAcumuladoProperty().asString());
        lblJugada.textProperty().bind(ronda.ultimaJugadaProperty());
        lblPuntajeJugada.textProperty().bind(ronda.puntajeUltimaJugadaProperty().asString());
        lblPts.textProperty().bind(ronda.ptsUltimaJugadaProperty().asString());
        lblMult.textProperty().bind(ronda.multUltimaJugadaProperty().asString());
        lblManosDisponibles.textProperty().bind(ronda.manosDisponiblesProperty().asString());
        lblPuntajeObjetivo.textProperty().bind(ronda.puntajeObjetivoProperty().asString());
        lblCantidadDescartes.textProperty().bind(ronda.cantidadDescartesProperty().asString());
        lblRonda.textProperty().bind(ronda.numeroRondaProperty().asString());
        this.ronda = ronda;
    }

}
