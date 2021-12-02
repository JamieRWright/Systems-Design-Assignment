package main;
public class Living {
	private boolean wifi;
	private boolean television;
	private boolean satellite;
	private boolean streaming;
	private boolean dvdPlayer;
	private boolean boardGames;
	
	// living space creator
	public Living(boolean wifi, boolean television, boolean satellite, boolean streaming, boolean dvdPlayer, 
			boolean boardGames) {
		this.wifi = wifi;
		this.television = television;
		this.satellite = satellite;
		this.streaming = streaming;
		this.dvdPlayer = dvdPlayer;
		this.boardGames = boardGames;
	}
		
	public boolean getWifi() {
		return this.wifi;
	}
	
	public boolean getTelevision() {
		return this.television;
	}
	
	public boolean getSatellite() {
		return this.satellite;
	}
	
	public boolean getStreaming() {
		return this.streaming;
	}
	
	public boolean getDvdPlayer() {
		return this.dvdPlayer;
	}
	
	public boolean getBoardGames() {
		return this.boardGames;
	}
	
	public boolean setWifi(boolean value, Integer PropertyID)
	{
		boolean output=false;
		if (value==true)
		{
			output=TDatabase.UpdateFacilityValue("Living_Facility", "WIFI", PropertyID.toString(), 1);
		}
		else
		{
			output=TDatabase.UpdateFacilityValue("Living_Facility", "WIFI", PropertyID.toString(), 0);
		}
		return output;
	}
	
	public boolean setTV(boolean value, Integer PropertyID)
	{
		boolean output=false;
		if (value==true)
		{
			output=TDatabase.UpdateFacilityValue("Living_Facility", "Television", PropertyID.toString(), 1);
		}
		else
		{
			output=TDatabase.UpdateFacilityValue("Living_Facility", "Television", PropertyID.toString(), 0);
		}
		return output;
	}
	
	public boolean setSat(boolean value, Integer PropertyID)
	{
		boolean output=false;
		if (value==true)
		{
			output=TDatabase.UpdateFacilityValue("Living_Facility", "Satellite", PropertyID.toString(), 1);
		}
		else
		{
			output=TDatabase.UpdateFacilityValue("Living_Facility", "Satellite", PropertyID.toString(), 0);
		}
		return output;
	}
	
	public boolean setStream(boolean value, Integer PropertyID)
	{
		boolean output=false;
		if (value==true)
		{
			output=TDatabase.UpdateFacilityValue("Living_Facility", "Streaming", PropertyID.toString(), 1);
		}
		else
		{
			output=TDatabase.UpdateFacilityValue("Living_Facility", "Streaming", PropertyID.toString(), 0);
		}
		return output;
	}
	
	public boolean setDvd(boolean value, Integer PropertyID)
	{
		boolean output=false;
		if (value==true)
		{
			output=TDatabase.UpdateFacilityValue("Living_Facility", "DVDPlayer", PropertyID.toString(), 1);
		}
		else
		{
			output=TDatabase.UpdateFacilityValue("Living_Facility", "DVDPlayer", PropertyID.toString(), 0);
		}
		return output;
	}
	
	public boolean setBoard(boolean value, Integer PropertyID)
	{
		boolean output=false;
		if (value==true)
		{
			output=TDatabase.UpdateFacilityValue("Living_Facility", "BoardGames", PropertyID.toString(), 1);
		}
		else
		{
			output=TDatabase.UpdateFacilityValue("Living_Facility", "BoardGames", PropertyID.toString(), 0);
		}
		return output;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Amenities Provided: \nWifi: ");
		sb.append(getWifi());
		sb.append("\n");
		sb.append("Television: ");
		sb.append(getTelevision());
		sb.append("\n");
		sb.append("Satellite: ");
		sb.append(getSatellite());
		sb.append("\n");
		sb.append("Streaming: ");
		sb.append(getStreaming());
		sb.append("\n");
		sb.append("DVD Player: ");
		sb.append(getDvdPlayer());
		sb.append("\n");
		sb.append("Board Games: ");
		sb.append(getBoardGames());
		
		String result = sb.toString();
		
		result = result.replaceAll("true", "\u2713");
		result = result.replaceAll("false", "\u2717");
	
		return result;
	}
		
}
