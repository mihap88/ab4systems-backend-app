import java.util.ArrayList;
import java.util.Date;

public abstract class Place {
	protected ArrayList<Place> children = null;
	protected ArrayList<Location> locations = null;
	protected String name;
	
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return this.name;
	}


	public void addLocation(Location loc) {
		if (locations == null)
			locations = new ArrayList<Location>();
		
		locations.add(loc);
	}

	public void addChild(Place child) {
		if (children == null)
			children = new ArrayList<Place>();
		
		children.add(child);
	}


	/**
	 * Functie care selecteaza toate locatiile ale caror startDate si endDate sunt
	 * in intervalul inchis [start; end]
	 * @param start
	 * @param end
	 * @return
	 */
	public ArrayList<Location> getLocationsBetween(Date start, Date end) {
		ArrayList<Location> topLocations = new ArrayList<Location>();
		
		if (this.locations != null) {
			for (Location loc : this.locations) {
				if (loc.getStartDate().compareTo(start) >= 0
						&& loc.getEndDate().compareTo(end) <= 0) {
					topLocations.add(loc);
				}
			}
		}
		
		if (this.children != null) {
			for (Place child : children) {
				topLocations.addAll(child.getLocationsBetween(start, end));
			}
		}
		
		
		return topLocations;
	}
	
}
