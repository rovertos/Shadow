package model.world;

import java.util.ArrayList;
import java.util.List;

public class Map {

	// double-height: {N, NE, SE, S, SW, NW}
	public static int[][] deltasDoubleHeight = {{-2,0},{-1,1},{1,1},{2,0},{1,-1},{-1,-1}};
	
	public final Area[][] areas;
	
	public List<Area> isolationList = new ArrayList<Area>();
	
	public final int N;
	
	public int xmin;	
	public int xmax;
	public int ymin;
	public int ymax;
	
	public Map(int N){
		
		areas = new Area[2*N+1][2*N+1];
		
		xmin = N;
		xmax = N;
		ymin = N;
		ymax = N;
		
		this.N = N;
		
	}
	
	public void setCounty(int x, int y){
		
		xmin = x < xmin ? x : xmin;
		xmax = x > xmax ? x : xmax;
		ymin = y < ymin ? y : ymin;
		ymax = y > ymax ? y : ymax;
		
		areas[x][y] = new County(x, y);
		
		for (int[] delta: Map.deltasDoubleHeight){
			
			int dx = x + delta[0];
			int dy = y + delta[1];
			
			if (areas[dx][dy] == null)
				
				areas[dx][dy] = new Area(dx, dy);
			
		}
		
	}
		
	public Area[] getOutskirts(int x, int y){
		
		Area[] outskirts = new Area[6];
		
		for (int i=0; i<6; i++){
			
			int dx = x + Map.deltasDoubleHeight[i][0];
			int dy = y + Map.deltasDoubleHeight[i][1];
			
			outskirts[i] = areas[dx][dy];
					
		}
		
		return outskirts;
		
	}
	
	public void calculateCongestion(int depth){
		
		for (int i=xmin; i<=xmax; i++){
			
			boolean evenRow = i % 2 == 0;
			boolean yminEven = ymin % 2 == 0;			
			int starty = (evenRow && yminEven) || (!evenRow && !yminEven) ? ymin : ymin + 1;
			
			for (int j=starty; j<=ymax; j+=2){
				
				if (areas[i][j] != null){
					areas[i][j].resetCongestion();
					Area[] outskirts = getOutskirts(i, j);
					int congestion = 0;
					for (Area area: outskirts){
						if (area != null && area instanceof County)
							congestion++;
					}
					areas[i][j].congestion.add(new Integer(congestion));
					
				}
				
			}
			
		}
		
		if (depth > 1)
			
			iterateCongestion(0, depth);
		
	}
	
	private void iterateCongestion(int iteration, int depth){
		
		if (iteration < depth){
		
			for (int i=xmin; i<=xmax; i++){
				
				boolean evenRow = i % 2 == 0;
				boolean yminEven = ymin % 2 == 0;				
				int starty = (evenRow && yminEven) || (!evenRow && !yminEven) ? ymin : ymin + 1;
				
				for (int j=starty; j<=ymax; j+=2){
					
					if (areas[i][j] != null){
						Area[] outskirts = getOutskirts(i, j);
						int congestion = 0;
						for (Area area: outskirts){
							if (area != null && area instanceof County)
								congestion+=area.congestion.get(iteration);
						}
						areas[i][j].congestion.add(new Integer(congestion));
					}
					
				}
				
			}
			
			iterateCongestion(iteration + 1, depth);
		
		}
		
	}
	
}
