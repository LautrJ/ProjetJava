package app.controller;

import app.model.Article;
import app.model.ArticleData;
import app.model.Client;
import app.model.ClientData;
import app.view.CommandeAddView;
import app.view.CommandeAddView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class CommandeController {
    private ObservableList<Client> clientObservableList;
    private ObservableList<Article> articleObservableList;
    private Stage parentStage;

    public CommandeController(Stage parentStage) {
        this.parentStage = parentStage;
        this.clientObservableList = FXCollections.observableArrayList(ClientData.getClients());
        this.articleObservableList = FXCollections.observableArrayList(ArticleData.getArticles());
    }
    public void afficherCommandeAddView(){
        CommandeAddView commandeAddView = new CommandeAddView(clientObservableList, articleObservableList);
        commandeAddView.show();
    }
}
