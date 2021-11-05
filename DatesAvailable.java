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
	
	
	/*
	 *  This is my original DatesAvailable, which gave out of memory error when I tried to run it
	 public class DatesAvailable {
   	private final LocalDate startVacancy;
   	private final LocalDate endVacancy;
   	
   	public DatesAvailable(LocalDate startVacancy, LocalDate endVacancy) {
         	this.startVacancy = startVacancy;
         	this.endVacancy= endVacancy;
   	}
   	
   	public LocalDate getStartVacancy() {
         	return this.startVacancy;
   	}
   	
   	public LocalDate getEndVacancy() {
         	return this.endVacancy;
   	}
   	
   	public List<LocalDate> getVacancyWindow() {
         	List<LocalDate> vacancyWindow = new ArrayList<LocalDate>();
         	LocalDate start = getStartVacancy();
         	LocalDate end = getEndVacancy();
         	if (start == end) {
                	vacancyWindow.add(start);
         	}
         	else {
                	for (LocalDate date = start; date.isBefore(end); date.plusDays(1)) {
                       	vacancyWindow.add(date);
                	}
         	}
         	
         	return vacancyWindow;
   	}
}

	 */
	
}