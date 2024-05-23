package app.model;

public class Client {
    private String login;
    private String password;
    private String nomClient;
    private String prenom;
    private String adresseDeLivraison;
    private String adresseDeFacturation;

    public Client(String login, String password, String nomClient, String prenom, String adresseDeLivraison, String adresseDeFacturation) {
        this.login = login;
        this.password = password;
        this.nomClient = nomClient;
        this.prenom = prenom;
        this.adresseDeLivraison = adresseDeLivraison;
        this.adresseDeFacturation = adresseDeFacturation;
    }


    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNomClient() { return nomClient; }
    public void setNomClient(String nomClient) { this.nomClient = nomClient; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getAdresseDeLivraison() { return adresseDeLivraison; }
    public void setAdresseDeLivraison(String adresseDeLivraison) { this.adresseDeLivraison = adresseDeLivraison; }

    public String getAdresseDeFacturation() { return adresseDeFacturation; }
    public void setAdresseDeFacturation(String adresseDeFacturation) { this.adresseDeFacturation = adresseDeFacturation; }
}
