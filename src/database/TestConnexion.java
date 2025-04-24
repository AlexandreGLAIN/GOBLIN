package database;
import java.io.File;
import java.sql.*;

public class TestConnexion {
    public static void main(String[] args) throws Exception {
        // Chargement du driver
        Class.forName("org.hsqldb.jdbcDriver");

        // URL de la base de données
        String url = "jdbc:hsqldb:file:database" + File.separator + "goblin;shutdown=true";
        String login = "sa";
        String password = "";

        // Connexion à la base
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            System.out.println("Connexion réussie !");
        }
    }
}
