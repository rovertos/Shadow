package model.expandable;

import java.util.concurrent.ThreadLocalRandom;

import util.Print;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Hive h = new Hive();
		
		for (int i=0; i<14; i++){
			
			int randomNum = ThreadLocalRandom.current().nextInt(0, 6);
			
			h.grow(Compass.values()[randomNum],1,true);
			
		}
		
		print(h);		
		
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
