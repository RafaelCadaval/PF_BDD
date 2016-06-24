package body;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Quest {
	private static int idQuest;
	private static String name;
	private static String description;
	private static int reqLvl;
	private static int lvlQuest;
	private static String gains;
	private static String category;
	
	public static void selectAll(Connection connection) throws SQLException {
		String sql = "select * " + 
					 "from quest";
		Statement sttmt = connection.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);

		while (rs.next()) {
			idQuest = rs.getInt("idquest");
			name = rs.getString("name");
			description = rs.getString("description");
			reqLvl = rs.getInt("reqlevel");
			lvlQuest = rs.getInt("levelquest");
			gains = rs.getString("gains");
			category = rs.getString("category");
			System.out.println(idQuest + "\t" + name + "\t" + description + "\t" 
							   + reqLvl + "\t" + lvlQuest + "\t" + gains + "\t" + category);
		}
		sttmt.close();
		rs.close();
	}
	
	//próximos métodos
}
