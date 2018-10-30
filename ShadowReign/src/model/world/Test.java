package model.world;

import java.util.concurrent.ThreadLocalRandom;

import util.Print;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int N = 100;
		
		int x = N;
		
		int y = N;
		
		Map map = new Map(N);
		
		map.setCounty(x, y);
		
		for (int i=0; i<10; i++){
			
			int randomNum = ThreadLocalRandom.current().nextInt(0, 6);
			
			int[] delta = Map.deltasDoubleHeight[randomNum];
			
			x = x + delta[0];
			
			y = y + delta[1];
			
			map.setCounty(x, y);
			
		}
		
		map.calculateCongestion(5);
		
		Print.map(map);
		
	}

}
