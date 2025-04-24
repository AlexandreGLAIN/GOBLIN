package database;

import com.opencsv.*;
import java.io.FileReader;
import java.sql.*;
import java.util.List;

public class ImportClients {
    public static void run(Connection connection) throws Exception {
        Statement st = connection.createStatement();
        st.executeUpdate("DROP TABLE IF EXISTS clients");
        st.executeUpdate("CREATE TABLE clients (mail VARCHAR(100) PRIMARY KEY, site INT)");

        CSVReader reader = new CSVReaderBuilder(new FileReader("fichiers/init-clients-500-200-Carre.csv"))
            .withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build();

        List<String[]> lignes = reader.readAll();
        for (int i = 1; i < lignes.size(); i++) {
        	String mail = lignes.get(i)[1];                      
        	int site = Integer.parseInt(lignes.get(i)[2]);       


            PreparedStatement pst = connection.prepareStatement("INSERT INTO clients VALUES (?, ?)");
            pst.setString(1, mail);
            pst.setInt(2, site);
            pst.executeUpdate();
        }

        System.out.println("=> Clients importés");
    }
}