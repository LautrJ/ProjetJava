package app.model;

import java.util.ArrayList;
import java.util.List;

public class ArticleData {

    public static List<Article> getArticles() {
        List<Article> articles = new ArrayList<>();

        articles.add(new Article("Ordinateur portable", "Ordinateur portable haute performance", 1200.00, 10));
        articles.add(new Article( "Smartphone", "Modèle de smartphone dernier cri", 800.00, 25));
        articles.add(new Article( "Casque audio", "Casque à réduction de bruit", 200.00, 15));
        articles.add(new Article( "Écran", "Écran Ultra HD 4K", 350.00, 8));
        articles.add(new Article( "Clavier", "Clavier mécanique", 100.00, 20));
        articles.add(new Article( "Souris", "Souris ergonomique sans fil", 50.00, 30));
        articles.add(new Article( "Tablette", "Tablette 10 pouces avec stylet", 400.00, 12));
        articles.add(new Article( "Montre connectée", "Montre connectée avec suivi de la condition physique", 150.00, 18));
        articles.add(new Article( "Appareil photo", "Appareil photo reflex numérique", 900.00, 5));
        articles.add(new Article( "Imprimante", "Imprimante multifonction sans fil", 250.00, 7));

        return articles;
    }
}
