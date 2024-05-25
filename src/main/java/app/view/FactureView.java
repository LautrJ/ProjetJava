package app.view;

import app.model.ArticleCommande;
import app.model.Commande;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;

public class FactureView {

    private Stage stage;
    private Commande commande;
    private Double prixTotal;

    public FactureView(Commande commande, Double prixTotal) {
        this.commande = commande;
        this.prixTotal = prixTotal;
        stage = new Stage();
        setupLayout();
    }

    private void setupLayout() {
        VBox vbox = new VBox(10);

        Label titre = new Label("Bon de Livraison");
        titre.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label clientNom = new Label("Nom du client : " + commande.getClient().getNomClient());
        Label clientPrenom = new Label("Prénom du client : " + commande.getClient().getPrenom());
        Label adresseFacturation = new Label("Adresse de facturation : " + commande.getClient().getAdresseDeFacturation());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Label dateCommande = new Label("Date de commande : " + dateFormat.format(commande.getDateDeCommande()));

        vbox.getChildren().addAll(titre, clientNom, clientPrenom, adresseFacturation, dateCommande);

        GridPane gridPane = new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(10);

        int row = 0;
        for (ArticleCommande articleCommande : commande.getListeArticles()) {
            Label articleLabel = new Label(articleCommande.getArticle().getNomArticle());
            Label quantiteLabel = new Label(String.valueOf(articleCommande.getQuantite()));
            gridPane.add(articleLabel, 0, row);
            gridPane.add(quantiteLabel, 1, row);
            row++;
        }

        Label prixLabel = new Label("Prix total de la commande : " + Double.toString(prixTotal) + "€");
        gridPane.add(prixLabel,2,row);

        vbox.getChildren().add(gridPane);

        BorderPane root = new BorderPane();
        root.setCenter(vbox);

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
    }

    public void show() {
        stage.showAndWait();
    }
}
