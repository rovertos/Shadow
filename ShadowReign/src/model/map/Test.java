package model.map;

import model.map.ExpandableLine.LineEntity;

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
				
		System.out.println("====================");
		
		PositionedEntity[][] array = h.getAsArray();
		
		for (int row=0; row<array.length; row++){
			for (int col=0; col<array[row].length; col++){				
				String p = array[row][col] != null ? "*" : "-";
				System.out.print(" " + p);
			}
			System.out.println("");
		}
		
		System.out.println("====================");
		
		h.connectAll();
		
		h.print();	
		
		System.out.println("====================");
		
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
