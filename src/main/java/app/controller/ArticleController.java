package app.controller;

import app.model.*;
import app.view.ArticleAddView;
import app.view.ArticleModifyView;
import app.view.ClientModifyView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class ArticleController {
    private ObservableList<Article> articleObservableList;
    private ObservableList<Commande> commandeObservableList;
    private Stage parentStage;
    private CommandeController commandeController;

    public ArticleController(Stage parentStage) {
        this.parentStage = parentStage;
        this.articleObservableList = ArticleListSingleton.getInstance().getArticleObservableList();
        this.commandeObservableList = FXCollections.observableArrayList(CommandeData.getCommandes());
        this.commandeController = new CommandeController(parentStage);
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

    public Article findArticleByName(ObservableList<Article> articleObservableList, String nomArticle) {
        for (Article article : articleObservableList) {
            if(article.getNomArticle() == nomArticle) {
                return article;
            }
        }
        return null;
    }

    public ObservableList<Article> getArticleObservableList() {
        return articleObservableList;
    }
}
