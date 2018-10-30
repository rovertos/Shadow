package model.map;

import java.util.ArrayList;
import java.util.Collections;

public class ExpandableLine implements Comparable<ExpandableLine> {

	public ArrayList<PositionedEntity> entitylist = new ArrayList<PositionedEntity>();
	
	public final int index;
	
	public ExpandableLine(int index){
		
		this.index = index;
		
	}
	
	public void put(PositionedEntity pe){
		
		int entityindex = entitylist.indexOf(pe);
		
		if (entityindex > -1)
			
			entitylist.set(entityindex, pe);
			
		else
			
			entitylist.add(pe);
		
	}
	
	public PositionedEntity getFirst(){
		
		return entitylist.get(0);
		
	}
	
	public PositionedEntity getLast(){
		
		return entitylist.get(entitylist.size()-1);
		
	}
	
	public PositionedEntity get(int col){
		
		for (PositionedEntity pe: entitylist){
			
			if (pe.col == col)
				
				return pe;
			
			else if (pe.col > col)
				
				break;
			
		}
		
		return null;
		
	}
		
	@Override
	public int compareTo(ExpandableLine o) {
		
		return this.index - o.index;
		
	}
	
}
