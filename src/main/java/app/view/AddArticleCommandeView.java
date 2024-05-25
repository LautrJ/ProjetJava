package app.view;

import app.model.Article;
import app.model.ArticleListSingleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class AddArticleCommandeView {

    private Stage stage;
    private ObservableList<Article> articleObservableList;
    private ComboBox<Article> articleDropdown;
    private TextField quantityField;
    private Button saveButton;

    public AddArticleCommandeView() {
        this.articleObservableList = ArticleListSingleton.getInstance().getArticleObservableList();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Sélection d'article et de sa quantité");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label articleLabel = new Label("Article:");
        articleDropdown = new ComboBox<>();
        articleDropdown.setPromptText("Sélectionner un article");

        articleDropdown.setCellFactory(param -> new ListCell<Article>() {
            @Override
            protected void updateItem(Article item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getNomArticle() == null) {
                    setText(null);
                } else {
                    setText(item.getNomArticle());
                }
            }
        });

        articleDropdown.setButtonCell(new ListCell<Article>() {
            @Override
            protected void updateItem(Article item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getNomArticle() == null) {
                    setText(null);
                } else {
                    setText(item.getNomArticle());
                }
            }
        });

        articleDropdown.setItems(articleObservableList);

        Label quantityLabel = new Label("Quantité:");
        quantityField = new TextField();
        quantityField.setPromptText("Entrez la quantité");

        // Submit Button
        saveButton = new Button("Valider");

        // Add components to grid
        gridPane.add(articleLabel, 0, 0);
        gridPane.add(articleDropdown, 1, 0);
        gridPane.add(quantityLabel, 0, 1);
        gridPane.add(quantityField, 1, 1);
        gridPane.add(saveButton, 1, 2);

        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
    }

    public void show() {
        stage.showAndWait();
    }

    public void close() {
        stage.close();
    }

    public Article getSelectedArticle() {
        return articleDropdown.getValue();
    }

    public int getQuantity() {
        try {
            return Integer.parseInt(quantityField.getText());
        } catch (NumberFormatException e) {
            return 0; // Default value if input is not a valid integer
        }
    }

    public Button getSaveButton() {
        return saveButton;
    }
}
