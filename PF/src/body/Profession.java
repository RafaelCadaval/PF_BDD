package body;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Profession {
	
	public static void selectAll(Connection connection) throws SQLException {
		String sql = "select * " + 
					 "from profession";
		Statement sttmt = connection.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);

		while (rs.next()) {
			int idProfession = rs.getInt("idprofession");
			String name = rs.getString("name");
			int points = rs.getInt("points");
			String description = rs.getString("description");
			String category = rs.getString("category");
			System.out.println(idProfession + "\t" + name + "\t" + points + "\t" 
							   + description + "\t" + category);
		}
		sttmt.close();
		rs.close();
	}
	
	//próximos métodos
}
