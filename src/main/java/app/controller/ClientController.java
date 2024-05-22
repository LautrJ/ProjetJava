package app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import app.DatabaseConnection;

public class ClientController {
    public void fetchClients() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM clients";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Traitez les résultats ici
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
