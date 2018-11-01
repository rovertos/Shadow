package model.expandable;

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
	
	public Compass getNextCW(){		
		int dirindex = this.ordinal();	
		return dirindex < 5 ? Compass.values()[dirindex + 1] : Compass.values()[0];		
	}
	
}
