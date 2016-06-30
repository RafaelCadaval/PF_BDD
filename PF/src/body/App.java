package body;

import java.sql.Connection;
import java.sql.SQLException;

/***
 * Main program class.
 */
public class App {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = runDatabase.getConnection();
		DBOps.start(conn);
		conn.close();
	}

}
