package app.view;

import app.model.Article;
import app.model.Client;
import app.model.ArticleCommande;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.beans.property.SimpleObjectProperty;
import javafx.util.converter.IntegerStringConverter;


import java.util.List;

public class osef {
    private Stage stage;
    private ComboBox<String> clientDropdown;
    private TableView<Article> articleTableView;
    private Button saveButton;

    public osef(List<Client> clients, List<Article> articles) {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Création de commande");

        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        // Client Dropdown
        clientDropdown = new ComboBox<>();
        ObservableList<String> clientNames = FXCollections.observableArrayList();
        for (Client client : clients) {
            clientNames.add(client.getNomClient());
        }
        clientDropdown.setItems(clientNames);
        clientDropdown.setPromptText("Sélectionner un client");

        // Article Table
        articleTableView = new TableView<>();
        TableColumn<Article, String> nomArticleColumn = new TableColumn<>("Nom");
        nomArticleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomArticle()));

        TableColumn<Article, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));

        TableColumn<Article, Double> prixUnitaireColumn = new TableColumn<>("Prix Unitaire");
        prixUnitaireColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPrixUnitaire()));

        TableColumn<Article, Integer> stockDispoColumn = new TableColumn<>("Stock Disponible");
        stockDispoColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStockDispo()));

        TableColumn<Article, Integer> inputColumn = new TableColumn<>("Quantité");
        inputColumn.setCellValueFactory(new PropertyValueFactory<>(""));
        inputColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        inputColumn.setOnEditCommit(event -> {
            int rowIndex = event.getTablePosition().getRow();
            Article article = event.getTableView().getItems().get(rowIndex);
            ArticleCommande newArticle = new ArticleCommande(article,event.getNewValue());
        });


        articleTableView.getColumns().addAll(nomArticleColumn, descriptionColumn, prixUnitaireColumn, stockDispoColumn, inputColumn);
        articleTableView.setItems(FXCollections.observableArrayList(articles));

        saveButton = new Button("Enregistrer");

        root.getChildren().addAll(clientDropdown, articleTableView, saveButton);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void show() {
        stage.showAndWait();
    }

    public void close() {
        stage.close();
    }

    public String getSelectedClient() {
        return clientDropdown.getValue();
    }

    public TableView<Article> getArticleTableView() {
        return articleTableView;
    }

    public Button getSaveButton() {
        return saveButton;
    }
}
