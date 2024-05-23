package app.controller;

import app.model.Client;
import app.model.ClientData;
import app.view.ClientAddView;
import app.view.ClientModifyView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClientController {
    private ObservableList<Client> clientObservableList;
    private Stage parentStage;

    public ClientController(Stage parentStage) {
        this.parentStage = parentStage;
        this.clientObservableList = FXCollections.observableArrayList(ClientData.getClients());
    }

    public void afficherClientAddView() {
        ClientAddView clientAddView = new ClientAddView();

        clientAddView.getSaveButton().setOnAction(e -> {
            Client newClient = clientAddView.getNewClient();
            addClient(newClient);
            clientAddView.close();
        });

        clientAddView.show();
    }

    public void afficherClientModifyView(Client client) {
        ClientModifyView clientModifyView = new ClientModifyView();
        clientModifyView.setClientFields(client);

        clientModifyView.getSaveButton().setOnAction(e -> {
            Client modifiedClient = clientModifyView.getModifiedClient();
            saveModifiedClient(modifiedClient);
            clientModifyView.close();
        });

        clientModifyView.show();
    }

    public void saveModifiedClient(Client modifiedClient) {
        for(int i=0;i<clientObservableList.size();i++) {
            if(clientObservableList.get(i).getLogin().equals(modifiedClient.getLogin())) {
                clientObservableList.set(i, modifiedClient);
                break;
            }
        }
    }

    public void deleteClient(int index) {
        clientObservableList.remove(index);
    }

    public void addClient(Client newClient) {
        if(newClient != null) {
            clientObservableList.add(newClient);
            System.out.println("Client ajouté : " + newClient);
        } else {
            System.out.println("Client non ajouté, données non valides");
        }
    }

    public ObservableList<Client> getClientObservableList() { return clientObservableList; }
}
