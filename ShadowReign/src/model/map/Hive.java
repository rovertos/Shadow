package model.map;

import java.util.ArrayList;
import java.util.Collections;

public class Hive extends ExpandableMap {

	private int anchorR = 0;
	
	private int anchorC = 0;
		
	// double-height
	public static int[][] diffsDoubleHeight = {{-2,0},{-1,1},{1,1},{2,0},{1,-1},{-1,-1}};
	//public static int[][] diffsCubic = {{-1,0},{-1,1},{0,1},{1,0},{1,-1},{0,-1}};
	
	public ArrayList<ExpandableLine> diagsnwse = new ArrayList<ExpandableLine>();
	
	public ArrayList<ExpandableLine> diagsnesw = new ArrayList<ExpandableLine>();	
	
	public Hive(){
		
		put(new Hex(anchorR, anchorC));
		
	}
	
	public void grow(Compass dir){
		
		grow(dir, false);
		
	}
	
	public void grow(Compass dir, boolean moveAnchor){
		
		int drow = diffsDoubleHeight[dir.ordinal()][0];
		
		int dcol = diffsDoubleHeight[dir.ordinal()][1];
		
		Hex newHex = new Hex(anchorR+drow, anchorC+dcol);
		
		put(newHex);
		
		if (moveAnchor){
			
			anchorR = newHex.row;
			
			anchorC = newHex.col;
			
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
		
}
