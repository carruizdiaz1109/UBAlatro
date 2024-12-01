package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.TiendaController;
import edu.fiuba.algo3.modelo.entidades.Tienda;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class TiendaVisual {

    private final Stage stage;
    private Tienda tienda;


    public TiendaVisual(Stage stage) {
        this.stage = stage;
    }

    public void setTienda(Tienda tiendaAMostar) {
        this.tienda = tiendaAMostar;
    }

    public void mostrar() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tienda.fxml"));
        Parent root = loader.load();
        TiendaController controller = loader.getController();

        controller.setTienda(this.tienda);
        controller.visualizarComprables();

        stage.setScene(new Scene(root));
        stage.setTitle("UBAlatro-Tienda");
        stage.show();
    }

}
