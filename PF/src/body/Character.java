package body;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Character {
	private static int idCharacter;
	private static String name;
	private static int charClass;
	
	public static void selectAll(Connection connection) throws SQLException {
		String sql = "select * " + 
					 "from character";
		Statement sttmt = connection.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);

		while (rs.next()) {
			idCharacter = rs.getInt("idcharacter");
			name = rs.getString("name");
			charClass = rs.getInt("class");
			System.out.println(idCharacter + "\t" + name + "\t" + charClass);
		}
		sttmt.close();
		rs.close();
	}
	
	//quantidade de personagens por classe
	//select class, count((class)) from character group by class;
	public static void charsByClass(Connection conn) throws SQLException {
		String sql = "SELECT class, count((class)) "
					 + "FROM character "
					 + "GROUP BY class ";
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		System.out.println("Classe\tQuantidade\n");
		int quant = 0;
		while(rs.next()) {
			charClass = rs.getInt("class");
			quant = rs.getInt("count((class))");
			System.out.println(charClass + "\t" + quant);
		}
		sttmt.close();
		rs.close();
	}
	
	//Nome dos personagens cujo sobrenome termina em "ker";
	//select name from character where name like '%ker';
	public static void nameWithEndingKER(Connection conn) throws SQLException {
		String sql = "SELECT name "
				 + "FROM character "
				 + "WHERE name like '%ker' ";
		
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
	
	/*Nome dos personagens que tem o item 690 em seu inventário.
	select cha.name, inv.iteminventory 
	from character cha inner join inventory inv on cha.IDCHARACTER = inv.character
	where inv.ITEMINVENTORY = 690;
	*/
	public static void nameWithItem690(Connection conn) throws SQLException{
		String sql = "SELECT cha.name, inv.iteminventory "
				 + "FROM character cha inner join inventory inv on cha.idcharacter = inv.character "
				 + "WHERE inv.iteminventory = 690 ";
		
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
	
	/*Lista de nomes de personagens que são Druídas e Magos, separados por classe e ordenados por nome.
	select cha.name, class.name
	from character cha
	inner join class
	on chaCLASS = class.IDCLASS
	where class.name = 'Druid' or class.NAME = 'Mage'
	order by class.name, cha.NAME;
	*/
	public static void nameDM_GBC_OBN(Connection conn) throws SQLException{
		String sql = "SELECT cha.name, cl.name "
				 + "FROM character cha inner join class cl on cha.class = cl.idclass "
				 + "WHERE cl.name = 'Druid' or cl.name = 'Mage'"
				 + "ORDER BY cl.name, cha.name ";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
	
		String c = null;
		System.out.println("Nome personagem\t\tClass\n");
		while(rs.next()) {
			name = rs.getString(1);
			c = rs.getString(2);
			System.out.println(name+"\t"+c);
		}
		sttmt.close();
		rs.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	/*todos os usuarios que tem mais de 2 quests
	 select character.name, sum(1) quests from character
	 inner join character_has_quest on character_has_quest.character_idcharacter = character.idcharacter
	 inner join quest on character_has_quest.quest_idquest = quest.idquest group by character.name
	 having sum(1) > 2
	*/
	
	//arrumar
//	public static void name2orMoreQuests(Connection conn) throws SQLException{
//		String sql = "SELECT cha.name, sum(1) quests "
//				 + "FROM inner join character_has_quest on character_has_quest.character_idcharacter = character.idcharacter "
//				 + "inner join quest on character_has_quest.quest_idquest = quest.idquest "
//				 + "GROUP BY character.name"
//				 + "HAVING sum(1) > 2 ";
//		
//		Statement sttmt = conn.createStatement();
//		ResultSet rs = sttmt.executeQuery(sql);
//	
//		int x = 0;
//		System.out.println("Nome\tQuests\n");
//		while(rs.next()) {
//			name = rs.getString(1);
//			x = rs.getInt(2);
//			System.out.println(name+"\t"+x);
//		}
//		sttmt.close();
//		rs.close();
//	}
	
}
