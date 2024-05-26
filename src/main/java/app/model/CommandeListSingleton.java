package app.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CommandeListSingleton {

    private static CommandeListSingleton instance;
    private ObservableList<Commande> commandeObservableList;

    private CommandeListSingleton() {
        commandeObservableList = FXCollections.observableArrayList(CommandeData.getCommandes());
    }

    public static CommandeListSingleton getInstance() {
        if (instance == null) {
            instance = new CommandeListSingleton();
        }
        return instance;
    }

    public ObservableList<Commande> getCommandeObservableList() {
        return commandeObservableList;
    }
}
