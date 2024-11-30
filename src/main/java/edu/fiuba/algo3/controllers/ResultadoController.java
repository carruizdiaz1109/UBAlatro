package edu.fiuba.algo3.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ResultadoController {

    @FXML
    private Label lblResultado;

    public void mostrarMensaje(String mensaje) {
        lblResultado.setText(mensaje);
    }
}