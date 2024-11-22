package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.CartaPoker;
import edu.fiuba.algo3.Palo;
import edu.fiuba.algo3.Valor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class CartaVisual extends CartaPoker {

    private final ImageView imageView;

    public CartaVisual(String rutaImagen, double ancho, double alto) {
        super(Valor.AS, Palo.PICAS);
        this.imageView = new ImageView(new Image(rutaImagen));
        this.imageView.setFitWidth(ancho);
        this.imageView.setFitHeight(alto);
        hacerArrastrable();
    }

    public ImageView getImageView() {
        return imageView;
    }

    private void hacerArrastrable() {
        final double[] offsetX = {0};
        final double[] offsetY = {0};

        imageView.setOnMousePressed((MouseEvent e) -> {
            offsetX[0] = e.getSceneX() - imageView.getLayoutX();
            offsetY[0] = e.getSceneY() - imageView.getLayoutY();
        });

        imageView.setOnMouseDragged((MouseEvent e) -> {
            imageView.setLayoutX(e.getSceneX() - offsetX[0]);
            imageView.setLayoutY(e.getSceneY() - offsetY[0]);
        });
    }
}