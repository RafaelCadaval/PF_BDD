package body;

import java.sql.Connection;
import java.sql.SQLException;

/***
 * Main program class.
 */
public class App {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = runDatabase.getConnection();

		//ITEM
//		Item.consulta1(conn);
//		Item.consuta2(conn);
//		Item.consulta3(conn);
//		Item.consulta4(conn);
//		Item.consulta5(conn);
//		Item.consulta6(conn);
//		Item.consulta20(conn);
//		Item.consulta21(conn);
//		Item.consulta22(conn);
		Item.consulta23(conn);
		
		//CHARACTER
//		Character.consulta7(conn);
//		Character.consulta8(conn);
//		Character.consulta9(conn);
//		Character.consulta10(conn);
//		Character.consulta11(conn);
//		Character.consulta13(conn);
//		Character.consulta14(conn);
//		Character.consulta15(conn);
//		Character.consulta16(conn);
//		Character.consulta17(conn);
//		Character.consulta18(conn);
//		Character.consulta19(conn);
		
		//ZONE
//		Zone.consulta12(conn);
		
		conn.close();
	}

}
