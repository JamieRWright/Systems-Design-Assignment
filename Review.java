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
	private Guest guest;
    private String reviewText;
	private RatingMap ratingMap;
	
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
	public double overallRating() {
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
		sb.append(overallRating());
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
	
}