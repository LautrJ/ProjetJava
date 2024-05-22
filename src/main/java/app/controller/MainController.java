package app.controller;

import app.view.MainView;
import app.view.ClientView;
import app.view.CommandeView;
import app.view.StockView;
import app.controller.ClientController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class MainController {

    private MainView view;
    private ClientView clientView;
    private CommandeView commandeView;
    private StockView stockView;

    public MainController(MainView view, Stage primaryStage){
        this.view = view;
        initialize(primaryStage);
    }

    private void initialize(Stage primaryStage) {
        ClientController clientController = new ClientController(primaryStage);
        clientView = new ClientView(clientController);
        commandeView = new CommandeView();
        stockView = new StockView();

        view.getMenuClient().setOnAction(event -> view.getRoot().setCenter(clientView.getRoot()));

        view.getMenuCommande().setOnAction(event -> view.getRoot().setCenter(commandeView.getRoot()));

        view.getMenuStock().setOnAction(event -> view.getRoot().setCenter(stockView.getRoot()));
    }
}
