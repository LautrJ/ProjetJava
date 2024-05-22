package app.controller;

import app.view.ClientAddView;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClientController {
    private Stage parentStage;

    public ClientController(Stage parentStage) {
        this.parentStage = parentStage;
    }

    public void afficherClientAddView() {
        ClientAddView clientAddView = new ClientAddView();
        clientAddView.show();
    }
}
