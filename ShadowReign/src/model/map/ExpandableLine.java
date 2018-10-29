package model.map;

import java.util.ArrayList;
import java.util.Collections;

public class ExpandableLine implements Comparable<ExpandableLine> {

	public ArrayList<LineEntity> entitylist = new ArrayList<LineEntity>();
	
	public final int index;
	
	public ExpandableLine(int index){
		
		this.index = index;
		
	}
	
	public void put(PositionedEntity pe){
		
		LineEntity newLineEntity = new LineEntity(pe);
		
		int entityindex = entitylist.indexOf(newLineEntity);
		
		if (entityindex > -1){
			
			entitylist.remove(entityindex);
			
			entitylist.add(entityindex, newLineEntity);
			
		} else {
			
			entitylist.add(newLineEntity);
			
			Collections.sort(entitylist);
			
		}
		
	}
	
	public PositionedEntity getFirst(){
		
		return entitylist.get(0).pe;
		
	}
	
	public PositionedEntity getLast(){
		
		return entitylist.get(entitylist.size()-1).pe;
		
	}
	
	public PositionedEntity get(int col){
		
		for (LineEntity re: entitylist){
			
			if (re.pe.col == col)
				
				return re.pe;
			
			else if (re.pe.col > col)
				
				break;
			
		}
		
		return null;
		
	}
		
	class LineEntity implements Comparable<LineEntity> {
		
		protected final PositionedEntity pe;
		
		protected LineEntity(PositionedEntity pe){
			
			this.pe = pe;
			
		}
		
		@Override
		public boolean equals(Object o){
			
			if (o instanceof LineEntity)
				
				return ((LineEntity)o).pe.equals(this.pe);
			
			return false;
			
		}		

		@Override
		public int compareTo(LineEntity o) {
			
			if (pe.row == o.pe.row)
			
				return pe.col - ((LineEntity)o).pe.col;
			
			else
				
				return pe.row - ((LineEntity)o).pe.row;
			
		}		
		
	}
		
	@Override
	public int compareTo(ExpandableLine o) {
		
		return this.index - o.index;
		
	}
	
}
