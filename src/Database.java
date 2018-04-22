import java.io.BufferedReader;
import java.util.HashMap;

public class Database {
	private Place root;
	private HashMap<String, Country> countryHash = new HashMap<String, Country>();
	private HashMap<String, Region> regionHash = new HashMap<String, Region>();
	private HashMap<String, City> cityHash = new HashMap<String, City>();
	private HashMap<String, Location> locationHash = new HashMap<String, Location>();
	
	private Database() {
		
	}
	
	
	public static Database parseInput(BufferedReader bf) {
		Database newdb = new Database();
		
		
		
		
		return newdb;
	}

}
