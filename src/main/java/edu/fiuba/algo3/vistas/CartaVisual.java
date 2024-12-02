package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.entidades.cartas.CartaPoker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class CartaVisual extends ImageView {
    private final CartaPoker cartaReferencia;

    public CartaVisual(CartaPoker carta, String imagePath, double width, double height) {
        super(new Image(Objects.requireNonNull(CartaVisual.class.getResourceAsStream(imagePath))));
        this.setFitWidth(width);
        this.setFitHeight(height);
        this.setPreserveRatio(true);
        this.cartaReferencia = carta;
    }

    public CartaPoker getCarta() {
        return this.cartaReferencia;
    }
}