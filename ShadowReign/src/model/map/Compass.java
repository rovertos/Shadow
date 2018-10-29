package model.map;

public enum Compass {

	N(0),
	NE(1),
	SE(2),
	S(3),
	SW(4),
	NW(5),
	
	E(6),
	W(7);
	
	private final int index;
	
	Compass(int index){		
		this.index = index;		
	}
	
}
