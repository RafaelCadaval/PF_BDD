package dao;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DBMethods {
	
	
	public void loadDB(Connection conn){
		Path path = Paths.get("script.txt");
		try(Scanner in = new Scanner(Files.newBufferedReader(path, Charset.forName("utf8")))){
			String s = null;
			while(in.hasNext()){
				s = in.nextLine();
			}
		}
		catch(IOException e){
			System.err.format("Erro de E/S: %s%n", e);
		}
	}
	
	public void consulta(Connection conn, String query){
		
	}
	
	public static boolean insert (Connection conn, String query) throws SQLException {
		Statement sttmt = conn.createStatement();
		int rowCount = sttmt.executeUpdate(query);
		return rowCount == 1;
	}
}
