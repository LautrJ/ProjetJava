package app.model;

import java.util.Date;
import java.util.List;

public class Commande {
    private int idCommande;
    private Client client;
    private Date dateDeCommande;
    private StatusCommande statusCommande;
    private List<ArticleCommande> listeArticles;

    public Commande(int idCommande, Client client, Date dateDeCommande, StatusCommande statusCommande, List<ArticleCommande> listeArticles) {
        this.idCommande = idCommande;
        this.client = client;
        this.dateDeCommande = dateDeCommande;
        this.statusCommande = statusCommande;
        this.listeArticles = listeArticles;
    }

    public enum StatusCommande {
        EN_ATTENTE,
        VALIDEE,
        EXPEDIEE,
        LIVREE,
        ANNULEE;
    }

    public int getId() { return idCommande; }
    public void setId(int idCommande) { this.idCommande = idCommande; }

    public Client getClient() { return client;}
    public void setRefClient(Client client) {  this.client = client; }

    public Date getDateDeCommande() { return dateDeCommande; }
    public void setDateDeCommande(Date dateDeCommande) { this.dateDeCommande = dateDeCommande; }

    public StatusCommande getStatusCommande() { return statusCommande; }
    public void setStatusCommande(StatusCommande statusCommande) { this.statusCommande = statusCommande; }

    public List<ArticleCommande> getListeArticles() { return listeArticles; }
    public void setListeArticles(List<ArticleCommande> listeArticles) {
        this.listeArticles = listeArticles;
    }
    public void addListeArticles(ArticleCommande article) {
        this.listeArticles.add(article);
    }
}