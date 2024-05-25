package app.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientListSingleton {

    private static ClientListSingleton instance;
    private ObservableList<Client> clientObservableList;

    private ClientListSingleton() {
        clientObservableList = FXCollections.observableArrayList(ClientData.getClients());
    }

    public static ClientListSingleton getInstance() {
        if (instance == null) {
            instance = new ClientListSingleton();
        }
        return instance;
    }

    public ObservableList<Client> getClientObservableList() {
        return clientObservableList;
    }
}
