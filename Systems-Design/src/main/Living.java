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
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Wifi: ");
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
	
		return sb.toString();
	}
		
}