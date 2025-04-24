package database;

import com.opencsv.*;
import java.io.FileReader;
import java.sql.*;
import java.util.List;

public class ImportEntrepots {
    public static void run(Connection connection) throws Exception {
        Statement st = connection.createStatement();
        st.executeUpdate("DROP TABLE IF EXISTS entrepots");
        st.executeUpdate("CREATE TABLE entrepots (id INT PRIMARY KEY, capacite INT, cout INT, site INT)");

        CSVReader reader = new CSVReaderBuilder(new FileReader("fichiers/init-entrepots-500-100-Carre.csv"))
            .withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build();

        List<String[]> lignes = reader.readAll();
        for (int i = 1; i < lignes.size(); i++) {
            int id = Integer.parseInt(lignes.get(i)[0]);
            int capacite = Integer.parseInt(lignes.get(i)[1]);
            int cout = Integer.parseInt(lignes.get(i)[2]);
            int site = Integer.parseInt(lignes.get(i)[3]);

            PreparedStatement pst = connection.prepareStatement("INSERT INTO entrepots VALUES (?, ?, ?, ?)");
            pst.setInt(1, id);
            pst.setInt(2, capacite);
            pst.setInt(3, cout);
            pst.setInt(4, site);
            pst.executeUpdate();
        }

        System.out.println("=> Entrepôts importés");
    }
}
