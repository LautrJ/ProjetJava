package app.controller;

import app.view.MainView;
import app.view.ClientView;
import app.view.CommandeView;
import app.view.StockView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MainController {

    private MainView view;
    private ClientView clientView;
    private CommandeView commandeView;
    private StockView stockView;

    public MainController(MainView view){
        this.view = view;
        initialize();
    }

    private void initialize() {
        clientView = new ClientView();
        commandeView = new CommandeView();
        stockView = new StockView();

        view.getMenuClient().setOnAction(event -> view.getRoot().setCenter(clientView.getRoot()));

        view.getMenuCommande().setOnAction(event -> view.getRoot().setCenter(commandeView.getRoot()));

        view.getMenuStock().setOnAction(event -> view.getRoot().setCenter(stockView.getRoot()));
    }
}
