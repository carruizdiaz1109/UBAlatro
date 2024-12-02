package edu.fiuba.algo3.vistas;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public abstract class ElementoVisual<T> extends ImageView {

    private final T referencia; // Referencia genérica al modelo (CartaPoker, Comodin, Tarot)
    private static double ancho = 130; // Dimensiones estáticas para todas las cartas
    private static double alto = 180;

    public ElementoVisual(T referencia, String imagePath) {
        super(new Image(Objects.requireNonNull(ElementoVisual.class.getResourceAsStream(imagePath))));
        this.referencia = referencia;
        this.setFitWidth(ancho);
        this.setFitHeight(alto);
        this.setPreserveRatio(true);
        this.hacerArrastrable(); // Comportamiento común
    }

    // Getter para la referencia del modelo
    public T getReferencia() {
        return referencia;
    }

    // Método común para hacer arrastrable
    private void hacerArrastrable() {
        final double[] offsetX = {0};
        final double[] offsetY = {0};

        this.setOnMousePressed(event -> {
            offsetX[0] = event.getSceneX() - this.getLayoutX();
            offsetY[0] = event.getSceneY() - this.getLayoutY();
        });

        this.setOnMouseDragged(event -> {
            this.setLayoutX(event.getSceneX() - offsetX[0]);
            this.setLayoutY(event.getSceneY() - offsetY[0]);
        });

        this.setOnMouseReleased(event -> {
            System.out.println("Elemento soltado en: " + this.getLayoutX() + ", " + this.getLayoutY());
        });
    }

    // Métodos para ajustar las dimensiones de forma centralizada
    public static void setDimensiones(double nuevoAncho, double nuevoAlto) {
        ancho = nuevoAncho;
        alto = nuevoAlto;
    }
}
