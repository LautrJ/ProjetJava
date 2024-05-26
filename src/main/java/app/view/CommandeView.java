package app.view;

import app.controller.CommandeController;
import app.model.ArticleCommande;
import app.model.Commande;
import javafx.beans.property.SimpleStringProperty;
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
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

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

        TableColumn<Commande, Void> colModifier = new TableColumn<>("Modifier");
        colModifier.setCellFactory(new Callback<TableColumn<Commande, Void>, TableCell<Commande, Void>>() {
            @Override
            public TableCell<Commande, Void> call(TableColumn<Commande, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button("Modifier");
                    {
                        btn.setOnAction((event) -> {
                            Commande commande = getTableView().getItems().get(getIndex());
                            commandeController.afficherCommandeModifyView(commande);
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

        TableColumn<Commande, Void> colSupprimer = new TableColumn<>("Supprimer");
        colSupprimer.setCellFactory(new Callback<TableColumn<Commande, Void>, TableCell<Commande, Void>>() {
            @Override
            public TableCell<Commande, Void> call(TableColumn<Commande, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button("Supprimer");
                    {
                        btn.setOnAction((event) -> {
                            commandeController.deleteCommande(getIndex());
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

        TableColumn<Commande, Void> colBonDeLivraison = new TableColumn<>("Bon de livraison");
        colBonDeLivraison.setCellFactory(new Callback<TableColumn<Commande, Void>, TableCell<Commande, Void>>() {
            @Override
            public TableCell<Commande, Void> call(TableColumn<Commande, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button("Bon de livraison");
                    {
                        btn.setOnAction((event) -> {
                            Commande commande = getTableView().getItems().get(getIndex());
                            commandeController.genererBonDeLivraison(commande);
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

        TableColumn<Commande, Void> colFacture = new TableColumn<>("Facture");
        colFacture.setCellFactory(new Callback<TableColumn<Commande, Void>, TableCell<Commande, Void>>() {
            @Override
            public TableCell<Commande, Void> call(TableColumn<Commande, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button("Facture");
                    {
                        btn.setOnAction((event) -> {
                            Commande commande = getTableView().getItems().get(getIndex());
                            commandeController.genererFacture(commande);
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

        TableColumn<Commande, Void> colPayee = new TableColumn<>("Payee");
        colPayee.setCellFactory(new Callback<TableColumn<Commande, Void>, TableCell<Commande, Void>>() {
            @Override
            public TableCell<Commande, Void> call(TableColumn<Commande, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button("Payee");
                    {
                        btn.setOnAction((event) -> {
                            Commande commande = getTableView().getItems().get(getIndex());
                            commandeController.payeeCommande(commande);
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

        tableView.getColumns().addAll(colClient, colDate, colStatus, colArticles, colModifier, colSupprimer, colBonDeLivraison, colFacture, colPayee);


        ObservableList<Commande> commandeList = commandeController.getCommandeObservableList();
        tableView.setItems(commandeList);

        root.setTop(header);
        root.setCenter(tableView);
    }

    public BorderPane getRoot() {
        return root;
    }
}
