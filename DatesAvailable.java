import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * stores date objects as a list containing dates for which
 * a property is available
 * @version 1.0 
 *
 * @author Nur Yasmeen Rashdiah Binti Nor Azman Rashed
 *
 */

public class DatesAvailable {
	private final String startVacancy;
	private final String endVacancy;
	
	/**
     * Constructor
     * @param startVacancy the starting date for the property's vacancy window
     * @param endVacancy the ending date for the property's vacancy window
     *
     */
	public DatesAvailable(String startVacancy, String endVacancy) {
		this.startVacancy = startVacancy;
		this.endVacancy= endVacancy;
	}
	
	public String getStartVacancy() {
		return this.startVacancy;
	}
	
	public String getEndVacancy() {
		return this.endVacancy;
	}
	
	
}