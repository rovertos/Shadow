package model.expandable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hive extends ExpandableMap {

	private Hex anchor;
		
	// double-height
	public static int[][] diffsDoubleHeight = {{-2,0},{-1,1},{1,1},{2,0},{1,-1},{-1,-1}};
	//public static int[][] diffsCubic = {{-1,0},{-1,1},{0,1},{1,0},{1,-1},{0,-1}};
	
	public List<ExpandableLine> diagsnwse = new ArrayList<ExpandableLine>();
	
	public List<ExpandableLine> diagsnesw = new ArrayList<ExpandableLine>();
	
	public List<Bordered> borderedlist = new ArrayList<Bordered>();
	
	public Hive(){
		
		anchor = new Hex(0, 0);
		
		put(anchor);
		
	}
	
	public void grow(Compass dir){
		
		grow(dir, 2);
		
	}
	
	public void grow(Compass dir, boolean moveAnchor){
		
		grow(dir, 2, moveAnchor);
		
	}	
	
	public void grow(Compass dir, int cw){
		
		grow(dir, cw, false);
		
	}
	
	public void grow(Compass dir, int cw, boolean moveAnchor){
		
		if (anchor.get(dir) != null){
			
			if (anchor.borders() < 6){
				
				grow(dir.getNextCW(), cw, moveAnchor);
				
			} else {
				
				anchor = getLeastBordered();
				
				grow(dir, cw, moveAnchor);
				
			}
			
		} else {
			
			int drow = diffsDoubleHeight[dir.ordinal()][0];
			
			int dcol = diffsDoubleHeight[dir.ordinal()][1];
			
			Hex newHex = new Hex(anchor.row+drow, anchor.col+dcol);
			
			put(newHex);
			
			if (cw > 1){
				
				grow(dir.getNextCW(), cw-1, moveAnchor);
				
			} else if (moveAnchor){
				
				anchor = newHex;
				
			}
			
		}
		
	}
	
	public void put(Hex hex){
		
		ExpandableLine rowline = putInLine(hex, rowlist, hex.row);
		
		Collections.sort(rowline.entitylist);		
		
		ExpandableLine colline = putInLine(hex, collist, hex.col);
		
		Collections.sort(colline.entitylist);
		
		connect(hex, colline, Compass.N, Compass.S, 2);
				
		ExpandableLine dnwseline = putInLine(hex, diagsnwse, hex.row - hex.col);
		
		Collections.sort(dnwseline.entitylist);
		
		connect(hex, dnwseline, Compass.NW, Compass.SE, 1);
		
		ExpandableLine dneswline = putInLine(hex, diagsnesw, hex.row + hex.col);
		
		Collections.sort(dneswline.entitylist);
		
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
	
	public Hex getLeastBordered(){
		
		return borderedlist.get(0).hex;
		
	}
	
	public Hex getMostBordered(){
		
		return borderedlist.get(borderedlist.size()-1).hex;
		
	}
	
	ExpandableLine putInLine(Hex hex, List<ExpandableLine> linelist, int index){
		
		ExpandableLine eline = super.putInLine(hex, linelist, index);
		
		borderedlist.add(new Bordered(hex));
		
		Collections.sort(borderedlist);
		
		return eline;
		
	}
	
	public class Bordered implements Comparable<Bordered>{
		
		public final Hex hex;
		
		public Bordered(Hex hex){			
			this.hex = hex;			
		}
		
		public int borders(){			
			return hex.borders();			
		}

		@Override
		public int compareTo(Bordered o) {
			// TODO Auto-generated method stub
			return this.borders() - o.borders();
		}
		
	}
		
}
