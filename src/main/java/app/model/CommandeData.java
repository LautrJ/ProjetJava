package app.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CommandeData {

    public static List<Commande> getCommandes() {
        List<Article> articles = ArticleData.getArticles();
        List<Client> clients = ClientData.getClients();

        Article article1 = articles.get(0);
        Article article2 = articles.get(1);
        Article article3 = articles.get(2);
        Article article4 = articles.get(3);
        Article article5 = articles.get(4);
        Article article6 = articles.get(5);
        Article article7 = articles.get(6);
        Article article8 = articles.get(7);
        Article article9 = articles.get(8);
        Article article10 = articles.get(9);

        ArticleCommande articleCommande1 = new ArticleCommande(article1, 1);
        ArticleCommande articleCommande2 = new ArticleCommande(article2, 2);
        ArticleCommande articleCommande3 = new ArticleCommande(article3, 1);
        ArticleCommande articleCommande4 = new ArticleCommande(article4, 1);
        ArticleCommande articleCommande5 = new ArticleCommande(article5, 1);
        ArticleCommande articleCommande6 = new ArticleCommande(article6, 2);
        ArticleCommande articleCommande7 = new ArticleCommande(article7, 5);
        ArticleCommande articleCommande8 = new ArticleCommande(article8, 1);
        ArticleCommande articleCommande9 = new ArticleCommande(article9, 1);
        ArticleCommande articleCommande10 = new ArticleCommande(article10, 3);

        Commande commande1 = new Commande(1, clients.get(0), new Date(), Commande.StatusCommande.EN_ATTENTE, new ArrayList<>(Arrays.asList(articleCommande1, articleCommande2, articleCommande3)));
        Commande commande2 = new Commande(2, clients.get(1), new Date(), Commande.StatusCommande.VALIDEE, new ArrayList<>(Arrays.asList(articleCommande4, articleCommande5)));
        Commande commande3 = new Commande(3, clients.get(2), new Date(), Commande.StatusCommande.EXPEDIEE, new ArrayList<>(Arrays.asList(articleCommande6, articleCommande7, articleCommande8, articleCommande9, articleCommande10)));

        return new ArrayList<>(Arrays.asList(commande1, commande2, commande3));
    }
}
