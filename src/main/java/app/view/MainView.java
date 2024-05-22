package app.view;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class MainView {
    private BorderPane root;
    private MenuBar menuBar;
    private MenuItem menuClient;
    private MenuItem menuCommande;
    private MenuItem menuStock;

    public MainView(){
        root = new BorderPane();
        setupLayout();
    }

    private void setupLayout() {
        menuBar = new MenuBar();
        Menu navigation = new Menu("Menu");
        menuClient = new MenuItem("Gestion clients");
        menuCommande = new MenuItem("Gestion commandes");
        menuStock = new MenuItem("Gestion stock");

        navigation.getItems().addAll(menuClient, menuCommande, menuStock);
        menuBar.getMenus().add(navigation);

        root.setTop(menuBar);
    }

    public BorderPane getRoot(){
        return root;
    }

    public MenuItem getMenuClient(){
        return menuClient;
    }

    public MenuItem getMenuCommande() {
        return menuCommande;
    }

    public MenuItem getMenuStock() {
        return menuStock;
    }
}
