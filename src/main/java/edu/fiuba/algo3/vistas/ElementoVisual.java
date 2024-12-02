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
    }

    public T getReferencia() {
        return referencia;
    }

    public static void setDimensiones(double nuevoAncho, double nuevoAlto) {
        ancho = nuevoAncho;
        alto = nuevoAlto;
    }
}
