package app.controller;

import app.view.*;
import app.controller.ClientController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class MainController {

    private MainView view;
    private ClientView clientView;
    private CommandeView commandeView;
    private ArticleView stockView;

    public MainController(MainView view, Stage primaryStage){
        this.view = view;
        initialize(primaryStage);
    }

    private void initialize(Stage primaryStage) {
        ClientController clientController = new ClientController(primaryStage);
        CommandeController commandeController = new CommandeController(primaryStage);
        ArticleController articleController = new ArticleController(primaryStage);
        clientView = new ClientView(clientController);
        commandeView = new CommandeView(commandeController);
        stockView = new ArticleView(articleController);

        view.getMenuClient().setOnAction(event -> view.getRoot().setCenter(clientView.getRoot()));

        view.getMenuCommande().setOnAction(event -> view.getRoot().setCenter(commandeView.getRoot()));

        view.getMenuStock().setOnAction(event -> view.getRoot().setCenter(stockView.getRoot()));
    }
}
