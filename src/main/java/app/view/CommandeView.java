package app.view;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class CommandeView {

    private StackPane root;

    public CommandeView() {
        root = new StackPane();
        setupLayout();
    }

    private void setupLayout() {
        Label titre = new Label("Gestion des commandes");
        root.getChildren().add(titre);
    }

    public StackPane getRoot() {
        return root;
    }
}
