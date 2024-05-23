package app.view;

import app.model.Article;
import app.model.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class CommandeAddView {

    private Stage stage;
    private ComboBox<String> clientDropdown;
    private VBox articleBox;
    private List<Article> articles;

    public CommandeAddView(List<Client> clients, List<Article> articles) {
        stage = new Stage();
        stage.setTitle("Création de commande");
        stage.initModality(Modality.APPLICATION_MODAL);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // Client Dropdown
        clientDropdown = new ComboBox<>();
        ObservableList<String> clientLogin = FXCollections.observableArrayList();
        for (Client client : clients) {
            clientLogin.add(client.getLogin());
        }
        clientDropdown.setItems(clientLogin);
        clientDropdown.setPromptText("Sélectionner un client");

        // Article Box
        articleBox = new VBox();
        this.articles = articles;

        Button minusButton = new Button("-");
        minusButton.setOnAction(e -> removeArticleQuantityField());

        Button plusButton = new Button("+");
        plusButton.setOnAction(e -> addArticleQuantityField());

        Button saveButton = new Button("Enregistrer");
        saveButton.setOnAction(e -> saveCommande());

        gridPane.addRow(0, new Label("Client:"), clientDropdown);
        gridPane.addRow(1, new Label("Articles:"), articleBox);
        gridPane.addRow(2, minusButton, plusButton, saveButton);

        Scene scene = new Scene(gridPane, 400, 300);
        stage.setScene(scene);
    }

    public void show() {
        stage.showAndWait();
    }

    private void removeArticleQuantityField() {
        //ArticleQuantityField articleQuantityField = new ArticleQuantityField(articles);
        //articleBox.getChildren().add(articleQuantityField);
    }
    private void addArticleQuantityField() {
        //ArticleQuantityField articleQuantityField = new ArticleQuantityField(articles);
        //articleBox.getChildren().add(articleQuantityField);
    }

    private void saveCommande() {
        String selectedClient = clientDropdown.getValue();
        if (selectedClient == null) {
            showAlert("Veuillez sélectionner un client.");
            return;
        }

        // Récupérer les articles et leurs quantités
        //List<ArticleQuantity> articleQuantities = ArticleQuantityField.getArticleQuantities();

        // Ici, vous pouvez appeler votre méthode dans le contrôleur pour enregistrer la commande
        // Par exemple : commandeController.saveCommande(selectedClient, articleQuantities);

        stage.close();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
