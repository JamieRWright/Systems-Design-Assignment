import java.util.Iterator;

/**
 * Class designed to be used to create Kitchen objects
 * 
 * @version 1.0 
 *
 * @author Nur Yasmeen Rashdiah Binti Nor Azman Rashed
 *
 */

public class Kitchen {
	final Amenities amenities;
	
	public Kitchen(Amenities amenities) {
		this.amenities = amenities;
	}
	
	public Amenities getAmenities() {return this.amenities;}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		Amenities amenities = getAmenities();
		
        Iterator<String> amenityIterator = amenities.categorySet().iterator();
		
		while (amenityIterator.hasNext()) {
			String a = amenityIterator.next();
			sb.append(a);
			sb.append(": ");
			sb.append(amenities.get(a));
			sb.append("\n");
		}
		
		return sb.toString();
	}
}