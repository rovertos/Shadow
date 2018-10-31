package model.expandable;

import java.util.ArrayList;
import java.util.List;

public class Hex extends PositionedEntity {

	private Hex[] outskirts = new Hex[6];
	
	private boolean active;
	
	public int activeConnections(){
		int b = 0;
		for (Hex connection: outskirts){
			if (connection != null && connection.isActive())
				b++;
		}
		return b;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString(){
		if (isActive())
			return "[" + row + "," + col + ": " + activeConnections() + "]";
		else
			return " " + row + "," + col + ": " + activeConnections() + " ";
	}	

	public Hex(int col, int row) {
		super(col, row);
	}	
	
	public void set(Compass dir, Hex hex){
		outskirts[dir.ordinal()] = hex;
	}
	
	public Hex get(Compass dir){
		return outskirts[dir.ordinal()];
	}
	
	public Hex[] getOutskirts(){
		return outskirts;
	}
	
	public List<Hex> getConnections(boolean active){
		List<Hex> connetions = new ArrayList<Hex>();
		for (Hex hex: outskirts){
			if (hex != null && (hex.isActive() == active))
				connetions.add(hex);
		}
		return connetions;
	}
		
}
