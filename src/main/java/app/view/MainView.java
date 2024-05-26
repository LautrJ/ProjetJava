package app.view;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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

        Label asciiLabel = new Label(
                "  III   L      DDDD   EEEEE   III  L       CCCC\n" +
                   "   I    L      D   D  E        I   L      C\n" +
                   "   I    L      D   D  EEE      I   L      C\n" +
                   "   I    L      D   D  E        I   L      C\n" +
                   "  III   LLLLL  DDDD   EEEEE   III  LLLLL   CCCC"
        );


        asciiLabel.setFont(Font.font("Consolas", FontWeight.BOLD, 20));

        VBox logoBox = new VBox();
        logoBox.setAlignment(Pos.CENTER);
        logoBox.getChildren().add(asciiLabel);

        root.setTop(menuBar);
        root.setCenter(logoBox);
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
