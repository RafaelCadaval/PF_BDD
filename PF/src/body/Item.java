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
	public static void consulta1(Connection conn) throws SQLException {
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
	public static void consuta2(Connection conn) throws SQLException {
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
	public static void consulta3(Connection conn) throws SQLException {
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
	public static void consulta4(Connection conn) throws SQLException {
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
	public static void consulta5(Connection conn) throws SQLException {
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
	public static void consulta6(Connection conn) throws SQLException {
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
	
	//Nome dos items (sem repetição) que tem tamanho menor que a média.
	//select distinct(name) from item where SIZEITEM < (select avg(sizeitem) from item);
	public static void consulta20(Connection conn) throws SQLException {
		String sql = "SELECT distinct(name) "
				 + "FROM item "
				 + "WHERE sizeitem < (select avg(sizeitem) from item)";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
	
		String n = null;
		System.out.println("Nome\n");
		while(rs.next()) {
			n = rs.getString(1);
			System.out.println(n);
		}
		sttmt.close();
		rs.close();
	}
	
	//pegar o tamanho dos itens do primeiro char	
	//select character.name, sum(item.sizeitem) from character
	//inner join inventory on inventory.character = character.idcharacter
	//inner join item on inventory.iteminventory = item.iditem
	//WHERE character.idcharacter = (select idcharacter from character where ROWNUM = 1) group by character.name;
	public static void consulta21(Connection conn) throws SQLException {
		String sql = "SELECT character.name, sum(item.sizeitem) " 
				 + "FROM character "
				 + "inner join inventory on inventory.character = character.idcharacter "
				 + "inner join item on inventory.iteminventory = item.iditem "
				 + "WHERE character.idcharacter = (select idcharacter from character where ROWNUM = 1) "
				 + "GROUP BY character.name ";
	
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);

		String n = null, t = null;
		System.out.println("Nome\tTamanho\n");
		while(rs.next()) {
			n = rs.getString(1);
			t = rs.getString(2);
			System.out.println(n+"\t"+t);
		}
		sttmt.close();
		rs.close();
	}
	
	//Lista de nomes de profissões que tem 'funcionários' com a letra y no nome
	//select profession.name from character_has_profession
	//inner join profession on character_has_profession.profession_idprofession = profession.idprofession
	//where character_has_profession.character_idcharacter in (select idcharacter from character where name like '%y%') group by profession.name
	public static void consulta22(Connection conn) throws SQLException {
		String sql = "SELECT profession.name " 
				 + "FROM character_has_profession "
				 + "inner join profession on character_has_profession.profession_idprofession = profession.idprofession "
				 + "WHERE character_has_profession.character_idcharacter in (select idcharacter from character where name like '%y%') "
				 + "GROUP BY profession.name ";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		String n = null;
		System.out.println("Profissão\n");
		while(rs.next()) {
			n = rs.getString(1);
			System.out.println(n);
		}
		sttmt.close();
		rs.close();
	}
	
	//Mostra itens que tem dps maiores que a media
	//select * from item where item.dps > (select avg(item.dps) from item)
	public static void consulta23(Connection conn) throws SQLException {
		String sql = "SELECT * " 
				 + "FROM item "
				 + "WHERE item.dps > (select avg(item.dps) from item) ";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		String id = null, n = null, r = null, l = null, sl = null,
			   so = null, t = null, dps = null, spd = null, sI = null;
		System.out.println("ID\tNome\tReqLevel\tLvlItem\tSlot"
							+ "\tOrigem\tTipo\tDPS\tVelocidade\tTamanho\n");
		while(rs.next()) {
			id = rs.getString(1);
			n = rs.getString(2);
			r = rs.getString(3);
			l = rs.getString(4);
			sl = rs.getString(5);
			so = rs.getString(6);
			t = rs.getString(7);
			dps = rs.getString(8);
			spd = rs.getString(9);
			sI = rs.getString(10);
			System.out.println(id+"\t"+n+"\t"+r+"\t"+l+"\t"+sl
								 +"\t"+so+"\t"+t+"\t"+dps+"\t"+spd+"\t"+sI);
		}
		sttmt.close();
		rs.close();
	}
	
}
