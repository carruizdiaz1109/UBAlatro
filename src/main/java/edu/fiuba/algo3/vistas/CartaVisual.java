package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.entidades.CartaPoker;
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
        this.hacerArrastrable();
    }

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
            System.out.println("Carta soltada en: " + this.getLayoutX() + ", " + this.getLayoutY());
        });
    }

    public CartaPoker getCarta() {
        return this.cartaReferencia;
    }

}