import java.util.Comparator;

public class LocationsMediumPriceComparator implements Comparator<Location> {

	@Override
	public int compare(Location o1, Location o2) {
		if (o1.getMediumPrice() < o2.getMediumPrice()) {
			return -1;
		} else if (o1.getMediumPrice() > o2.getMediumPrice()) {
			return 1;
		} else {
			return 0;
		}
	}

}
