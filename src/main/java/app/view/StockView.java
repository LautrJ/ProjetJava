package app.view;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class StockView {

    private StackPane root;

    public StockView() {
        root = new StackPane();
        setupLayout();
    }

    private void setupLayout() {
        Label titre = new Label("Gestion des stocks");
        root.getChildren().add(titre);
    }

    public StackPane getRoot() {
        return root;
    }
}
