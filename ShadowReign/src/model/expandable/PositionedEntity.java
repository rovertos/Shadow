package model.expandable;

public class PositionedEntity implements Comparable<PositionedEntity> {

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
	public int compareTo(PositionedEntity o) {
		
		if (row == o.row)
		
			return col - ((PositionedEntity)o).col;
		
		else
			
			return row - ((PositionedEntity)o).row;
		
	}	
	
	@Override
	public boolean equals(Object o){
		
		if (o instanceof PositionedEntity)
			
			return ((PositionedEntity)o).row == this.row && ((PositionedEntity)o).col == this.col;
		
		return false;
		
	}
	
}
