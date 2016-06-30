package body;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DBOps {

	private class Database {
		private static String mJdbcUrl = "jdbc:oracle:thin:@camburi.pucrs.br:1521:facin11g";
		private static String mUser = "be180123";
		private static String mPassword = "be180123";
		private static OracleDataSource mDataSource = null;
		
		public static Connection getConnection() throws SQLException {
			if (mDataSource == null) {
				initialize();
			}
			return mDataSource.getConnection(mUser, mPassword);
		}

		public static void initialize() throws SQLException {
			mDataSource = new OracleDataSource();
			mDataSource.setURL(mJdbcUrl);
		}
		
	}
	
	public static void start(Connection conn) throws SQLException {
		loadDB(conn);
		menu(conn);
	}
	
	public static void menu(Connection conn){
		Scanner in = new Scanner(System.in);
		System.out.println("\fDigite os números indicados para navegar no menu\n1 - Lista de consultas");
		System.out.println("2 - Inserir, deletar ou realizar update no banco de dados");
		System.out.println("0 - Sair");
		int op = -1;
		while(op!=0){
			op = in.nextInt();
			switch(op){
			case 1: menuConsultas(conn,in,op);
				break;
			case 2: command();
				break;
			case 0: return;
			default: System.out.println("Opção não definida, tentar novamente");
			}
		}
	}
	
	private static void menuConsultas(Connection conn, Scanner in, int op) throws SQLException {
		
		System.out.println("\fConsulta 1: Tipos de zonas de jogo");
		System.out.println("Consulta 2: Quantidade de personagens por classe");
		System.out.println("Consulta 3: Nome, tipo e velocidade de ataque dos items que tenham velocidade de ataque 2 ou 4");
		System.out.println("Consulta 4: Nome dos personagens cujo sobrenome termina em \"ker\"");
		System.out.println("Consulta 5: Nome dos itens e seu tipo ordenados por dps");
		System.out.println("Consulta 6: Quais personagens foram criados a menos de 2 anos e 270 dias atrás");
		System.out.println("Consulta 7: Nome dos personagens e seu respectivo ano de criação");
		System.out.println("Consulta 8: Nome dos personagens que tem o item 690 em seu inventário");
		System.out.println("Consulta 9: Lista de nomes de personagens que são Druídas e Magos, separados por classe e ordenados por nome");
		System.out.println("Consulta 10: Nome dos personagens, sua classe e sua profissão");
		System.out.println("Consulta 11: Consultar peso dos itens do primeiro registro de personagem");
		System.out.println("Consulta 12: Todos os usuarios que tem mais de 2 quests");
		System.out.println("Consulta 13: Categorias de profissões e a quantidade de personagens que estão em cada categoria");
		System.out.println("Consulta 14: Média de DPS das armas (com duas casas decimais) agrupados por tipo");
		System.out.println("Consulta 15: Nome e DPS mais alto das armas cujo DPS seja maior que 450");
		System.out.println("Consulta 16: Média de DPS dos personagens");
		System.out.println("Consulta 17: Personagens com menor dano");
		System.out.println("Consulta 18: Nome dos itens (sem repetição) que tem tamanho menor que a média");
		System.out.println("Consulta 19: Nome dos itens que tem DPS maior que a média geral dos items");
		System.out.println("Consulta 20: As velocidades de ataque dos itens que tem o maior DPS");
		System.out.println("Consulta 21: Lista de nomes de profissões que tem 'funcionários' com a letra y no nome");
		System.out.println("Consulta 22: Mostra itens que tem DPS maiores que a média geral");
		while(op!=0){	
			op = in.nextInt();
			switch(op){
			case 1: 
				System.out.println("\f");
				consulta1(conn);
				if(!next(in)) return;
				break;
			case 2: 
				System.out.println("\f");
				consulta2(conn);
					if(!next(in)) return;
				break;
			case 3: 
				System.out.println("\f");
				consulta3(conn);
					if(!next(in)) return;
				break;
			case 4: 
				System.out.println("\f");
				consulta4(conn);
					if(!next(in)) return;
				break;
			case 5: 
				System.out.println("\f");
				consulta5(conn);
					if(!next(in)) return;
				break;
			case 6: 
				System.out.println("\f");
				consulta6(conn);
					if(!next(in)) return;
				break;
			case 7: 
				System.out.println("\f");
				consulta7(conn);
					if(!next(in)) return;
				break;
			case 8: 
				System.out.println("\f");
				consulta8(conn);
					if(!next(in)) return;
				break;
			case 9: 
				System.out.println("\f");
				consulta9(conn);
					if(!next(in)) return;
				break;
			case 10: 
				System.out.println("\f");
				consulta10(conn);
					 if(!next(in)) return;
				break;
			case 11: 
				System.out.println("\f");
				consulta11(conn);
					 if(!next(in)) return;
				break;
			case 12: 
				System.out.println("\f");
				consulta12(conn);
					 if(!next(in)) return;
				break;
			case 13: 
				System.out.println("\f");
				consulta13(conn);
					 if(!next(in)) return;
				break;
			case 14: 
				System.out.println("\f");
				consulta14(conn);
					 if(!next(in)) return;
				break;
			case 15: 
				System.out.println("\f");
				consulta15(conn);
					 if(!next(in)) return;
				break;
			case 16: 
				System.out.println("\f");
				consulta16(conn);
					 if(!next(in)) return;
				break;
			case 17: 
				System.out.println("\f");
				consulta17(conn);
					 if(!next(in)) return;
				break;
			case 18: 
				System.out.println("\f");
				consulta18(conn);
					 if(!next(in)) return;
				break;
			case 19: 
				System.out.println("\f");
				consulta19(conn);
					 if(!next(in)) return;
				break;
			case 20: 
				System.out.println("\f");
				consulta20(conn);
					 if(!next(in)) return;
				break;
			case 21: 
				System.out.println("\f");
				consulta21(conn);
					 if(!next(in)) return;
				break;
			case 22: 
				System.out.println("\f");
				consulta22(conn);
					 if(!next(in)) return;
				break;
			case 0: return;
			default: System.out.println("Opção não definida, tente novamente");
			}
		}
	}
	
	private static boolean next(Scanner in){
		System.out.println("\n\n1 - Voltar à listagem de consultas\n0 - Encerrar programa");
		if(in.nextInt()==0) return false;
		return true;
	}
	
	private static void loadDB(Connection conn) throws IOException, SQLException {
		Path path = Paths.get("tabelas.sql");
		
		try(Scanner in = new Scanner(Files.newBufferedReader(path, Charset.forName("utf8")))){
			String s = null;
			Statement sttmt = conn.createStatement();
			
			while(in.hasNextLine()){
				s = in.nextLine();
				sttmt.executeQuery(s);
			}
			sttmt.close();
		}
		catch(IOException e){
			System.err.format("Erro de E/S: %s%n", e);
		}
	}
	
	public static boolean command(Connection conn, String s) throws SQLException{
		Statement sttmt = conn.createStatement();
		int rowCount = sttmt.executeUpdate(s);
		return rowCount == 1;
	}
	
	// tipos de zonas de jogo
	// select distinct(type) from zone;
	public static void consulta1(Connection conn) throws SQLException {
		String sql = "SELECT distinct(type) " + "FROM zone ";

		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);

		String t = null;
		System.out.println("Tipo de zona\n");
		while (rs.next()) {
			t = rs.getString(1);
			System.out.println(t);
		}

		sttmt.close();
		rs.close();
	}

	// quantidade de personagens por classe
	// select class.name, count(1) from character
	// inner join class on class.idclass = character.class group by class.name
	public static void consulta2(Connection conn) throws SQLException {
		String sql = "SELECT class.name, count(1) " + "FROM character "
				+ "inner join class on class.idclass = character.class " + "GROUP BY class.name ";
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);

		System.out.println("Classe\tQuantidade\n");
		String c = null, q = null;
		while (rs.next()) {
			c = rs.getString(1);
			q = rs.getString(2);
			System.out.println(c + "\t" + q);
		}
		sttmt.close();
		rs.close();
	}

	// Nome, tipo e velocidade de ataque dos items que tenham velocidade de
	// ataque 2 ou 4
	// select name, type, speed from item where speed in (2, 4);
	public static void consulta3(Connection conn) throws SQLException {
		String sql = "SELECT name, type, speed " + "FROM item " + "WHERE speed in (2, 4) ";

		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);

		String n = null, t = null, s = null;
		System.out.println("Nome\tTipo\tVelo Ataque\n");
		while (rs.next()) {
			n = rs.getString(1);
			t = rs.getString(2);
			s = rs.getString(3);
			System.out.println(n + "\t" + t + "\t" + s);
		}
		sttmt.close();
		rs.close();
	}

	// Nome dos personagens cujo sobrenome termina em "ker";
	// select name from character where name like '%ker';
	public static void consulta4(Connection conn) throws SQLException {
		String sql = "SELECT name " + "FROM character " + "WHERE name like '%ker' ";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		String n = null;
		System.out.println("Nome\n");
		while (rs.next()) {
			n = rs.getString(1);
			System.out.println(n);
		}
		sttmt.close();
		rs.close();
	}
	
	// Nome dos itens e seu tipo ordenados por dps;
	// select name, type from item order by dps;
	public static void consulta5(Connection conn) throws SQLException {
		String sql = "SELECT name, type " + "FROM item " + "ORDER BY dps ";

		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);

		String n = null, t = null;
		System.out.println("Nome\tTipo\n");
		while (rs.next()) {
			n = rs.getString(1);
			t = rs.getString(2);
			System.out.println(n + "\t" + t);
		}
		sttmt.close();
		rs.close();
	}

	// Quais personagens foram criados a menos de 2 anos e 270 dias atrás.
	// SELECT * FROM CHARACTER WHERE CREATIONDATE > SYSDATE - 1000;
	
	// arrumar o print, limpar a string de date!!!!!!!!!!!!!!
	
	public static void consulta6(Connection conn) throws SQLException {
		String sql = "SELECT * " + "FROM character " + "WHERE creationdate > sysdate - 1000 ";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		String id = null, n = null, c = null, dC = null;
		System.out.println("ID\tNome\tClasse\tData Criação\n");
		while (rs.next()) {
			id = rs.getString(1);
			n = rs.getString(2);
			c = rs.getString(3);
			dC = rs.getString(4);
			System.out.println(id + "\t" + n + "\t" + c + "\t" + dC);
		}
		sttmt.close();
		rs.close();
	}
	
	// Nome dos personagens e seu respectivo ano de criação.
	// SELECT NAME, EXTRACT(YEAR FROM CREATIONDATE) AS "ANO DE CRIAÇÃO" FROM
	// CHARACTER;
	public static void consulta7(Connection conn) throws SQLException {
		String sql = "SELECT name, extract(year from creationdate) as \"ano de criação\"" + "FROM character ";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		String n = null, s = null;
		System.out.println("Nome\tData Criação\n");
		while (rs.next()) {
			n = rs.getString(1);
			s = rs.getString(2);
			System.out.println(n + "\t" + s);
		}
		sttmt.close();
		rs.close();
	}
	
	/*
	 * Nome dos personagens que tem o item 690 em seu inventário. 
	 * select cha.name, inv.iteminventory from character cha inner join inventory inv
	 * on cha.IDCHARACTER = inv.character where inv.ITEMINVENTORY = 690;
	 */
	public static void consulta8(Connection conn) throws SQLException {
		String sql = "SELECT cha.name, inv.iteminventory "
				+ "FROM character cha inner join inventory inv on cha.idcharacter = inv.character "
				+ "WHERE inv.iteminventory = 690 ";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		String n = null;
		System.out.println("Nome\n");
		while (rs.next()) {
			n = rs.getString(1);
			System.out.println(n);
		}
		sttmt.close();
		rs.close();
	}
	
	/*
	 * Lista de nomes de personagens que são Druídas e Magos, separados por
	 * classe e ordenados por nome. 
	 * select cha.name, class.name from character cha 
	 * inner join class on chaCLASS = class.IDCLASS 
	 * where class.name = 'Druid' or class.NAME = 'Mage' 
	 * order by class.name, cha.NAME;
	 */
	public static void consulta9(Connection conn) throws SQLException {
		String sql = "SELECT cha.name, cl.name " + "FROM character cha inner join class cl on cha.class = cl.idclass "
				+ "WHERE cl.name = 'Druid' or cl.name = 'Mage'" + "ORDER BY cl.name, cha.name ";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		String n = null, c = null;
		System.out.println("Nome personagem\t\tClass\n");
		while (rs.next()) {
			n = rs.getString(1);
			c = rs.getString(2);
			System.out.println(n + "\t" + c);
		}
		sttmt.close();
		rs.close();
	}
	
	// Nome dos personagens, sua classe e sua profissão.
	// select cha.name, class.name, prf.name from character cha
	// inner join class on class.IDCLASS = cha.class
	// inner join character_has_profession hasp on hasp.character_idcharacter =
	// cha.idcharacter
	// inner join profession prf on prf. idprofession =
	// hasp.profession_idprofession
	public static void consulta10(Connection conn) throws SQLException {
		String sql = "SELECT cha.name, class.name, prf.name " + "FROM character cha "
				+ "inner join class on class.IDCLASS = cha.class "
				+ "inner join character_has_profession hasp on hasp.character_idcharacter = cha.idcharacter "
				+ "inner join profession prf on prf. idprofession = hasp.profession_idprofession";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		String n = null, s = null, p = null;
		System.out.println("Nome\tClasse\tProfissão\n");
		while (rs.next()) {
			n = rs.getString(1);
			s = rs.getString(2);
			p = rs.getString(3);
			System.out.println(n + "\t" + s + "\t" + p);
		}
		sttmt.close();
		rs.close();
	}
	
	/*
	 * pegar peso dos itens do primeiro char 
	 * select character.name, sum(item.sizeitem) 
	 * from character 
	 * inner join inventory on inventory.character = character.idcharacter 
	 * inner join item on inventory.iteminventory = item.iditem 
	 * WHERE character.idcharacter = (select idcharacter from character where ROWNUM = 1) 
	 * group by character.name
	 */
	public static void consulta11(Connection conn) throws SQLException {
		String sql = "SELECT character.name, sum(item.sizeitem) "
				+ "FROM character "
				+ "inner join inventory on inventory.character = character.idcharacter "
				+ "inner join item on inventory.iteminventory = item.iditem "
				+ "WHERE character.idcharacter = (select idcharacter from character where ROWNUM = 1) "
				+ "GROUP BY character.name ";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		String n = null, t = null;
		System.out.println("Nome Personagem\tTamanho inventário\n");
		while (rs.next()) {
			n = rs.getString(1);
			t = rs.getString(2);
			System.out.println(n + "\t" + t);
		}
		sttmt.close();
		rs.close();
	}
	
	/*
	 * todos os usuarios que tem mais de 2 quests 
	 * select character.name, sum(1) quests 
	 * from character inner join character_has_quest on
	 * character_has_quest.character_idcharacter = character.idcharacter 
	 * inner join quest on character_has_quest.quest_idquest = quest.idquest group by
	 * character.name having sum(1) > 2
	 */
	public static void consulta12(Connection conn) throws SQLException {
		String sql = "SELECT character.name, sum(1) quests " + "FROM character "
				+ "inner join character_has_quest on character_has_quest.character_idcharacter = character.idcharacter "
				+ "inner join quest on character_has_quest.quest_idquest = quest.idquest " + "GROUP BY character.name "
				+ "HAVING sum(1) > 2";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		String n = null, q = null;
		System.out.println("Nome\tNº Quests\n");
		while (rs.next()) {
			n = rs.getString(1);
			q = rs.getString(2);
			System.out.println(n + "\t" + q);
		}
		sttmt.close();
		rs.close();
	}
	
	// Categorias de profissões e a quantidade de personagens que estão em cada categoria
	// select profession.category, count(1) chars from profession
	// inner join character_has_profession on
	// character_has_profession.profession_idprofession =
	// profession.idprofession
	// inner join character on character_has_profession.character_idcharacter =
	// character.idcharacter group by profession.category
	public static void consulta13(Connection conn) throws SQLException {
		String sql = "SELECT profession.category, count(1) chars " + "FROM profession "
				+ "inner join character_has_profession on character_has_profession.profession_idprofession = profession.idprofession "
				+ "inner join character on character_has_profession.character_idcharacter = character.idcharacter "
				+ "GROUP BY profession.category ";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		String n = null, s = null;
		System.out.println("Categoria\tQuant\n");
		while (rs.next()) {
			n = rs.getString(1);
			s = rs.getString(2);
			System.out.println(n + "\t" + s);
		}
		sttmt.close();
		rs.close();
	}
	
	// Média de DPS das armas (com duas casas decimais) agrupados por tipo.
	// select type, round(avg(dps), 2) from item group by type;
	public static void consulta14(Connection conn) throws SQLException {
		String sql = "SELECT type, round(avg(dps),2) " + "FROM item " + "GROUP BY type ";

		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		String t = null, m = null;
		System.out.println("Tipo\tMédia\n");
		while (rs.next()) {
			t = rs.getString(1);
			m = rs.getString(2);
			System.out.println(t + "\t" + m);
		}
		sttmt.close();
		rs.close();
	}

	// Nome e DPS mais alto das armas cujo DPS seja maior que 450.
	// select type, max(dps) from item group by type having max(dps) > 450;
	public static void consulta15(Connection conn) throws SQLException {
		String sql = "SELECT type, max(dps) " + "FROM item " + "GROUP BY type " + "HAVING max(dps) > 450";

		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);

		String n = null, dps = null;
		System.out.println("Type\tDPS\n");
		while (rs.next()) {
			n = rs.getString(1);
			dps = rs.getString(2);
			System.out.println(n + "\t" + dps);
		}
		sttmt.close();
		rs.close();
	}
	
	// Media de dps dos personagens
	// select character.name, avg(item.dps) dps from character
	// inner join inventory on inventory.character = character.idcharacter
	// inner join item on item.iditem = inventory.iteminventory group by
	// character.name;
	public static void consulta16(Connection conn) throws SQLException {
		String sql = "SELECT character.name, avg(item.dps) dps " + "FROM character "
				+ "inner join inventory on inventory.character = character.idcharacter "
				+ "inner join item on item.iditem = inventory.iteminventory " + "GROUP BY character.name ";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		String n = null, s = null;
		System.out.println("Nome\tDPS\n");
		while (rs.next()) {
			n = rs.getString(1);
			s = rs.getString(2);
			System.out.println(n + "\t" + s);
		}
		sttmt.close();
		rs.close();
	}
	
	// Personagens com menor dano
	// select character.name, item.dps from character
	// inner join inventory on inventory.character = character.idcharacter
	// inner join item on item.iditem = inventory.iteminventory
	// where item.dps = (select MIN(item.DPS) from item) group by
	// character.name, item.dps;
	public static void consulta17(Connection conn) throws SQLException {
		String sql = "SELECT character.name, item.dps " + "FROM character "
				+ "inner join inventory on inventory.character = character.idcharacter "
				+ "inner join item on item.iditem = inventory.iteminventory "
				+ "WHERE item.dps = (select MIN(item.DPS) from item) group by character.name, item.dps ";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		String n = null, s = null;
		System.out.println("Nome\tDPS\n");
		while (rs.next()) {
			n = rs.getString(1);
			s = rs.getString(2);
			System.out.println(n + "\t" + s);
		}
		sttmt.close();
		rs.close();
	}
	
	// Nome dos itens (sem repetição) que tem tamanho menor que a média.
	// select distinct(name) from item where SIZEITEM < (select avg(sizeitem)
	// from item);
	public static void consulta18(Connection conn) throws SQLException {
		String sql = "SELECT distinct(name) " + "FROM item " + "WHERE sizeitem < (select avg(sizeitem) from item)";
		
		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);
		
		String n = null;
		System.out.println("Nome\n");
		while (rs.next()) {
			n = rs.getString(1);
			System.out.println(n);
		}
		sttmt.close();
		rs.close();
	}

	// Nome dos itens que tem DPS maior que a média geral dos items.
	// select name from item where DPS > (select avg(dps) from item);
	public static void consulta19(Connection conn) throws SQLException {
		String sql = "SELECT name " + "FROM item " + "WHERE DPS > (select avg(dps) from item) ";

		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);

		String n = null;
		System.out.println("Nome\n");
		while (rs.next()) {
			n = rs.getString(1);
			System.out.println(n);
		}
		sttmt.close();
		rs.close();
	}

	// As velocidades de ataque dos itens que tem o maior dps.
	// select speed from item where DPS = (select max(dps) from item) GROUP BY
	// SPEED;
	public static void consulta20(Connection conn) throws SQLException {
		String sql = "SELECT speed " + "FROM item " + "WHERE DPS = (select max(dps) from item) " + "GROUP BY speed";

		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);

		String s = null;
		System.out.println("Velo de Atq\n");
		while (rs.next()) {
			s = rs.getString(1);
			System.out.println(s);
		}
		sttmt.close();
		rs.close();
	}



	// Lista de nomes de profissões que tem 'funcionários' com a letra y no nome
	// select profession.name from character_has_profession
	// inner join profession on character_has_profession.profession_idprofession
	// = profession.idprofession
	// where character_has_profession.character_idcharacter in (select
	// idcharacter from character where name like '%y%') group by
	// profession.name
	public static void consulta21(Connection conn) throws SQLException {
		String sql = "SELECT profession.name " + "FROM character_has_profession "
				+ "inner join profession on character_has_profession.profession_idprofession = profession.idprofession "
				+ "WHERE character_has_profession.character_idcharacter in (select idcharacter from character where name like '%y%') "
				+ "GROUP BY profession.name ";

		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);

		String n = null;
		System.out.println("Profissão\n");
		while (rs.next()) {
			n = rs.getString(1);
			System.out.println(n);
		}
		sttmt.close();
		rs.close();
	}

	// Mostra itens que tem dps maiores que a media
	// select * from item where item.dps > (select avg(item.dps) from item)
	public static void consulta22(Connection conn) throws SQLException {
		String sql = "SELECT * " + "FROM item " + "WHERE item.dps > (select avg(item.dps) from item) ";

		Statement sttmt = conn.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);

		String id = null, n = null, r = null, l = null, sl = null, so = null, t = null, dps = null, spd = null,
				sI = null;
		System.out.println("ID\tNome\tReqLevel\tLvlItem\tSlot" + "\tOrigem\tTipo\tDPS\tVelocidade\tTamanho\n");
		while (rs.next()) {
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
			System.out.println(id + "\t" + n + "\t" + r + "\t" + l + "\t" + sl + "\t" + so + "\t" + t + "\t" + dps
					+ "\t" + spd + "\t" + sI);
		}
		sttmt.close();
		rs.close();
	}












}
