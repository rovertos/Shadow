package model.expandable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import util.Print;

public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Hex[] test1 = {new Hex(1,1),new Hex(2,2),new Hex(3,3),new Hex(4,4),new Hex(5,5)};
		
		Hex[] test2 = test1.clone(); 
		
		//Collections.rotate(Arrays.asList(test1), -2);
		Collections.shuffle(Arrays.asList(test1));
		
		for (Hex s: test1){
			System.out.print(s + " ");		
		}
		System.out.println("");
		for (Hex s: test2){
			System.out.print(s + " ");			
		}		
				
		/*Hive h1 = new Hive();
		
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

		print(h2);*/
		
	}

}
