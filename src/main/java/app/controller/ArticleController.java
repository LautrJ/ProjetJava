package app.controller;

import app.model.*;
import app.view.ArticleAddView;
import app.view.ArticleModifyView;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class ArticleController {
    private ObservableList<Article> articleObservableList;
    private ObservableList<Commande> commandeObservableList;
    private Stage parentStage;
    private CommandeController commandeController;

    public ArticleController(Stage parentStage, CommandeController commandeController) {
        this.parentStage = parentStage;
        this.articleObservableList = ArticleListSingleton.getInstance().getArticleObservableList();
        this.commandeObservableList = CommandeListSingleton.getInstance().getCommandeObservableList();
        this.commandeController = commandeController;
    }

    public void afficherArticleAddView() {
        ArticleAddView articleAddView = new ArticleAddView();

        articleAddView.getSaveButton().setOnAction(e -> {
            Article newArticle = articleAddView.getNewArticle();
            addArticle(newArticle);
            articleAddView.close();
        });

        articleAddView.show();
    }

    public void afficherArticleModifyView(Article article) {
        ArticleModifyView articleModifyView = new ArticleModifyView();
        articleModifyView.setArticleFields(article);

        articleModifyView.getSaveButton().setOnAction(e -> {
            Article modifiedArticle = articleModifyView.getModifiedArticle();
            saveModifiedArticle(modifiedArticle);
            articleModifyView.close();
        });

        articleModifyView.show();
    }

    public void saveModifiedArticle(Article modifiedArticle) {
        for(int i=0;i<articleObservableList.size();i++) {
            if(articleObservableList.get(i).getNomArticle().equals(modifiedArticle.getNomArticle())) {
                articleObservableList.set(i, modifiedArticle);
                break;
            }
        }
        commandeController.updateCommandesStatus();
    }

    public void deleteArticle(int index) {
        articleObservableList.remove(index);
    }

    public void addArticle(Article newArticle) {
        if(newArticle != null) {
            articleObservableList.add(newArticle);
            System.out.println("Article ajouté : " + newArticle);
        } else {
            System.out.println("Article non ajouté, données non valides");
        }
    }
}
