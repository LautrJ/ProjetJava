package app.controller;

import app.model.Article;
import app.model.ArticleData;
import app.model.Client;
import app.view.ArticleAddView;
import app.view.ArticleModifyView;
import app.view.ClientModifyView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class ArticleController {
    private ObservableList<Article> articleObservableList;
    private Stage parentStage;

    public ArticleController(Stage parentStage) {
        this.parentStage = parentStage;
        this.articleObservableList = FXCollections.observableArrayList(ArticleData.getArticles());
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

    public ObservableList<Article> getArticleObservableList() {
        return articleObservableList;
    }
}
