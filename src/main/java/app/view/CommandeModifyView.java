package app.view;

import app.controller.CommandeController;
import app.model.Article;
import app.model.ArticleCommande;
import app.model.Commande;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.List;

public class CommandeModifyView {

    private Stage stage;
    private TextField clientCommande;
    private Button addArticleCommande;
    private Button saveButton;
    private TableView<ArticleCommande> articleCommandeTable;
    private CommandeController commandeController;
    private ObservableList<ArticleCommande> articleCommandeObservableList;

    public CommandeModifyView(CommandeController commandeController, ObservableList<ArticleCommande> articleCommandeObservableList) {
        this.commandeController = commandeController;
        this.articleCommandeObservableList = articleCommandeObservableList;
        stage = new Stage();
        stage.setTitle("Création de commande");
        stage.initModality(Modality.APPLICATION_MODAL);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        clientCommande = new TextField("Client");
        clientCommande.setEditable(false);

        addArticleCommande = new Button("Ajouter un article");

        saveButton = new Button("Enregistrer");

        articleCommandeTable = new TableView<>();
        articleCommandeTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<ArticleCommande, String> colArticles = new TableColumn<>("Article");
        colArticles.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getArticle().getNomArticle())
        );

        TableColumn<ArticleCommande, String> colArticleQuantity = new TableColumn<>("Quantité");
        colArticleQuantity.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getQuantite()))
        );

        TableColumn<ArticleCommande, Void> colSupprimer = new TableColumn<>("Supprimer");
        colSupprimer.setCellFactory(new Callback<TableColumn<ArticleCommande, Void>, TableCell<ArticleCommande, Void>>() {
            @Override
            public TableCell<ArticleCommande, Void> call(TableColumn<ArticleCommande, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button("Supprimer");
                    {
                        btn.setOnAction((event) -> {
                            commandeController.deleteArticle(getIndex());
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
            }
        });


        articleCommandeTable.getColumns().addAll(colArticles, colArticleQuantity, colSupprimer);

        gridPane.addRow(0, new Label("Client:"), clientCommande);
        gridPane.addRow(1, addArticleCommande);
        gridPane.addRow(2, articleCommandeTable);
        gridPane.addRow(3, saveButton);

        Scene scene = new Scene(gridPane, 400, 300);
        stage.setScene(scene);
    }

    public void setCommandeFields(Commande commande,ObservableList<ArticleCommande> articleCommandeObservableList) {
        clientCommande.setText(commande.getClient().getLogin());
        articleCommandeTable.setItems(articleCommandeObservableList);
    }

    public void show() {
        stage.showAndWait();
    }

    public void close() {
        stage.close();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public Button getAddArticleCommande() {
        return addArticleCommande;
    }

    public Button getSaveButton() { return saveButton; }
}
