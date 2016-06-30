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
	//select class.name, count(1) from character 
	//inner join class on class.idclass = character.class group by class.name
	public static void consulta7(Connection conn) throws SQLException {
		String sql = "SELECT class.name, count(1) "
					 + "FROM character "
					 + "inner join class on class.idclass = character.class "
					 + "GROUP BY class.name ";
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		System.out.println("Classe\tQuantidade\n");
		String c = null, q = null;
		while(rs.next()) {
			c = rs.getString(1);
			q = rs.getString(2);
			System.out.println(c+"\t"+q);
		}
		sttmt.close();
		rs.close();
	}
	
	//Nome dos personagens cujo sobrenome termina em "ker";
	//select name from character where name like '%ker';
	public static void consulta8(Connection conn) throws SQLException {
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
	public static void consulta9(Connection conn) throws SQLException{
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
	public static void consulta10(Connection conn) throws SQLException{
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
	
	
	/*
	pegar peso dos itens do primeiro char
	select character.name, sum(item.sizeitem) from character
	inner join inventory on inventory.character = character.idcharacter
	inner join item on inventory.iteminventory = item.iditem
	WHERE character.idcharacter = (select idcharacter from character where ROWNUM = 1) group by character.name
	 */
	public static void consulta11(Connection conn) throws SQLException {
		String sql = "SELECT character.name, sum(item.sizeitem) "
				 + "FROM character inner join inventory on inventory.character = character.idcharacter "
				 + "inner join item on inventory.iteminventory = item.iditem "
				 + "WHERE character.idcharacter = (select idcharacter from character where ROWNUM = 1) "
				 + "GROUP BY character.name ";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		int x = 0;
		System.out.println("Nome Personagem\tTamanho inventário\n");
		while(rs.next()) {
			name = rs.getString(1);
			x = rs.getInt(2);
			System.out.println(name+"\t"+x);
		}
		sttmt.close();
		rs.close();
	}
	
	//Quais personagens foram criados a menos de 2 anos e 270 dias atrás.
	//SELECT * FROM CHARACTER WHERE CREATIONDATE > SYSDATE - 1000;
	
	
	//arrumar o print, limpar a string de date!!!!!!!!!!!!!!
	
	public static void consulta13(Connection conn) throws SQLException {
		String sql = "SELECT * "
				 + "FROM character "
				 + "WHERE creationdate > sysdate - 1000 ";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		String id = null, n = null, c = null, dC = null;
		System.out.println("ID\tNome\tClasse\tData Criação\n");
		while(rs.next()) {
			id = rs.getString(1);
			n = rs.getString(2);
			c = rs.getString(3);
			dC = rs.getString(4);
			System.out.println(id+"\t"+n+"\t"+c+"\t"+dC);
		}
		sttmt.close();
		rs.close();
	}
	
	//Nome dos personagens e seu respectivo ano de criação.
	//SELECT NAME, EXTRACT(YEAR FROM CREATIONDATE) AS "ANO DE CRIAÇÃO" FROM CHARACTER;
	public static void consulta14(Connection conn) throws SQLException {
		String sql = "SELECT name, extract(year from creationdate) as \"ano de criação\""
				 + "FROM character ";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		String s = null;
		System.out.println("Nome\tData Criação\n");
		while(rs.next()) {
			name = rs.getString(1);
			s = rs.getString(2);
			System.out.println(name+"\t"+s);
		}
		sttmt.close();
		rs.close();
	}
	
	//Nome dos personagens, sua classe e sua profissão.
	//select cha.name, class.name, prf.name from character cha
	//inner join class on class.IDCLASS = cha.class
	//inner join character_has_profession hasp on hasp.character_idcharacter = cha.idcharacter
	//inner join profession prf on prf. idprofession = hasp.profession_idprofession
	public static void consulta15(Connection conn) throws SQLException {
		String sql = "SELECT cha.name, class.name, prf.name "
				 + "FROM character cha "
				 + "inner join class on class.IDCLASS = cha.class "
				 + "inner join character_has_profession hasp on hasp.character_idcharacter = cha.idcharacter "
				 + "inner join profession prf on prf. idprofession = hasp.profession_idprofession";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		String n = null, s = null, p = null;
		System.out.println("Nome\tClasse\tProfissão\n");
		while(rs.next()) {
			n = rs.getString(1);
			s = rs.getString(2);
			p = rs.getString(3);
			System.out.println(n+"\t"+s+"\t"+p);
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
	public static void consulta16(Connection conn) throws SQLException{
		String sql = "SELECT character.name, sum(1) quests "
				 + "FROM character "
				 + "inner join character_has_quest on character_has_quest.character_idcharacter = character.idcharacter "
				 + "inner join quest on character_has_quest.quest_idquest = quest.idquest "
				 + "GROUP BY character.name "
				 + "HAVING sum(1) > 2";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		String n = null, q = null;
		System.out.println("Nome\tNº Quests\n");
		while(rs.next()) {
			n = rs.getString(1);
			q = rs.getString(2);
			System.out.println(n+"\t"+q);
		}
		sttmt.close();
		rs.close();
	}
	
	//Categorias de profissões e a quantidade de personagens que estão em cada categoria
	//select profession.category, count(1) chars from profession
	//inner join character_has_profession on character_has_profession.profession_idprofession = profession.idprofession
	//inner join character on character_has_profession.character_idcharacter = character.idcharacter group by profession.category
	public static void consulta17(Connection conn) throws SQLException {
		String sql = "SELECT profession.category, count(1) chars "
				 + "FROM profession "
				 + "inner join character_has_profession on character_has_profession.profession_idprofession = profession.idprofession "
				 + "inner join character on character_has_profession.character_idcharacter = character.idcharacter "
				 + "GROUP BY profession.category ";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		String n = null, s = null;
		System.out.println("Categoria\tQuant\n");
		while(rs.next()) {
			n = rs.getString(1);
			s = rs.getString(2);
			System.out.println(n+"\t"+s);
		}
		sttmt.close();
		rs.close();
	}
	
	//Media de dps dos personagens
	//select character.name, avg(item.dps) dps from character
	//inner join inventory on inventory.character = character.idcharacter
	//inner join item on item.iditem = inventory.iteminventory group by character.name;
	public static void consulta18(Connection conn) throws SQLException {
		String sql = "SELECT character.name, avg(item.dps) dps "
				 + "FROM character "
				 + "inner join inventory on inventory.character = character.idcharacter "
				 + "inner join item on item.iditem = inventory.iteminventory "
				 + "GROUP BY character.name ";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		String n = null, s = null;
		System.out.println("Nome\tDPS\n");
		while(rs.next()) {
			n = rs.getString(1);
			s = rs.getString(2);
			System.out.println(n+"\t"+s);
		}
		sttmt.close();
		rs.close();
	}
	
	//Personagens com menor dano
	//select character.name, item.dps from character
	//inner join inventory on inventory.character = character.idcharacter
	//inner join item on item.iditem = inventory.iteminventory
	//where item.dps = (select MIN(item.DPS) from item) group by character.name, item.dps;
	public static void consulta19(Connection conn) throws SQLException {
		String sql = "SELECT character.name, item.dps "
				 + "FROM character "
				 + "inner join inventory on inventory.character = character.idcharacter "
				 + "inner join item on item.iditem = inventory.iteminventory "
				 + "WHERE item.dps = (select MIN(item.DPS) from item) group by character.name, item.dps ";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		String n = null, s = null;
		System.out.println("Nome\tDPS\n");
		while(rs.next()) {
			n = rs.getString(1);
			s = rs.getString(2);
			System.out.println(n+"\t"+s);
		}
		sttmt.close();
		rs.close();
	}
	
	
}
