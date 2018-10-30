package model.expandable;

import java.util.concurrent.ThreadLocalRandom;

import util.Print;

public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Hive h1 = new Hive();
		
		h1.grow(Compass.SE,true);
		h1.grow(Compass.SE,true);
		h1.grow(Compass.SE,true);
		h1.grow(Compass.NE,true);
		h1.grow(Compass.SE,true);
		
		print(h1);
		
		Hive h2 = new Hive();
		
		h2.grow(Compass.NW,true);
		h2.grow(Compass.SW,true);
		h2.grow(Compass.S,true);
		h2.grow(Compass.S,true);
		h2.grow(Compass.NE,true);
		h2.grow(Compass.SE,true);

		print(h2);
		
	}
	
	private static void print(Hive h){
		
		Print.hive(h);
		
		for (ExpandableLine eline: h.rowlist){			
			System.out.print("row " + eline.index + ": ");			
			for (PositionedEntity pe: eline.entitylist){				
				System.out.print(pe + " ");				
			}			
		}
		System.out.println("");
		
		for (ExpandableLine eline: h.collist){			
			System.out.print("col " + eline.index + ": ");			
			for (PositionedEntity pe: eline.entitylist){				
				System.out.print(pe + " ");				
			}			
		}
		System.out.println("");
		
		for (ExpandableLine eline: h.diagsnwse){			
			System.out.print("diag nwse " + eline.index + ": ");			
			for (PositionedEntity pe: eline.entitylist){				
				System.out.print(pe + " ");				
			}			
		}		
		System.out.println("");
		
		for (ExpandableLine eline: h.diagsnesw){			
			System.out.print("diag nesw " + eline.index + ": ");			
			for (PositionedEntity pe: eline.entitylist){				
				System.out.print(pe + " ");				
			}
		}		
		System.out.println("");
		
	}

}
