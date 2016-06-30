package body;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Zone {
	private static int idZone;
	private static String nameZone;
	private static String lvlZone;
	private static String type;
	private static int NPC;
	
	public static void selectAll(Connection connection) throws SQLException {
		String sql = "select * " + 
					 "from zone";
		Statement sttmt = connection.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);

		while (rs.next()) {
			idZone = rs.getInt("idzone");
			nameZone = rs.getString("namezone");
			lvlZone = rs.getString("levelzone");
			type = rs.getString("type");
			NPC = rs.getInt("npc");
			System.out.println(idZone + "\t" + nameZone + "\t" + lvlZone + "\t" 
							   + type + "\t" + NPC);
		}
		sttmt.close();
		rs.close();
	}
	
	//próximos métodos
	
	//tipos de zonas de jogo
	//select distinct(type) from zone;
	public static void consulta12(Connection connection) throws SQLException {
		String sql = "SELECT distinct(type) " + 
				 	 "FROM zone ";
	
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		
		System.out.println("ZoneType\n");
		
		while (rs.next()) {
			type = rs.getString("type");
			System.out.println(type);
		}
	
		statement.close();
		rs.close();
	}
	
	
	
	
}
