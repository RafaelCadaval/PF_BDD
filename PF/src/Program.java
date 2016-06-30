import java.sql.Connection;
import java.sql.SQLException;

import body.Database;

/***
 * Main program class.
 */
public class Program {

	public static void main(String[] args) throws SQLException {
		
		// Gets a database connection.
		Connection conn = Database.getConnection();

		// Selects all movies
		Movies.selectAll(conn);
		
		// Close the connection.
		conn.close();
		
		// Selects one movie
		// Movies.select(conn, 2);
		
		// Insert a new movie
		// Movies.insert(conn, "Test", 1990, "Another Test");
		
		// Update an existing movie
		// Movies.update(conn, 42, "Test", 1990, "Another Test 2");
		
		// Deletes a movie
		// Movies.delete(conn, 41);
	}

}
