package app.controller;

import app.model.*;
import app.view.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.util.Date;
import java.util.List;

public class CommandeController {
    private ObservableList<Commande> commandeObservableList;
    private ObservableList<ArticleCommande> articleCommandeObservableList;
    private ObservableList<Client> clientObservableList;
    private ObservableList<Article> articleObservableList;
    private Stage parentStage;

    public CommandeController(Stage parentStage) {
        this.parentStage = parentStage;
        this.commandeObservableList = FXCollections.observableArrayList(CommandeData.getCommandes());
        this.clientObservableList = ClientListSingleton.getInstance().getClientObservableList();
        this.articleObservableList = ArticleListSingleton.getInstance().getArticleObservableList();
    }

    public void afficherCommandeAddView(){
        articleCommandeObservableList = FXCollections.observableArrayList();
        CommandeAddView commandeAddView = new CommandeAddView(this, clientObservableList, articleObservableList, articleCommandeObservableList);

        commandeAddView.getAddArticleCommande().setOnAction(e -> {
            afficherAddArticleCommandeView(articleCommandeObservableList);
        });

        commandeAddView.getSaveButton().setOnAction(e -> {
            saveNewCommande(commandeAddView);
            commandeAddView.close();
        });

        commandeAddView.show();
    }

    public void saveNewCommande(CommandeAddView commandeAddView) {
        Client client = commandeAddView.getSelectedClient();
        Date date = new Date();
        ArticleController articleController = new ArticleController(parentStage);
        Commande.StatusCommande status = Commande.StatusCommande.EN_ATTENTE;

        List<ArticleCommande> articles = articleCommandeObservableList;

        Commande newCommande = new Commande(commandeObservableList.size() + 1, client, date, status, articles);
        commandeObservableList.add(newCommande);
    }

    public void afficherCommandeModifyView(Commande commande) {
        this.articleCommandeObservableList = FXCollections.observableArrayList(commande.getListeArticles());
        CommandeModifyView commandeModifyView = new CommandeModifyView(this, articleCommandeObservableList);

        commandeModifyView.setCommandeFields(commande,articleCommandeObservableList);

        commandeModifyView.getAddArticleCommande().setOnAction(e -> {
            afficherAddArticleCommandeView(articleCommandeObservableList);
        });

        commandeModifyView.getSaveButton().setOnAction(e -> {
            commande.setListeArticles(articleCommandeObservableList);
            saveModifiedCommande(commande);
            commandeModifyView.close();
        });

        commandeModifyView.show();
    }

    public void afficherAddArticleCommandeView(ObservableList<ArticleCommande> listeArticles) {
        AddArticleCommandeView addArticleCommandeView = new AddArticleCommandeView();

        addArticleCommandeView.getSaveButton().setOnAction(e -> {
            ArticleCommande newArticle = new ArticleCommande(addArticleCommandeView.getSelectedArticle(),addArticleCommandeView.getQuantity());
            listeArticles.add(newArticle);
            addArticleCommandeView.close();
        });

        addArticleCommandeView.show();
    }

    public void saveModifiedCommande(Commande commande) {
        for(int i=0;i<commandeObservableList.size();i++){
            if(commandeObservableList.get(i).getId()==(commande.getId())) {
                commandeObservableList.set(i, commande);
            };
        }
    }

    public double calculerPrixTotal(Commande commande) {
        double prixTotal = 0.0;
        for (ArticleCommande articleCommande : commande.getListeArticles()) {
            double prixArticle = articleCommande.getArticle().getPrixUnitaire();
            int quantite = articleCommande.getQuantite();
            prixTotal += prixArticle * quantite;
        }
        return prixTotal;
    }

    public void genererBonDeLivraison(Commande commande) {
        BonDeLivraisonView bonDeLivraisonView = new BonDeLivraisonView(commande);
        bonDeLivraisonView.show();
    }

    public void genererFacture(Commande commande) {
        Double prixTotal = calculerPrixTotal(commande);
        FactureView factureView = new FactureView(commande, prixTotal);
        factureView.show();
    }

    public void deleteCommande(int index) { commandeObservableList.remove(index); }
    public void deleteArticle(int index) { articleCommandeObservableList.remove(index); }

    public ObservableList<Commande> getCommandeObservableList() { return commandeObservableList; }
}
