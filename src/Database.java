import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

public class Database {
	private Place root = null;
	
	private HashMap<String, Place> placeHash = new HashMap<String, Place>();
	private HashMap<String, Location> locationHash = new HashMap<String, Location>();
	private HashMap<String, ArrayList<Location>> activityHash = new HashMap<String, ArrayList<Location>>();
	
	private static final DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	
	private AbstractPlaceFactory placeFactory = FactoryProducer.getFactory("PLACE_V1");
	
	/**
	 * 1. Toate informatiile despre locatia cu numele X
	 * @param locationX locatia despre care vrem sa aflam date
	 * @return date despre locatia ceruta
	 */
	public String task1(String locationX) {
		return locationHash.get(locationX).toString();
	}
	
	/**
	 * 2. top 5 locatii din tara/judetul/orasul X, pt perioada A-B, ordonate dupa
	 * costul total pentru a merge pe perioada A-B acolo.
	 * @param placeX Tara/judetul/orasul
	 * @return 5 locatii ordonate dupa cost
	 */
	public Location[] task2(String placeX, String startDate, String endDate) {
		Place place = placeHash.get(placeX);
		
		Date start, end;
		try {
			start = df.parse(startDate);
			end = df.parse(endDate);
			
			/* obtin toate locatiile ce se incadreaza intre cele 2 date */
			ArrayList<Location> topLocations = place.getLocationsBetween(start, end);
			
			/* sortez locatiile obtinute dupa pretul mediu pe zi, crescator,
			 * adica de la cel mai ieftin loc, pana la cel mai scump loc din
			 * top 5
			 */
			Collections.sort(topLocations, new LocationsMediumPriceComparator());
			
			
			Location[] top5 = new Location[5];
			for (int i = 0; i < 5 && i < topLocations.size(); i++)
				top5[i] = topLocations.get(i);
			
			
			return top5;
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/** 
	 * 3. Unde este cel mai ieftin sa practic 10 zile o activitate?
	 * Am considerat costul unei zile de practicat activitatea intr-o anumita
	 * locatie ca fiind cost_zilnic = cost_mediu_zi + cost_activitate.
	 * Altfel spus, am considerat ca acel cost_mediu_zi dat ca input reprezinta
	 * costul pentru nevoie de baza ale acelei locatii (cazare, mancare), la care
	 * se adauga costul activitatii propriu zise
	 * @param activity Activitatea pe care vrem sa o practicam 10 zile
	 * @return Locatia cea mai buna pt cele zile de activitate
	 */
	public Location task3(String activity) {
		ArrayList<Location> locations = activityHash.get(activity);
		if (locations == null)
			return new Location();
		
		double min = Double.MAX_VALUE;
		Location loc = null;
		
		for (Location l : locations) {
			double priceOfActivity = l.getPriceOf(activity);
			
			if (priceOfActivity + l.getMediumPrice() < min) {
				min = priceOfActivity + l.getMediumPrice();
				loc = l;
			}
		}
		
		return loc;
	}
	
	public static Database parseInput(BufferedReader bf) {
		Database newdb = new Database();
		newdb.root = newdb.placeFactory.getPlace("ROOT");
		
		/* construim toata ierarhia de tari/judete/orase/locatii */
		newdb.buildRecursively(bf, newdb.root);
		
		
		return newdb;
	}

	private void buildRecursively(BufferedReader bf, Place currentRoot) {
		String line;
		
		while (true) {
			try {
				line = bf.readLine();
				
				/* verificam ca avem inca linii de citit */
				if (line == null)
					break;
				
				/* eliminam taburile de la inceputul inputului */
				line = line.trim();
				
				/* daca avem - la inceputul liniei, inseamna ca am terminat de adaugat
				 * copiii sau locatiile lui currentRoot
				 */
				if (line.charAt(0) == '-')
					break;
				
				if (line.charAt(0) == '?') {
					/* obtinem locatia */
					Location loc = parseLocation(line.substring(1, line.length()));
					
					/* adaugam activitatile ce se pot practica in locatia loc in
					 * hashul de activitati
					 */
					for (Activity act : loc.getActivities()) {
						if (!activityHash.containsKey(act.getName())) {
							activityHash.put(act.getName(), new ArrayList<Location>());
						}
						
						activityHash.get(act.getName()).add(loc);
					}
					
					/* setam tara/judetul/orasul in care se afla locatia */
					loc.setPlace(currentRoot.toString());
					
					/* salvam locatia intr-un hash pt acces rapid la ea */
					locationHash.put(loc.getName(), loc);
					
					/* adaugam activitatea in tara/judetul/orasul de care apartine */
					currentRoot.addLocation(loc);
					
					System.out.println(loc);
				} else {
					/* creez un nou element in ierarie */
					String[] parts = line.split(":");
					Place child = placeFactory.getPlace(parts[1]);
					child.setName(parts[0]);
					
					/* adaugam noul copil in lista de copii a parintelui */
					currentRoot.addChild(child);
					
					/* adaugam noul copil in hashul global pt acces rapid */
					placeHash.put(child.getName(), child);
					
					/* pornim constructia arborelui, recursiv, pe child */
					buildRecursively(bf, child);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}


	private Location parseLocation(String substring) {
		Location loc = new Location();
		
		/* parsam datele despre o anumita locatie */
		String[] parts = substring.split(":");
		
		loc.setName(parts[0]);
		loc.setMediumPrice(Double.parseDouble(parts[1]));
		
		/* parsam activitatile aferente acelei locatii */
		String[] activities = parts[2].split(",");
		for (int i = 0; i < activities.length; i++) {
			String[] activityParts = activities[i].split("/");
			loc.getActivities().add(new Activity(activityParts[0], Double.parseDouble(activityParts[1])));
		}
		
		/* salvam datele de inceput si de sfarsit pentru locatia respectiva */

		try {
			loc.setStartDate(df.parse(parts[3]));
			loc.setEndDate(df.parse(parts[4]));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return loc;
	}

}
