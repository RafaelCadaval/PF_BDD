package body;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Item {
	private static int idItem;
	private static String name;
	private static int reqLvl;
	private static int lvlItem;
	private static String slot;
	private static String source;
	private static String type;
	private static int DPS;
	private static int speed;
	private static int sizeItem;
	
	public static void selectAll(Connection connection) throws SQLException {
		String sql = "select * " + 
					 "from item";
		Statement sttmt = connection.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);

		while (rs.next()) {
			idItem = rs.getInt("iditem");
			name = rs.getString("name");
			reqLvl = rs.getInt("reqlevel");
			lvlItem = rs.getInt("levelitem");
			slot = rs.getString("slot");
			source = rs.getString("source");
			type = rs.getString("type");
			DPS = rs.getInt("dps");
			speed = rs.getInt("speed");
			sizeItem = rs.getInt("sizeitem");
			System.out.println(idItem + "\t" + name + "\t" + reqLvl + "\t" 
							   + lvlItem + "\t" + slot + "\t" + source + "\t" 
							   + type + "\t" + DPS + "\t" + speed + "\t" + sizeItem);
		}
		sttmt.close();
		rs.close();
	}
	
	//Nome, tipo e velocidade de ataque dos items que tenham velocidade de ataque 2 ou 4
	//select name, type, speed from item where speed in (2, 4);
	public static void nTAS2OR4(Connection conn) throws SQLException {
		String sql = "SELECT name, type, speed "
				 + "FROM item "
				 + "WHERE speed in (2, 4) ";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		System.out.println("Nome\tTipo\tVelo Ataque\n");
		while(rs.next()) {
			name = rs.getString("name");
			type = rs.getString("type");
			speed = rs.getInt("speed");
			System.out.println(name + "\t" + type + "\t" + speed);
		}
		sttmt.close();
		rs.close();
	}
	
	//Nome dos itens e seu tipo ordenados por dps;
	//select name, type from item order by dps; 
	public static void itemNameOBDPS(Connection conn) throws SQLException {
		String sql = "SELECT name, type "
				 + "FROM item "
				 + "ORDER BY dps ";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		System.out.println("Nome\tTipo\n");
		while(rs.next()) {
			name = rs.getString("name");
			type = rs.getString("type");
			System.out.println(name + "\t" + type);
		}
		sttmt.close();
		rs.close();
	}
	
	//Média de DPS das armas (com duas casas decimais) agrupados por tipo.
	//select type, round(avg(dps), 2) from item group by type;
	public static void mWDPS_GBType(Connection conn) throws SQLException {
		String sql = "SELECT type, round(avg(dps),2) "
				 + "FROM item "
				 + "GROUP BY type ";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		double x = 0;
		System.out.println("Tipo\tMédia\n");
		while(rs.next()) {
			type = rs.getString(1);
			x = rs.getDouble(2);
			System.out.println(type+"\t"+x);
		}
		sttmt.close();
		rs.close();
	}
	
	//Nome e DPS mais alto das armas cujo DPS seja maior que 450.
	//select type, max(dps) from item group by type having max(dps) > 450;
	public static void nameAndDPSHT450(Connection conn) throws SQLException {
		String sql = "SELECT type, max(dps) "
				 + "FROM item "
				 + "GROUP BY type "
				 + "HAVING max(dps) > 450";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		int x = 0;
		System.out.println("Type\tDPS\n");
		while(rs.next()) {
			name = rs.getString(1);
			x = rs.getInt(2);
			System.out.println(name+"\t"+x);
		}
		sttmt.close();
		rs.close();
	}
	
	//Nome dos items que tem DPS maior que a média geral dos items.
	//select name from item where DPS > (select avg(dps) from item);
	public static void nameDPSHTm(Connection conn) throws SQLException {
		String sql = "SELECT name "
				 + "FROM item "
				 + "WHERE DPS > (select avg(dps) from item) ";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		System.out.println("Nome\n");
		while(rs.next()) {
			name = rs.getString("name");
			System.out.println(name);
		}
		sttmt.close();
		rs.close();
	}
	
	//As velocidades de ataque dos itens que tem o maior dps.
	//select speed from item where DPS = (select max(dps) from item) GROUP BY SPEED;
	public static void aSIWithHDPS(Connection conn) throws SQLException {
		String sql = "SELECT speed "
				 + "FROM item "
				 + "WHERE DPS = (select max(dps) from item) "
				 + "GROUP BY speed";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		System.out.println("Velo de Atq\n");
		while(rs.next()) {
			speed = rs.getInt("speed");
			System.out.println(speed);
		}
		sttmt.close();
		rs.close();
	}
	
	
	
	//pegar peso dos itens do primeiro char
	//arrumar essa consulta !!
	
//	public static void getWeightItems(){
//		String sql = "SELECT character.name, sum(1) quests from character " + 
//				 "FROM character inner join character_has_quest on character_has_quest.character_idcharacter = character.idcharacter " + 
//				 "inner join quest on character_has_quest.quest_idquest = quest.idquest " +
//				 "GROUP BY character.name " +
//				 "HAVING sum(1) > 2 = " + movieId;
//	
//		Statement statement = connection.createStatement();
//		ResultSet rs = statement.executeQuery(sql);
//
//		while (rs.next()) {
//			int id_filme = rs.getInt("id_filme");
//			String titulo = rs.getString("titulo");
//			int ano = rs.getInt("ano");
//			String diretor = rs.getString("diretor");
//			System.out.println(id_filme + "\t" + titulo + "\t" + ano + "\t" + diretor);
//		}
//		statement.close();
//		rs.close();
//	}	
	
}
