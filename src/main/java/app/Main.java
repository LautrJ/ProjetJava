package app;

import app.controller.ArticleController;
import app.controller.ClientController;
import app.view.MainView;
import app.controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MainView mainView = new MainView();
        MainController mainController = new MainController(mainView, primaryStage);
        ClientController clientController = new ClientController(primaryStage);
        ArticleController articleController = new ArticleController(primaryStage);

        Scene scene = new Scene(mainView.getRoot(),854,480);
        primaryStage.setTitle("Application de Gestion");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
