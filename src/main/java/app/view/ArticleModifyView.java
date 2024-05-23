package app.view;

import app.model.Article;
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

public class ArticleModifyView {
    private Stage stage;
    private TextField nomField;
    private TextField descField;
    private TextField prixField;
    private TextField stockField;
    private Button saveButton;

    public ArticleModifyView() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Modification d'un article");

        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        Label titre = new Label("Modification d'un article");
        titre.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(5);

        nomField = new TextField();
        descField = new TextField();
        prixField = new TextField();
        stockField = new TextField();

        formGrid.addRow(2, new Label("Nom de l'article:"), nomField);
        formGrid.addRow(3, new Label("Description:"), descField);
        formGrid.addRow(4, new Label("Prix unitaire:"), prixField);
        formGrid.addRow(5, new Label("Stock disponible:"), stockField);

        saveButton = new Button("Enregistrer");
        HBox buttonBox = new HBox(saveButton);
        buttonBox.setSpacing(10);

        root.getChildren().addAll(titre, formGrid, buttonBox);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void setArticleFields(Article article) {
        nomField.setText(article.getNomArticle());
        descField.setText(article.getDescription());
        prixField.setText(String.valueOf(article.getPrixUnitaire()));
        stockField.setText(String.valueOf(article.getStockDispo()));
    }

    public Article getModifiedArticle() {
        Article article = new Article(
                nomField.getText(),
                descField.getText(),
                Double.parseDouble(prixField.getText()),
                Integer.parseInt(stockField.getText())
        );
        return article;
    }

    public void show() {
        stage.showAndWait();
    }

    public void close() {
        stage.close();
    }

    public TextField getNomField() {
        return nomField;
    }

    public TextField getDescField() {
        return descField;
    }

    public TextField getPrixField() { return prixField; }

    public TextField getStockField() {
        return stockField;
    }

    public Button getSaveButton() {
        return saveButton;
    }
}
