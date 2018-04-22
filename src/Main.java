import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.DatabaseMetaData;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		System.out.println("Hello World!");
		
		try {
			BufferedReader bf = new BufferedReader(new FileReader("input.txt"));
			Database db = Database.parseInput(bf);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
