package fr.univartois.butinfo.sae.cabinet;

import fr.univartois.butinfo.sae.cabinet.controller.AccueilController;
import fr.univartois.butinfo.sae.cabinet.model.modele.CabinetFacade;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CabinetApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        CabinetFacade facade = new CabinetFacade();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr.univartois.butinfo.sae.cabinet.view/AccueilView.fxml"));
        Scene sceneAccueil = new Scene(loader.load(), 600, 400);

        AccueilController controller = loader.getController();
        controller.initContext(stage, sceneAccueil, facade);

        stage.setTitle("Système de Gestion - Cabinet Médical");
        stage.setScene(sceneAccueil);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}