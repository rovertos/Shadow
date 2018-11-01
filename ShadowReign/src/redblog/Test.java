package redblog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import model.expandable.Compass;
import model.expandable.Hex;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Compass[] t = {Compass.N, Compass.NE, Compass.SE, Compass.S, Compass.SW, Compass.NW};
		
		ArrayList<Compass> points = new ArrayList<Compass>(Arrays.asList(t.clone()));
		
		Collections.rotate(points, -Compass.N.ordinal());
		for (Compass c: points){System.out.print(c + " ");}
		System.out.println("");
		
		Collections.swap(points, 2, 5);
		for (Compass c: points){System.out.print(c + " ");}
		System.out.println("");
		
		Collections.swap(points, 3, 5);
		for (Compass c: points){System.out.print(c + " ");}		
		
		System.out.println("");
		
	}

}
