package app.model;

import java.util.ArrayList;
import java.util.List;

public class ClientData {
    public static List<Client> getClients() {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(1, "john.doe", "password123", "Doe", "John", "123 Rue Principale, Paris", "123 Rue Principale, Paris"));
        clients.add(new Client(2, "jane.smith", "password456", "Smith", "Jane", "456 Rue Secondaire, Lyon", "789 Rue Tertiaire, Lyon"));
        clients.add(new Client(3, "mike.brown", "password789", "Brown", "Mike", "789 Rue Tertiaire, Lyon", "789 Rue Tertiaire, Lyon"));
        clients.add(new Client(4, "lucy.white", "password101", "White", "Lucy", "101 Rue Quaternaire, Marseille", "102 Rue Quaternaire, Marseille"));
        clients.add(new Client(5, "anna.jones", "password202", "Jones", "Anna", "202 Rue Quinquennale, Bordeaux", "203 Rue Quinquennale, Bordeaux"));
        return clients;
    }
}
