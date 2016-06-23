package body;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Item {
	
	public static void selectAll(Connection connection) throws SQLException {
		String sql = "select * " + 
					 "from items";
		Statement sttmt = connection.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);

		while (rs.next()) {
			int idCharacter = rs.getInt("idcharacter");
			String name = rs.getString("name");
			String guild = rs.getString("guild");
			int quest = rs.getInt("quest");
			int inventory = rs.getInt("inventory");
			System.out.println(idCharacter + "\t" + name + "\t" + guild + "\t" 
							   + quest + "\t" + inventory);
		}
		sttmt.close();
		rs.close();
	}
	
	//próximos métodos
}
