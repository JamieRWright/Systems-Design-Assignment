import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Stores the data for amenities to be used by facility classes
 *
 * @version 1.0
 *
 * @author Nur Yasmeen Rashdiah Binti Nor Azman Rashed
 * 
 */

public class Amenities {
public final Map<String, Boolean> AmenitiesMap = new HashMap<>();
	
    /**
     * Add an amenity and its value
     * @param amenity, such as microwave etc.
     * @param b, boolean whether or not it's available
     */
	public void put(String amenity, boolean b) {
		AmenitiesMap.put(amenity, b);
	}
	
    /**
     * Check if an amenity is available or not
     * @param amenity wanted
     * @return true/false value for that amenity
     */
	
	public boolean get(String amenity) {
		return AmenitiesMap.get(amenity);
	}
	
    /**
     * Get the amenities stored
     * @return amenities in the map
     */
	public Set<String> categorySet() {
		return AmenitiesMap.keySet();
	}
	

}