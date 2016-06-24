package body;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Inventory {
	private static int sizeInventory;
	private static int itemInventory;
	private static int character;
	
	public static void selectAll(Connection connection) throws SQLException {
		String sql = "select * " + 
					 "from profession";
		Statement sttmt = connection.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);

		while (rs.next()) {
			sizeInventory = rs.getInt("sizeinventory");
			itemInventory = rs.getInt("iteminventory");
			character = rs.getInt("character");
			System.out.println(sizeInventory + "\t" + itemInventory + "\t" + character);
		}
		sttmt.close();
		rs.close();
	}
	
	//próximos métodos
}
