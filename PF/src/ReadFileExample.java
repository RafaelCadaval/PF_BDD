import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Simple example of reading a file in Java
 *
 */
public class ReadFileExample {

	// Change this line to the full file path in your computer.
	private static String mFilename = "example.sql";

	public static void main(String[] args) throws IOException {

		// Creates a BufferedReader
		BufferedReader br = new BufferedReader(new FileReader(mFilename));
		
		// Reads the first line
		String line = br.readLine();

		// While finding lines, keeps printing it
		while (line != null) {
			System.out.println(line);
			line = br.readLine();
		}
		
		br.close();
	}

}
