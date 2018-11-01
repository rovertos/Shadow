package model.expandable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Hive extends ExpandableMap {

	private Hex anchor;
		
	// double-height
	public static int[][] diffsDoubleHeight = {{-2,0},{-1,1},{1,1},{2,0},{1,-1},{-1,-1}};
	
	public List<ExpandableLine> diagsnwse = new ArrayList<ExpandableLine>();
	public List<ExpandableLine> diagsnesw = new ArrayList<ExpandableLine>();
	
	public List<Hex> growPath = new ArrayList<Hex>();
	
	public Hive(){

		anchor = new Hex(0, 0);
		
		put(anchor);
		
		surroundAndActivate(anchor);
		
	}	
	
	public boolean grow(Compass dir, int minBorders, int maxBorders, boolean rndEnabled){
		
		ArrayList<Hex> outskirts = new ArrayList<Hex>(Arrays.asList(anchor.getOutskirts().clone()));
		
		Collections.rotate(outskirts, -dir.ordinal());
		Collections.swap(outskirts, 2, 5);
		Collections.swap(outskirts, 3, 5);
		
		if (isCandidateForActivation(outskirts.get(0), minBorders, maxBorders)){
			
			surroundAndActivate(outskirts.get(0));
			
			anchor = outskirts.get(0);
			
			return true;
			
		} else {
			
			outskirts.remove(0);
			
			if (rndEnabled){
				
				Collections.shuffle(outskirts);
				
			}
				
			for (Hex hex: outskirts){
				
				if (isCandidateForActivation(hex, minBorders, maxBorders)){
					
					surroundAndActivate(hex);
					
					anchor = hex;
					
					return true;
					
				}
								
			}		
			
		}
		
		if (growPath.size() > 1){
		
			anchor.setDeadend(true);
			
			anchor.setActive(false);
			
			growPath.remove(growPath.size()-1);
			
			anchor = growPath.get(growPath.size()-1);
			
			//System.out.println("backtracking to " + anchor);
			
			return grow(dir, minBorders, maxBorders, rndEnabled);
		
		} else {
			
			return false;
			
		}
		
	}
	
	
	private boolean isCandidateForActivation(Hex hex, int minBorders, int maxBorders){
		
		if (hex == null || hex.isActive() || hex.isDeadend())
			
			return false;
		
		else {
			
			if (hex.activeConnections() >= maxBorders)
				
				return false;
			
			for (Hex around: hex.getConnections(true)){
				
				if (around.activeConnections() >= maxBorders)
					
					return false;
				
			}			
			
			List<Hex> activeConnections = anchor.getConnections(true);
			
			List<Hex> loneConnections = new ArrayList<Hex>();
			
			for (Hex active: activeConnections){
				
				int activeCons = active.activeConnections();
				
				if (activeCons > 0 && activeCons < minBorders)
					
					loneConnections.add(active);
				
			}
									
			if (loneConnections.size() > 0) {
				
				boolean connectsToALoner = false;
				
				for (Hex loner: loneConnections){
					
					if (loner.getConnections(false).contains(hex)){
						
						connectsToALoner = true;
						
						break;
						
					}
					
				}
				
				return connectsToALoner;
				
			}
			
		}
		
		return true;
		
	}
	
	public Hex getAnchor(){		
		return anchor;
	}
	
	public void setAnchor(Hex anchor){
		if (anchor == null)
			throw new IllegalArgumentException("Anchor can not be null!");
		if (!anchor.isActive())
			surroundAndActivate(anchor);
		this.anchor = anchor;
	}
	
	private void surroundAndActivate(Hex hex){
		surround(hex);
		growPath.add(hex);		
		hex.setActive(true);
	}	
	
	public boolean surround(Hex hex){
		boolean areasadded = false;		
		Hex[] outskirts = hex.getOutskirts();
		for (int i=0; i<6; i++){
			if (outskirts[i] == null){
				Hex area = new Hex(hex.row + diffsDoubleHeight[i][0], hex.col + diffsDoubleHeight[i][1]);
				put(area);
				areasadded = true;
			}
		}
		return areasadded;
	}	
	
	public List<Hex> getSurroundings(Hex hex, int remainingDistance, List<Hex> surroundings){
		if (remainingDistance > 0){
			for (Hex further: hex.getOutskirts()){				
				if (further != null && !surroundings.contains(further)){				
					surroundings.add(further);
					getSurroundings(further, remainingDistance-1, surroundings);
				}
			}			
		}		
		return surroundings;
	}
	
	public void put(Hex hex){
		putInLine(hex, rowlist, hex.row);
		ExpandableLine colline = putInLine(hex, collist, hex.col);
		connect(hex, colline, Compass.N, Compass.S, 2);
		ExpandableLine dnwseline = putInLine(hex, diagsnwse, hex.row - hex.col);
		connect(hex, dnwseline, Compass.NW, Compass.SE, 1);
		ExpandableLine dneswline = putInLine(hex, diagsnesw, hex.row + hex.col);
		connect(hex, dneswline, Compass.NE, Compass.SW, 1);
	}
	
	private void connect(Hex hex, ExpandableLine eline, Compass up, Compass down, int rowdiff){
		int peindex = eline.entitylist.indexOf(hex);
		if (eline.entitylist.size() > 1){
			if (peindex > 0){
				Hex previous = (Hex)eline.entitylist.get(peindex-1);
				if (previous.row + rowdiff == hex.row){
					hex.set(up, previous);
					previous.set(down, hex);
				}
			}
			if (peindex + 1 < eline.entitylist.size()){
				Hex next = (Hex)eline.entitylist.get(peindex+1);
				if (next.row == hex.row + rowdiff){
					hex.set(down, next);
					next.set(up, hex);
				}
			}
		}
	}
		
}
