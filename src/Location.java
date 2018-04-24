import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Location {
	public static final DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	
	
	private String name;
	private String place;
	private double mediumPrice;
	private ArrayList<Activity> activities = new ArrayList<Activity>();
	private Date startDate;
	private Date endDate;

	public double getPriceOf(String activity) {
		for (Activity act : activities) {
			if (act.getName().equals(activity))
				return act.getDailyPrice();
		}
		
		return 0;
	}
	
	@Override
	public String toString() {
		String allActivities = "";
		for (Activity act : activities) {
			allActivities += act.getName() + "-" + act.getDailyPrice() + "  ";
		}
		
		
		return "Name: " + name + ", place: " + place + ", medium price: " + mediumPrice + ", start date: " + df.format(startDate)
				+ ", end date: " + df.format(endDate) + ", activities: " + allActivities;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMediumPrice() {
		return mediumPrice;
	}

	public void setMediumPrice(double mediumPrice) {
		this.mediumPrice = mediumPrice;
	}

	
	
	public ArrayList<Activity> getActivities() {
		return activities;
	}

	public void setActivities(ArrayList<Activity> activities) {
		this.activities = activities;
	}

	
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setPlace(String newPlace) {
		this.place = newPlace;
	}
	
	public String getPlace() {
		return place;
	}
}
