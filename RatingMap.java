import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Stores the data for rating
 *
 * @version 1.0
 *
 * @author Nur Yasmeen Rashdiah Binti Nor Azman Rashed
 * 
 */

public class RatingMap {
	public Map<RatingCategory, Integer> categoryToRatingMap = new HashMap<>();
	
    /**
     * Add a rating and its category
     * @param category of the value
     * @param rating given by user for category in question
     */
	public void put(RatingCategory category, int value) {
		categoryToRatingMap.put(category, value);
	}
	
    /**
     * Get rating value given category
     * @param category wanted
     * @return rating for that category
     */
	
	public int get(RatingCategory category) {
		return categoryToRatingMap.get(category);
	}
	
    /**
     * Get the ratings stored
     * @return ratings in the map
     */
	public Set<RatingCategory> categorySet() {
		return categoryToRatingMap.keySet();
	}
}