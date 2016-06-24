package body;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Class {
	private static int idClass;
	private static String name;
	
	public static void selectAll(Connection connection) throws SQLException {
		String sql = "select * " + 
					 "from class";
		Statement sttmt = connection.createStatement();
		ResultSet rs = sttmt.executeQuery(sql);

		while (rs.next()) {
			idClass = rs.getInt("idClass");
			name = rs.getString("name");
			System.out.println(idClass + "\t" + name);
		}
		sttmt.close();
		rs.close();
	}
	
	//próximos métodos
	
	
	//arrumar
	/*
	 numero de charecteres de cada classe
	 select class.name, sum(1) from character
	 inner join class on class.idclass = character.class group by class.name
	*/
//	public static void quantPerClass(Connection conn) throws SQLException{
//		String sql = "SELECT class.name, sum(1) "
//				 + "FROM character inner join class on class.idclass = character.class group by class.name "
//				 + "GROUP BY class.name";
//		
//		Statement sttmt = conn.createStatement();
//		ResultSet rs = sttmt.executeQuery(sql);
//		
//		int x = 0;
//		System.out.println("Classe\tQuant\n");
//		while(rs.next()) {
//			name = rs.getString(1);
//			x = rs.getInt(2);
//			System.out.println(name+"\t"+x);
//		}
//		sttmt.close();
//		rs.close();
//	}
	
}
