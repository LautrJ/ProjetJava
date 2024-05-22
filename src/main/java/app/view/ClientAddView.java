package app.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClientAddView {
    private Stage stage;
    private TextField loginField;
    private TextField passwordField;
    private TextField nomField;
    private TextField prenomField;
    private TextField adrLivrField;
    private TextField adrFactField;
    private Button saveButton;

    public ClientAddView() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Création d'un nouveau client");

        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        Label titre = new Label("Création d'un nouveau client");
        titre.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(5);

        loginField = new TextField();
        passwordField = new TextField();
        nomField = new TextField();
        prenomField = new TextField();
        adrLivrField = new TextField();
        adrFactField = new TextField();

        formGrid.addRow(0, new Label("Login:"), loginField);
        formGrid.addRow(1, new Label("Password:"), passwordField);
        formGrid.addRow(2, new Label("Nom:"), nomField);
        formGrid.addRow(3, new Label("Prénom:"), prenomField);
        formGrid.addRow(4, new Label("Adresse de livraison:"), adrLivrField);
        formGrid.addRow(5, new Label("Adresse de facturation:"), adrFactField);

        saveButton = new Button("Enregistrer");
        saveButton.setOnAction(e -> close());
        HBox buttonBox = new HBox(saveButton);
        buttonBox.setSpacing(10);

        root.getChildren().addAll(titre, formGrid, buttonBox);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void show() {
        stage.showAndWait();
    }

    public void close() {
        stage.close();
    }

    public TextField getLoginField() {
        return loginField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public TextField getNomField() {
        return nomField;
    }

    public TextField getPrenomField() {
        return prenomField;
    }

    public TextField getAdrLivrField() {
        return adrLivrField;
    }

    public TextField getAdrFactField() {
        return adrFactField;
    }

    public Button getSaveButton() {
        return saveButton;
    }
}
