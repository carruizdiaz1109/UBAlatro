package edu.fiuba.algo3.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


public class ResultadoController {

    @FXML
    private Label lblResultado;

    @FXML
    private Button btnVolverAJugar;

    @FXML
    private Button btnSalir;

    @FXML
    private VBox root;
    private BalatroController balatroController;

    @FXML
    public void initialize() {
        btnVolverAJugar.setOnAction(event -> volverAJugar());
        btnSalir.setOnAction(event -> salir());
    }

    public void setBalatroController(BalatroController balatroController) {
        this.balatroController = balatroController;
    }

    public void mostrarMensaje(String mensaje, boolean gano) {
        lblResultado.setText(mensaje);
        if (gano) {
            root.setStyle("-fx-background-color: linear-gradient(to bottom, #4CAF50, #81C784);"); // Verde para ganar
        } else {
            root.setStyle("-fx-background-color: linear-gradient(to bottom, #E53935, #EF9A9A);"); // Rojo para perder
        }
    }

    private void volverAJugar() {
        this.balatroController.avanzarRonda();
    }

    private void salir() {
        System.exit(0);
    }
}