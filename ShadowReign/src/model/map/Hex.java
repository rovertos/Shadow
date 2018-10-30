package model.map;

public class Hex extends PositionedEntity {

	private Hex[] bordering = new Hex[6];
	
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
		//return "[" + row + "," + col + "]";
		return "[" + row + "," + col + ":" + borders() + "]";
	}	

	public Hex(int col, int row) {
		super(col, row);
	}	
	
	public void set(Compass dir, Hex hex){
		bordering[dir.ordinal()] = hex;
	}
	
	public Hex get(Compass dir){
		return bordering[dir.ordinal()];
	}
	
	public Hex[] getBordering(){
		return bordering;
	}
	
}
