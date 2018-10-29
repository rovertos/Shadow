package model.map;

public class PositionedEntity {

	public int row;
	
	public int col;	
	
	public PositionedEntity(int row, int col){
		
		this.row = row;
		
		this.col = col;
		
	}
	
	@Override
	public String toString(){
		return "["+this.row+","+this.col+"]";
	}
	
	@Override
	public boolean equals(Object o){
		
		if (o instanceof PositionedEntity)
			
			return ((PositionedEntity)o).row == this.row && ((PositionedEntity)o).col == this.col;
		
		return false;
		
	}
	
}
