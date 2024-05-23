package app.view;

import app.controller.CommandeController;
import app.model.ArticleCommande;
import app.model.Commande;
import app.model.CommandeData;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.text.SimpleDateFormat;

public class CommandeView {

    private BorderPane root;
    private TableView<Commande> tableView;
    private CommandeController commandeController;

    public CommandeView(CommandeController commandeController) {
        this.commandeController = commandeController;
        root = new BorderPane();
        setupLayout();
    }

    private void setupLayout() {
        Label titre = new Label("Gestion des commandes");
        titre.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        Button bouttonAjouter = new Button("Ajouter +");
        bouttonAjouter.setOnAction(e -> commandeController.afficherCommandeAddView());

        VBox header = new VBox(10);
        header.getChildren().addAll(titre, bouttonAjouter);

        tableView = new TableView<>();

        TableColumn<Commande, String> colClient = new TableColumn<>("Client");
        colClient.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClient().getLogin()));

        TableColumn<Commande, String> colDate = new TableColumn<>("Date de Commande");
        colDate.setCellValueFactory(cellData -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return new SimpleStringProperty(dateFormat.format(cellData.getValue().getDateDeCommande()));
        });

        TableColumn<Commande, Commande.StatusCommande> colStatus = new TableColumn<>("Statut");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("statusCommande"));

        TableColumn<Commande, String> colArticles = new TableColumn<>("Articles");
        colArticles.setCellValueFactory(cellData -> {
            StringBuilder articlesString = new StringBuilder();
            for (ArticleCommande articleCommande : cellData.getValue().getListeArticles()) {
                articlesString.append(articleCommande.getArticle().getNomArticle()).append(" (").append(articleCommande.getQuantite()).append("), ");
            }
            return new SimpleStringProperty(articlesString.toString());
        });

        tableView.getColumns().addAll(colClient, colDate, colStatus, colArticles);

        ObservableList<Commande> commandeList = FXCollections.observableArrayList(CommandeData.getCommandes());
        tableView.setItems(commandeList);

        root.setTop(header);
        root.setCenter(tableView);
    }

    public BorderPane getRoot() {
        return root;
    }
}
