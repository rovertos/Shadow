package model.map;

import java.util.ArrayList;

import model.map.ExpandableLine.LineEntity;

public class Hive extends ExpandableMap {

	private int anchorR = 0;
	
	private int anchorC = 0;
		
	// double-height
	public static int[][] diffsDoubleHeight = {{-2,0},{-1,1},{1,1},{2,0},{1,-1},{-1,-1}};
	//public static int[][] diffsCubic = {{-1,0},{-1,1},{0,1},{1,0},{1,-1},{0,-1}};
	
	protected ArrayList<ExpandableLine> diagsnwse = new ArrayList<ExpandableLine>();
	
	protected ArrayList<ExpandableLine> diagsnesw = new ArrayList<ExpandableLine>();	
	
	public Hive(){
		
		put(new Hex(anchorR, anchorC));
		
	}
	
	public void grow(Compass dir){
		
		grow(dir, false);
		
	}
	
	public void grow(Compass dir, boolean moveAnchor){
		
		int dirindex = Hex.sides.indexOf(dir);
		
		int drow = diffsDoubleHeight[dirindex][0];
		
		int dcol = diffsDoubleHeight[dirindex][1];
		
		Hex newHex = new Hex(anchorR+drow, anchorC+dcol);
		
		put(newHex);
		
		if (moveAnchor){
			
			anchorR = newHex.row;
			
			anchorC = newHex.col;
			
		}
		
	}
	
	public void put(PositionedEntity pe){
		
		super.put(pe);
		
		putInLine(pe, diagsnwse, pe.row - pe.col);
		
		putInLine(pe, diagsnesw, pe.row + pe.col);
		
	}
	
	public void connectAll(){
		
		for (ExpandableLine row: rowlist){			
			Hex lastHex = (Hex)row.getFirst();			
			for (int i=1; i<row.entitylist.size(); i++){				
				Hex thisHex = (Hex)row.entitylist.get(i).pe;				
				if (thisHex.row == lastHex.row + 1){					
					thisHex.set(Compass.N, lastHex);
					lastHex.set(Compass.S, thisHex);					
				}				
				lastHex = thisHex;				
			}			
		}
		
		for (ExpandableLine row: diagsnwse){		
			Hex lastHex = (Hex)row.getFirst();			
			for (int i=1; i<row.entitylist.size(); i++){				
				Hex thisHex = (Hex)row.entitylist.get(i).pe;				
				if (thisHex.row == lastHex.row + 1){					
					thisHex.set(Compass.NW, lastHex);
					lastHex.set(Compass.SE, thisHex);					
				}				
				lastHex = thisHex;
			}			
		}
		
		for (ExpandableLine row: diagsnesw){		
			Hex lastHex = (Hex)row.getFirst();			
			for (int i=1; i<row.entitylist.size(); i++){				
				Hex thisHex = (Hex)row.entitylist.get(i).pe;				
				if (thisHex.row == lastHex.row + 1){					
					thisHex.set(Compass.NE, lastHex);
					lastHex.set(Compass.SW, thisHex);					
				}				
				lastHex = thisHex;
			}			
		}		
		
	}
		
	public void print(){
		
		int colmin = getMostDistantIndex(Compass.W);		
		int colmax = getMostDistantIndex(Compass.E);		
		
		boolean colmineven = colmin % 2 == 0;
		
		String empty = "-----";
		
		for (ExpandableLine row: rowlist){
			
			boolean thisroweven = row.index % 2 == 0;
			
			int start = 0;
			
			if (thisroweven){
				start = colmineven ? colmin : colmin - 1;
			} else {		
				System.out.print("   ");
				start = colmineven ? colmin + 1 : colmin;
			}
			
			for (int i=start; i<=colmax; i+=2){						
				String s = row.get(i) != null ? ""+(Hex)row.get(i) : empty;
				System.out.print(s + "   ");
			}
			System.out.println("");			
			
		}
				
	}
	
}
