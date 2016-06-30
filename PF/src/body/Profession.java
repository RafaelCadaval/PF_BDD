package body;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Profession {
	private static int idProfession;
	private static String name;
	private static int points;
	private static String description;
	private static String category;
	
	public static void selectAll(Connection connection) throws SQLException {
		String sql = "select * " + 
					 "from profession";
		Statement sttmt = connection.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);

		while (rs.next()) {
			idProfession = rs.getInt("idprofession");
			name = rs.getString("name");
			points = rs.getInt("points");
			description = rs.getString("description");
			category = rs.getString("category");
			System.out.println(idProfession + "\t" + name + "\t" + points + "\t" 
							   + description + "\t" + category);
		}
		sttmt.close();
		rs.close();
	}
	
	
}
