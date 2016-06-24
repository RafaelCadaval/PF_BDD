package body;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NPC {
	private static int idNPC;
	private static String name;
	private static String type;
	private static int lvlNPC;
	
	public static void selectAll(Connection connection) throws SQLException {
		String sql = "select * " + 
					 "from npc";
		Statement sttmt = connection.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);

		while (rs.next()) {
			idNPC = rs.getInt("idNPC");
			name = rs.getString("name");
			type = rs.getString("type");
			lvlNPC = rs.getInt("levelNPC");
			System.out.println(idNPC + "\t" + name + "\t" + type + "\t" + lvlNPC);
		}
		sttmt.close();
		rs.close();
	}
	
	//próximos métodos
}
