package model.map;

import java.util.ArrayList;
import java.util.Collections;

public class ExpandableMap {

	protected ArrayList<ExpandableLine> rowlist = new ArrayList<ExpandableLine>();
	
	protected ArrayList<ExpandableLine> collist = new ArrayList<ExpandableLine>();
	
	public void put(PositionedEntity pe){
		
		putInLine(pe, rowlist, pe.row);
		
		putInLine(pe, collist, pe.col);
		
	}
	
	void putInLine(PositionedEntity pe, ArrayList<ExpandableLine> linelist, int index){
		
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
			
			for (ExpandableLine.LineEntity re: row.entitylist){
				
				array[re.pe.row - rowmin][re.pe.col - colmin] = re.pe;
				
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
