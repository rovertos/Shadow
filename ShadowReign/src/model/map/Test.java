package model.map;

import model.map.ExpandableLine.LineEntity;
import util.Print;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Hive h = new Hive();
		
		/*h.grow(Compass.SE,true);
		h.grow(Compass.SE,true);
		h.grow(Compass.SE,true);
		h.grow(Compass.NE,true);
		h.grow(Compass.SE,true);*/
		
		h.grow(Compass.NW,true);
		h.grow(Compass.SW,true);
		h.grow(Compass.S,true);
		h.grow(Compass.S,true);
		h.grow(Compass.NE,true);
		h.grow(Compass.SE,true);
		
		h.connectAll();
		
		Print.hive(h);
				
		for (ExpandableLine eline: h.rowlist){			
			System.out.print("row " + eline.index + ": ");			
			for (LineEntity le: eline.entitylist){				
				System.out.print(le.pe + " ");				
			}			
		}
		System.out.println("");
		
		for (ExpandableLine eline: h.collist){			
			System.out.print("col " + eline.index + ": ");			
			for (LineEntity le: eline.entitylist){				
				System.out.print(le.pe + " ");				
			}			
		}
		System.out.println("");
		
		for (ExpandableLine eline: h.diagsnwse){			
			System.out.print("diag nwse " + eline.index + ": ");			
			for (LineEntity le: eline.entitylist){				
				System.out.print(le.pe + " ");				
			}			
		}		
		System.out.println("");
		
		for (ExpandableLine eline: h.diagsnesw){			
			System.out.print("diag nesw " + eline.index + ": ");			
			for (LineEntity le: eline.entitylist){				
				System.out.print(le.pe + " ");				
			}
		}		
		
	}

}
