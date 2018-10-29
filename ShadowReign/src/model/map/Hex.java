package model.map;

import java.util.ArrayList;
import java.util.List;

public class Hex extends PositionedEntity {

	public static List<Compass> sides;
	
	private Hex[] bordering = new Hex[6];
	
	static {		
		sides = new ArrayList<Compass>();		
		sides.add(Compass.N);sides.add(Compass.NE);sides.add(Compass.SE);
		sides.add(Compass.S);sides.add(Compass.SW);sides.add(Compass.NW);	
	}
	
	public int borders(){
		int b = 0;
		for (Hex border: bordering){
			if (border != null)
				b++;
		}
		return b;
	}
	
	@Override
	public String toString(){
		return "[ " + borders() + " ]";
	}	

	public Hex(int col, int row) {
		super(col, row);
	}	
	
	public void set(Compass dir, Hex hex){
		bordering[sides.indexOf(dir)] = hex;
	}
	
	public Hex get(Compass dir){
		return bordering[sides.indexOf(dir)];
	}
	
	public Hex[] getBordering(){
		return bordering;
	}
	
}
