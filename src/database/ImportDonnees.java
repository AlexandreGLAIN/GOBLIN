package database;

import java.sql.*;

public class ImportDonnees {
    public static void main(String[] args) throws Exception {
        Class.forName("org.hsqldb.jdbcDriver");
        String url = "jdbc:hsqldb:file:database/goblin;shutdown=true";
        String login = "sa";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            ImportSites.run(connection);
            ImportClients.run(connection);
            ImportEntrepots.run(connection);
            ImportRoutes.run(connection);

            System.out.println("=> Importation de toutes les données terminée");
        }
    }
}
