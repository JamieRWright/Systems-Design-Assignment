package main;
import java.util.Iterator;
import java.util.List;

/**
 * Class designed to be used to create Review objects
 * 
 */

public class Review {
	private int guestID;
	private int propertyID;
    private String reviewText;
	private RatingMap ratingMap;
	
	/**
     * Constructor
     * @param guest who left the review
     * @param propertyID the property id for the property related to the review
     * @param reviewText guest's text about their experience, explaining their ratings
     * @param guestID guest rating in all six rating categories; if new then default at 0
     * 
     */
	public Review(int guestID, int propertyID, String reviewText, RatingMap ratingMap) {
		this.guestID = guestID;
		this.propertyID = propertyID;
		this.reviewText = reviewText;
		this.ratingMap = ratingMap;
	}
	
	// Accessor methods
	public String getText() {
		return this.reviewText;
	}
	
	public int getGuestID() {
		return this.guestID;
	}
	
	public RatingMap getRatingMap() {
		return this.ratingMap;
	}
	
	public int getPropertyID() {
		return this.propertyID;
	}
	
	// Get ReviewID from the database
	public int getID() {
		return Integer.parseInt(TDatabase.GetReviewID(this.propertyID, this.guestID));
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
	
	// get the average rating for a single category
	public static double getAverageFor(List<RatingMap> ratings, RatingCategory category) {
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
		sb.append(TDatabase.Guests.get(guestID).getName());
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
	
