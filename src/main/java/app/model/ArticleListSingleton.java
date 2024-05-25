package app.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ArticleListSingleton {

    private static ArticleListSingleton instance;
    private ObservableList<Article> articleObservableList;

    private ArticleListSingleton() {
        articleObservableList = FXCollections.observableArrayList(ArticleData.getArticles());
    }

    public static ArticleListSingleton getInstance() {
        if (instance == null) {
            instance = new ArticleListSingleton();
        }
        return instance;
    }

    public ObservableList<Article> getArticleObservableList() {
        return articleObservableList;
    }
}
