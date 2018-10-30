package model.world;

import java.util.ArrayList;
import java.util.List;

public class Area implements Comparable<Area> {

	public final int x;
	
	public final int y;
	
	public final List<Integer> congestion = new ArrayList<Integer>();
	
	public Area(int x, int y){
		
		this.x = x;
		
		this.y = y;
		
	}
	
	public void resetCongestion(){
		
		congestion.clear();
		
	}
	
	public int getCongestion(){
		
		return congestion.size() > 0 ? congestion.get(congestion.size()-1).intValue() : 6;
		
	}

	@Override
	public int compareTo(Area o) {
		
		return this.getCongestion() - o.getCongestion();
		
	}
	
}
