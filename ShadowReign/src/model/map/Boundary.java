package model.map;

import java.util.ArrayList;

public class Boundary {

	public final Compass point;
	
	public ArrayList<PositionedEntity> entitylist = new ArrayList<PositionedEntity>();
	
	public Boundary(Compass point){
		
		this.point = point;
		
	}
	
}
