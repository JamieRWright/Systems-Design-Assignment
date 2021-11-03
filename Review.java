import java.util.Iterator;

/**
 * Class designed to be used to create Review objects
 *
 * @version 1.0 
 *
 * @author Nur Yasmeen Rashdiah Binti Nor Azman Rashed
 *
 */

public class Review {
	final Guest guest;
    final String reviewText;
	final RatingMap ratingMap;
	
	/**
     * Constructor
     * @param guest who left the review
     * @param guest's text about their experience, explaining their ratings
     * @param guest rating in all six rating categories; if new then default at 0
     * 
     */
	public Review(Guest guest, String reviewText, RatingMap ratingMap) {
		this.guest = guest;
		this.reviewText = reviewText;
		this.ratingMap = ratingMap;
	}
	
	public String getText() {
		return this.reviewText;
	}
	
	public Guest getGuest() {
		return this.guest;
	}
	
	public RatingMap getRatingMap() {
		return this.ratingMap;
	}
	
	
	// calculate overall rating from the six categories
	public double getOverall() {
		double totalRating = 0;
		
		Iterator<RatingCategory> categoryIterator = ratingMap.categorySet().iterator();
		
		while (categoryIterator.hasNext()) {
			RatingCategory r = categoryIterator.next();
			totalRating = totalRating + getRatingMap().get(r);
		}
		
		double avgRating = totalRating / 6;
		
		return Math.round(avgRating * 10.0) / 10.0;
	}
	
	// get the rating for a single category
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getGuest().getName());
		sb.append("\n");
		sb.append("Overall rating: ");
		sb.append(getOverall());
		sb.append("\n");
		
		RatingCategory[] categories = {RatingCategory.Cleanliness, RatingCategory.Communication, RatingCategory.CheckIn, RatingCategory.Accuracy, RatingCategory.Location,RatingCategory.Value};
		int pos = 0;
		
		for (RatingCategory category : categories) {
			sb.append(category.getName());
			sb.append(": ");
			sb.append(getRatingMap().get(category));
			
			if (pos != (categories.length - 1)) {
				sb.append(", ");
			}
			
			pos++;			
		}
		
		return sb.toString();
	}
	
	public static void main (String [] arg) {
		RatingMap ratings = new RatingMap();
		ratings.put(RatingCategory.Cleanliness, 5);
		ratings.put(RatingCategory.Communication, 4);
		ratings.put(RatingCategory.CheckIn, 5);
		ratings.put(RatingCategory.Accuracy, 3);
		ratings.put(RatingCategory.Location, 5);
		ratings.put(RatingCategory.Value, 5);
		Address add = new Address("3A", "Cash Street", "America", "53100");
		Guest parker = new Guest("Parker", "Alice?", add, "thief45457@gmail.com", "glenreader4000isbest");
		Review r = new Review(parker, "Kinda nice. Vent is small.", ratings);
		
		System.out.println(r);
	}
	
	
}