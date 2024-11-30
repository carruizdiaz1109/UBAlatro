package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.comodines.Comodin;
import edu.fiuba.algo3.vistas.ComodinVisual;
import javafx.scene.layout.StackPane;

public class ComodinController {
    private ComodinVisual comodinVisual;

    public ComodinController(Comodin comodin, String imagePath, double width, double height) {
        this.comodinVisual = new ComodinVisual(comodin, imagePath, width, height);
    }

    // Metodo para inicializar la vista, por ejemplo, agregarla a un contenedor
    public void inicializarVista(StackPane contenedor) {
        contenedor.getChildren().add(comodinVisual);
    }

    // Metodo para agregar acción al comodín
    public void agregarAccionClick(Runnable accion) {
        comodinVisual.setOnMouseClicked(event -> accion.run());
    }
}
