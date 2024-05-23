package app.model;

public class Article {
    private String nomArticle;
    private String description;
    private Double prixUnitaire;
    private Integer stockDispo;

    public Article(String nom,String desc, Double prix,int stock) {
        this.nomArticle=nom;
        this.description=desc;
        this.prixUnitaire=prix;
        this.stockDispo=stock;
    }

    public String getNomArticle() { return nomArticle; };
    public void setNomArticle(String nom) { this.nomArticle=nom; }

    public String getDescription() { return description; }
    public void setDescription(String desc) { this.description=desc; }

    public Double getPrixUnitaire() { return prixUnitaire; }
    public void setPrixUnitaire(Double prix) { this.prixUnitaire=prix; }

    public Integer getStockDispo() { return stockDispo; }
    public void setStockDispo(int stock) { this.stockDispo=stock; }
}
