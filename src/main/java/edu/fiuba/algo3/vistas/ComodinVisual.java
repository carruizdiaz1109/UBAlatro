package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.entidades.comodines.Comodin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.Objects;

public class ComodinVisual  extends ImageView {
    private final Comodin comodinReferencia;

    public ComodinVisual(Comodin comodin, String imagePath, double width, double height) {
        super(new Image(Objects.requireNonNull(ComodinVisual.class.getResourceAsStream(imagePath))));
        this.setFitWidth(width);
        this.setFitHeight(height);
        this.setPreserveRatio(true);
        this.comodinReferencia = comodin;
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
            System.out.println("Comodín soltado en: " + this.getLayoutX() + ", " + this.getLayoutY());
        });
    }

    public Comodin getComodin() {
        return this.comodinReferencia;
    }
}
