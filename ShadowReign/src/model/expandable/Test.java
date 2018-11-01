package model.expandable;

import java.util.concurrent.ThreadLocalRandom;

import util.Print;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Hive h = new Hive();
		
		for (int i=0; i<100; i++){
			
			int randomNum = ThreadLocalRandom.current().nextInt(0, 6);
			
			Compass dir = Compass.values()[randomNum];
			//Compass dir = Compass.NE;
			
			boolean ok = h.grow(dir,3,5,true);
			
			if (!ok){
				
				System.out.println("Deadend at iteration ");
				
				break;
				
			}
			
		}
		
		System.out.println("");
		
		Print.hive(h,true);
		
	}
	
}
