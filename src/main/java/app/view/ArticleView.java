package app.view;

import app.model.Article;
import app.controller.ArticleController;
import app.model.ArticleListSingleton;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class ArticleView {

    private BorderPane root;
    private TableView<Article> tableView;
    private ArticleController articleController;
    private ObservableList<Article> articleObservableList;

    public ArticleView(ArticleController articleController){
        this.articleController = articleController;
        this.articleObservableList = ArticleListSingleton.getInstance().getArticleObservableList();
        root = new BorderPane();
        setupLayout();
    }

    private void setupLayout() {
        Label titre = new Label("Gestion des stocks");
        titre.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        Button bouttonAjouter = new Button("Ajouter +");
        bouttonAjouter.setOnAction(e -> articleController.afficherArticleAddView());

        VBox header = new VBox(10);
        header.getChildren().addAll(titre, bouttonAjouter);

        tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Article, String> colNom = new TableColumn<>("Article");
        colNom.setCellValueFactory(new PropertyValueFactory<>("nomArticle"));

        TableColumn<Article, String> colDesc = new TableColumn<>("Description");
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Article, Double> colPrix = new TableColumn<>("Prix unitaire");
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
        colPrix.setCellFactory(tc -> new TableCell<Article, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%.2f €", item));
                }
            }
        });

        TableColumn<Article, Integer> colStock = new TableColumn<>("Stock disponible");
        colStock.setCellValueFactory(new PropertyValueFactory<>("stockDispo"));

        TableColumn<Article, Void> colModifier = new TableColumn<>("Modifier");
        colModifier.setCellFactory(new Callback<TableColumn<Article, Void>, TableCell<Article, Void>>() {
            @Override
            public TableCell<Article, Void> call(TableColumn<Article, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button("Modifier");
                    {
                        btn.setOnAction((event) -> {
                            Article article = getTableView().getItems().get(getIndex());
                            articleController.afficherArticleModifyView(article);
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

        TableColumn<Article, Void> colSupprimer = new TableColumn<>("Supprimer");
        colSupprimer.setCellFactory(new Callback<TableColumn<Article, Void>, TableCell<Article, Void>>() {
            @Override
            public TableCell<Article, Void> call(TableColumn<Article, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button("Supprimer");
                    {
                        btn.setOnAction((event) -> {
                            articleController.deleteArticle(getIndex());
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

        tableView.getColumns().addAll(colNom, colDesc, colPrix, colStock, colModifier, colSupprimer);

        tableView.setItems(articleObservableList);

        root.setTop(header);
        root.setCenter(tableView);
    }
    public BorderPane getRoot() {
        return root;
    }
}
