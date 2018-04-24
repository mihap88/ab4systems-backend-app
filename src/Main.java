import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {


		try {
			BufferedReader bf = new BufferedReader(new FileReader("input.txt"));
			Database db = Database.parseInput(bf);
			
			
			System.out.println("\nTest task1");
			System.out.println(db.task1("balci"));
			
			Location[] top5 = db.task2("romania", "01/01/2000", "01/01/2019");
			System.out.println("\nTest task2");
			for (int i = 0; i < 5; i++) {
				if (top5[i] != null) {
					System.out.println(top5[i]);
				}
			}
			
			System.out.println("\nTest task3");
			System.out.println(db.task3("gratar"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
