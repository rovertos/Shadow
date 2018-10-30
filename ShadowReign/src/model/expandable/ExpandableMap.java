package model.expandable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpandableMap {

	public List<ExpandableLine> rowlist = new ArrayList<ExpandableLine>();
	
	public List<ExpandableLine> collist = new ArrayList<ExpandableLine>();
	
	public void put(PositionedEntity pe){
		
		ExpandableLine rowline = putInLine(pe, rowlist, pe.row);
		
		ExpandableLine colline = putInLine(pe, collist, pe.col);
		
		Collections.sort(rowline.entitylist);
		
		Collections.sort(colline.entitylist);
		
	}
	
	ExpandableLine putInLine(PositionedEntity pe, List<ExpandableLine> linelist, int index){
		
		ExpandableLine chosenline = null;
		
		for (ExpandableLine line: linelist){
			
			if (line.index == index) {
				
				chosenline = line;
				
				break;
				
			}			
			
		}
		
		if (chosenline == null){
			
			chosenline = new ExpandableLine(index);
			
			linelist.add(chosenline);			
			
		}
		
		chosenline.put(pe);
		
		Collections.sort(linelist);	
		
		return chosenline;
				
	}
	
	public PositionedEntity get(int row, int col){
		
		for (ExpandableLine exprow: rowlist){
			
			if (exprow.index == row)
				
				return exprow.get(col);
			
			else if (exprow.index > row)
				
				break;
			
		}
		
		return null;
		
	}
	
	public PositionedEntity[][] getAsArray(){
		
		int rowmin = getMostDistantIndex(Compass.N);
		
		int rowmax = getMostDistantIndex(Compass.S);
		
		int colmin = getMostDistantIndex(Compass.W);
		
		int colmax = getMostDistantIndex(Compass.E);
		
		PositionedEntity[][] array = new PositionedEntity[rowmax - rowmin + 1][colmax - colmin + 1];		
		
		for (ExpandableLine row: rowlist){
			
			for (PositionedEntity pe: row.entitylist){
				
				array[pe.row - rowmin][pe.col - colmin] = pe;
				
			}
			
		}
		
		return array;
		
	}
	
	@SuppressWarnings("incomplete-switch")
	public int getMostDistantIndex(Compass point){
		
		switch (point) {
		
		case N: return rowlist.get(0).index;
		
		case E: return collist.get(collist.size()-1).index;	
		
		case S: return rowlist.get(rowlist.size()-1).index;
		
		case W: return collist.get(0).index;
		
		}
		
		return -999;
		
	}
	
}
