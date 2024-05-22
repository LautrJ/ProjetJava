package app.view;

import app.model.Client;
import app.model.ClientData;
import app.controller.ClientController;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;


public class ClientView {

    private BorderPane root;
    private TableView<Client> tableView;
    private ClientController clientController; // Ajout de la référence au contrôleur

    public ClientView(ClientController clientController) { // Prend le contrôleur en paramètre
        this.clientController = clientController; // Initialise la référence au contrôleur
        root = new BorderPane();
        setupLayout();
    }

    private void setupLayout() {
        Label titre = new Label("Gestion des clients");
        titre.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        Button bouttonAjouter = new Button("Ajouter +");
        bouttonAjouter.setOnAction(e -> clientController.afficherClientAddView());

        VBox header = new VBox(10);
        header.getChildren().addAll(titre, bouttonAjouter);

        tableView = new TableView<>();

        TableColumn<Client, String> colNom = new TableColumn<>("Nom");
        colNom.setCellValueFactory(new PropertyValueFactory<>("nomClient"));

        TableColumn<Client, String> colPrenom = new TableColumn<>("Prenom");
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        TableColumn<Client, String>  colAdrLivr = new TableColumn<>("Adresse de livraison");
        colAdrLivr.setCellValueFactory(new PropertyValueFactory<>("adresseDeLivraison"));

        TableColumn<Client, String> colAdrFact = new TableColumn<>("Adresse de facturation");
        colAdrFact.setCellValueFactory(new PropertyValueFactory<>("adresseDeFacturation"));

        TableColumn<Client, Void> colModifier = new TableColumn<>("Modifier");
        colModifier.setCellFactory(new Callback<TableColumn<Client, Void>, TableCell<Client, Void>>() {
            @Override
            public TableCell<Client, Void> call(TableColumn<Client, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button("Modifier");

                    {
                        btn.setOnAction((event) -> {
                            Client client = getTableView().getItems().get(getIndex());
                            // Action de modification
                            System.out.println("Modifier : " + client);
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

        // Colonne Supprimer
        TableColumn<Client, Void> colSupprimer = new TableColumn<>("Supprimer");
        colSupprimer.setCellFactory(new Callback<TableColumn<Client, Void>, TableCell<Client, Void>>() {
            @Override
            public TableCell<Client, Void> call(TableColumn<Client, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((event) -> {
                            Client client = getTableView().getItems().get(getIndex());
                            // Action de suppression
                            System.out.println("Supprimer : " + client);
                            // Suppression du client de la liste
                            getTableView().getItems().remove(client);
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

        tableView.getColumns().addAll(colNom, colPrenom, colAdrLivr, colAdrFact, colModifier, colSupprimer);

        ObservableList<Client> clientList = FXCollections.observableArrayList(ClientData.getClients());
        tableView.setItems(clientList);

        root.setTop(header);
        root.setCenter(tableView);
    }

    public BorderPane getRoot() {
        return root;
    }
}
