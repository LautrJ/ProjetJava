package app.controller;

import app.model.*;
import app.view.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
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
        this.commandeObservableList = CommandeListSingleton.getInstance().getCommandeObservableList();
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

    public void updateCommandesStatus() {
        for (Commande commande : commandeObservableList) {
            if (commande.getStatusCommande() == Commande.StatusCommande.EN_ATTENTE) {
                boolean canValidate = true;
                for (ArticleCommande articleCommande : commande.getListeArticles()) {
                    Article article = articleCommande.getArticle();
                    if (articleCommande.getQuantite() > article.getStockDispo()) {
                        canValidate = false;
                        break;
                    }
                }
                if (canValidate) {
                    commande.setStatusCommande(Commande.StatusCommande.VALIDEE);
                }
            }
        }
    }

    public void saveNewCommande(CommandeAddView commandeAddView) {
        Client client = commandeAddView.getSelectedClient();
        Date date = new Date();
        Commande.StatusCommande status = Commande.StatusCommande.EN_ATTENTE;

        List<ArticleCommande> articles = articleCommandeObservableList;

        Commande newCommande = new Commande(commandeObservableList.size() + 1, client, date, status, articles);
        commandeObservableList.add(newCommande);
        updateCommandesStatus();
    }

    public void saveModifiedCommande(Commande commande) {
        for(int i=0;i<commandeObservableList.size();i++){
            if(commandeObservableList.get(i).getId()==(commande.getId())) {
                commandeObservableList.set(i, commande);
            };
        }
        updateCommandesStatus();
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
        if(commande.getStatusCommande() == Commande.StatusCommande.EN_ATTENTE) {
            showAlert("Impossible car stock insuffisant");
        } else if(commande.getStatusCommande() == Commande.StatusCommande.VALIDEE || commande.getStatusCommande() == Commande.StatusCommande.EXPEDIEE){
            BonDeLivraisonView bonDeLivraisonView = new BonDeLivraisonView(commande);
            commande.setStatusCommande(Commande.StatusCommande.EXPEDIEE);
            updateCommandeInList(commande);
            bonDeLivraisonView.show();
        } else {
            showAlert("Action impossible");
        }
    }

    public void genererFacture(Commande commande) {
        if(commande.getStatusCommande() == Commande.StatusCommande.EXPEDIEE || commande.getStatusCommande() == Commande.StatusCommande.EN_COURS) {
            Double prixTotal = calculerPrixTotal(commande);
            FactureView factureView = new FactureView(commande, prixTotal);
            commande.setStatusCommande(Commande.StatusCommande.EN_COURS);
            updateCommandeInList(commande);
            factureView.show();
        } else {
            showAlert("Action Impossible");
        }
    }

    public void payeeCommande(Commande commande) {
        if(commande.getStatusCommande() == Commande.StatusCommande.EN_COURS) {
            commande.setStatusCommande(Commande.StatusCommande.PAYEE);
            updateCommandeInList(commande);
        } else {
            showAlert("Action impossible");
        }
    }

    private void updateCommandeInList(Commande commande) {
        for (int i = 0; i < commandeObservableList.size(); i++) {
            if (commandeObservableList.get(i).getId() == commande.getId()) {
                commandeObservableList.set(i, new Commande(
                        commande.getId(),
                        commande.getClient(),
                        commande.getDateDeCommande(),
                        commande.getStatusCommande(),
                        commande.getListeArticles()
                ));
                break;
            }
        }
    }

    public void deleteCommande(int index) { commandeObservableList.remove(index); }
    public void deleteArticle(int index) { articleCommandeObservableList.remove(index); }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public ObservableList<Commande> getCommandeObservableList() { return commandeObservableList; }
}
