package app.view;

import app.controller.CommandeController;
import app.model.Article;
import app.model.ArticleCommande;
import app.model.Client;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
public class CommandeAddView {

    private Stage stage;
    private ComboBox<Client> clientDropdown;
    private Button addArticleCommande;
    private Button saveButton;
    private TableView<ArticleCommande> articleCommandeTable;
    private ObservableList<ArticleCommande> articleCommandeObservableList;
    private ObservableList<Article> articleObservableList;
    private CommandeController commandeController;

        public CommandeAddView(CommandeController commandeController, ObservableList<Client> clients, ObservableList<Article> articleObservableList, ObservableList<ArticleCommande> articleCommandeObservableList) {
        this.commandeController = commandeController;
        this.articleObservableList = articleObservableList;
        this.articleCommandeObservableList = articleCommandeObservableList;

        stage = new Stage();
        stage.setTitle("Création de commande");
        stage.initModality(Modality.APPLICATION_MODAL);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        clientDropdown = new ComboBox<>();
        clientDropdown.setItems(clients);
        clientDropdown.setPromptText("Sélectionner un client");
        clientDropdown.setConverter(new StringConverter<Client>() {
            @Override
            public String toString(Client client) {
                return client != null ? client.getLogin() : "";
            }

            @Override
            public Client fromString(String string) {
                return null;
            }
        });

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
        articleCommandeTable.setItems(articleCommandeObservableList);

        gridPane.addRow(0, new Label("Client:"), clientDropdown);
        gridPane.addRow(1, addArticleCommande);
        gridPane.addRow(2, articleCommandeTable);
        gridPane.addRow(3, saveButton);

        Scene scene = new Scene(gridPane, 400, 300);
        stage.setScene(scene);
    }

    public Client getSelectedClient() {
        return clientDropdown.getValue();
    }

    public ObservableList<ArticleCommande> getArticleCommandeData() {
        return articleCommandeObservableList;
    }

    public void show() {
        stage.showAndWait();
    }

    public void close() {
        stage.close();
    }

    public Button getAddArticleCommande() {
        return addArticleCommande;
    }

    public Button getSaveButton() { return saveButton; }
}
