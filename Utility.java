public class Utility {
	private boolean centralHeating;
	private boolean washingMachine;
	private boolean dryingMachine;
	private boolean fireExtinguisher;
	private boolean smokeAlarm;
	private boolean firstAid;
	
	// utility space creator
	public Utility(boolean centralHeating, boolean washingMachine, boolean dryingMachine, boolean fireExtinguisher, boolean smokeAlarm, 
			boolean firstAid) {
		this.centralHeating = centralHeating;
		this.washingMachine = washingMachine;
		this.dryingMachine = dryingMachine;
		this.fireExtinguisher = fireExtinguisher;
		this.smokeAlarm = smokeAlarm;
		this.firstAid = firstAid;
	}
		
	public boolean getCentralHeating() {
		return this.centralHeating;
	}
	
	public boolean getWashingMachine() {
		return this.washingMachine;
	}
	
	public boolean getDryingMachine() {
		return this.dryingMachine;
	}
	
	public boolean getFireExtinguisher() {
		return this.fireExtinguisher;
	}
	
	public boolean getSmokeAlarm() {
		return this.smokeAlarm;
	}
	
	public boolean getFirstAid() {
		return this.firstAid;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Central Heating: ");
		sb.append(getCentralHeating());
		sb.append("\n");
		sb.append("Washing Machine: ");
		sb.append(getWashingMachine());
		sb.append("\n");
		sb.append("Drying Machine: ");
		sb.append(getDryingMachine());
		sb.append("\n");
		sb.append("Fire Extinguisher: ");
		sb.append(getFireExtinguisher());
		sb.append("\n");
		sb.append("Smoke Alarm: ");
		sb.append(getSmokeAlarm());
		sb.append("\n");
		sb.append("First Aid: ");
		sb.append(getFirstAid());
	
		return sb.toString();
	}
}