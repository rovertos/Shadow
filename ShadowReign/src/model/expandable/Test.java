package model.expandable;

import java.util.concurrent.ThreadLocalRandom;

import util.Print;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Hive h = new Hive();
		
		for (int i=0; i<40; i++){
			
			int randomNum = ThreadLocalRandom.current().nextInt(0, 6);
			
			Compass rnd = Compass.values()[randomNum];
			
			boolean ok = h.grow(Compass.values()[randomNum],2,4,false);
			
			if (!ok){
				
				System.out.println("Deadend at iteration " + i + " going " + rnd);
				
				break;
				
			}
			
			System.out.print(h.getAnchor() + " ");
			
		}
		
		System.out.println("");
		
		Print.hive(h,true);
		
	}
	
}
