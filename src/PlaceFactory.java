
public class PlaceFactory extends AbstractPlaceFactory {

	@Override
	public Place getPlace(String placeType) {
		if (placeType.equalsIgnoreCase("ROOT")) {
			return new RootNode();
		} else if (placeType.equalsIgnoreCase("COUNTRY")) {
			return new Country();
		} else if (placeType.equalsIgnoreCase("REGION")) {
			return new Region();
		} else if (placeType.equalsIgnoreCase("CITY")) {
			return new City();
		}
		
		
		return null;
	}

}
