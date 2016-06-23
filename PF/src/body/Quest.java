package body;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Quest {
	
	public static void selectAll(Connection connection) throws SQLException {
		String sql = "select * " + 
					 "from quest";
		Statement sttmt = connection.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);

		while (rs.next()) {
			int idQuest = rs.getInt("idquest");
			String name = rs.getString("name");
			String description = rs.getString("description");
			int reqLvl = rs.getInt("reqlevel");
			int lvlQuest = rs.getInt("levelquest");
			String gains = rs.getString("gains");
			String category = rs.getString("categorie");
			System.out.println(idQuest + "\t" + name + "\t" + description + "\t" 
							   + reqLvl + "\t" + lvlQuest + "\t" + gains + "\t" + category);
		}
		sttmt.close();
		rs.close();
	}
	
	//próximos métodos
}
