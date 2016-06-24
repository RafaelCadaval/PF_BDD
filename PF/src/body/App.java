package body;

import java.sql.Connection;
import java.sql.SQLException;

/***
 * Main program class.
 */
public class App {

	public static void main(String[] args) throws SQLException {
		
		// Gets a database connection.
		Connection conn = Database.getConnection();

		// Selects all movies
//		Class.selectAll(conn);
		
		//ITEM
//		Item.nTAS2OR4(conn);
//		Item.itemNameOBDPS(conn);
//		Item.mWDPS_GBType(conn);
//		Item.nameAndDPSHT450(conn);
//		Item.nameDPSHTm(conn);
//		Item.aSIWithHDPS(conn);
		
		//CHARACTER
//		Character.charsByClass(conn);
//		Character.nameWithEndingKER(conn);
//		Character.nameWithItem690(conn);
//		Character.nameDM_GBC_OBN(conn);
		
		//ZONE
//		Zone.zoneTypes(conn);
		// Close the connection.
		conn.close();
	}

}
