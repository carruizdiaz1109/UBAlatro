package edu.fiuba.algo3.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MainController {
    @FXML
    private Label lblMano;

    @FXML
    void click(ActionEvent event) {
        lblMano.setText("Se repartio la mano");
    }
}
