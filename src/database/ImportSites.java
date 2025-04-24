package database;

import com.opencsv.*;
import java.io.FileReader;
import java.sql.*;
import java.util.List;

public class ImportSites {
    public static void run(Connection connection) throws Exception {
        Statement st = connection.createStatement();
        st.executeUpdate("DROP TABLE IF EXISTS sites");
        st.executeUpdate("CREATE TABLE sites (id INT PRIMARY KEY, x INT, y INT)");

        CSVReader reader = new CSVReaderBuilder(new FileReader("fichiers/init-sites-500-Carre.csv"))
            .withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build();

        List<String[]> lignes = reader.readAll();
        for (int i = 1; i < lignes.size(); i++) {
            int id = Integer.parseInt(lignes.get(i)[0]);
            int x = Integer.parseInt(lignes.get(i)[1]);
            int y = Integer.parseInt(lignes.get(i)[2]);

            PreparedStatement pst = connection.prepareStatement("INSERT INTO sites VALUES (?, ?, ?)");
            pst.setInt(1, id);
            pst.setInt(2, x);
            pst.setInt(3, y);
            pst.executeUpdate();
        }

        System.out.println("=> Sites importés");
    }
}
