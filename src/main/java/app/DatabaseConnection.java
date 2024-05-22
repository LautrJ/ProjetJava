package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/nom_de_votre_base_de_donnees";
    private static final String USERNAME = "votre_nom_utilisateur";
    private static final String PASSWORD = "votre_mot_de_passe";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}