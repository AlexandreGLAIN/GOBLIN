package database;

import com.opencsv.*;
import java.io.FileReader;
import java.sql.*;
import java.util.List;

public class ImportRoutes {
	public static void run(Connection connection) throws Exception {
		Statement st = connection.createStatement();
		st.executeUpdate("DROP TABLE IF EXISTS routes");
		st.executeUpdate("CREATE TABLE routes (site1 INT, site2 INT)");

		CSVReader reader = new CSVReaderBuilder(new FileReader("fichiers/init-routes-500-750-Carre.csv"))
				.withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build();

		List<String[]> lignes = reader.readAll();
		for (int i = 1; i < lignes.size(); i++) {
			int site1 = Integer.parseInt(lignes.get(i)[0]);
			int site2 = Integer.parseInt(lignes.get(i)[1]);


			PreparedStatement pst = connection.prepareStatement("INSERT INTO routes VALUES (?, ?)");
			pst.setInt(1, site1);
			pst.setInt(2, site2);
			pst.executeUpdate();
		}

		System.out.println("=> Routes importées");
	}
}
