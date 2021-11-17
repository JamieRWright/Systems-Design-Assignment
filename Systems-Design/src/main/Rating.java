package main;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class designed to be used to create Rating objects from the user reviews
 *
 * @version 1.1 
 *
 * @author Nur Yasmeen Rashdiah Binti Nor Azman Rashed
 *
 */

enum RatingCategory {
	Cleanliness("Cleanliness"),
	Communication("Communication (response time)"),
	CheckIn("Check-In (how easy)"),
	Accuracy("Accuracy (of public info)"),
	Location("Location (quality)"),
	Value("Value (for money)");
	
	private final String name;
	RatingCategory(String n) {name = n;}
	
	public String getName() {return name;}
	
}

public class Rating {
	
	// overall rating and the six categories that the guests will rate
    private List <RatingMap> ratings;
	
	/**
     * Constructor
     * @param A map containing all six categories and their values
     * 
     */
	public Rating(List<RatingMap> ratings) {
		this.ratings = ratings;
	}
	
    public List<RatingMap> getRatingMapList() {
    	return this.ratings;
    }
	
	// calculate overall rating from the six categories
	public double getHostOverall() {
		double totalHostRatings = 0;
		int numRatings = 0;
			
		for (RatingMap singleRating : ratings) {
				
			double totalSingleRating = 0;
			Iterator<RatingCategory> categoryIterator = singleRating.categorySet().iterator();
				
			while (categoryIterator.hasNext()) {
				RatingCategory r = categoryIterator.next();
				totalSingleRating = totalSingleRating + singleRating.get(r);
			}
				
			double avgSingleRating = totalSingleRating / 6;
			totalHostRatings += avgSingleRating;
			numRatings++;
		}
			
		double avgHostRating = totalHostRatings / numRatings;
			
		return  Math.round(avgHostRating * 10.0) / 10.0;
	}
		
		// Calculate average ratings for a single category
	public double getAverageFor(List<RatingMap> ratings, RatingCategory category) {
		double totalForCategory = 0;
		int numRatings = 0;
		
		for (RatingMap singleRating : ratings) {
			Iterator<RatingCategory> categoryIterator = singleRating.categorySet().iterator();
			
			while (categoryIterator.hasNext()) {
				RatingCategory r = categoryIterator.next();
				if (r == category) {totalForCategory += singleRating.get(r);}
			}
			
			numRatings++;
		}
		double averageForCategory = totalForCategory / numRatings;
		return Math.round(averageForCategory * 10.0) / 10.0;
	}	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		RatingCategory[] categories = {RatingCategory.Cleanliness, RatingCategory.Communication, RatingCategory.CheckIn, RatingCategory.Accuracy, RatingCategory.Location,RatingCategory.Value};
		sb.append("Overall rating: ");
		sb.append(getHostOverall());
		sb.append("\n");
		
		int pos = 0;
		for (RatingCategory category : categories) {
			sb.append(category.getName());
			sb.append(": ");
			sb.append(getAverageFor(getRatingMapList(), category));
			if (pos != (categories.length - 1)) {sb.append(", ");}
			
			pos++;
		}
		
		
		return sb.toString();
	}
	
	public static void main (String [] arg) {
		List<RatingMap> rate = new ArrayList<RatingMap>();
		RatingMap ratings = new RatingMap();
		ratings.put(RatingCategory.Cleanliness, 5);
		ratings.put(RatingCategory.Communication, 4);
		ratings.put(RatingCategory.CheckIn, 5);
		ratings.put(RatingCategory.Accuracy, 3);
		ratings.put(RatingCategory.Location, 5);
		ratings.put(RatingCategory.Value, 5);
		rate.add(ratings);
		
		RatingMap ratings2 = new RatingMap();
		ratings2.put(RatingCategory.Cleanliness, 5);
		ratings2.put(RatingCategory.Communication, 4);
		ratings2.put(RatingCategory.CheckIn, 2);
		ratings2.put(RatingCategory.Accuracy, 3);
		ratings2.put(RatingCategory.Location, 1);
		ratings2.put(RatingCategory.Value, 4);
		rate.add(ratings2);
		
		Rating hostRating = new Rating(rate);
		System.out.println(hostRating);
		
	}
	
}