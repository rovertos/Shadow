package util;

import model.expandable.Compass;
import model.expandable.Hive;
import model.expandable.PositionedEntity;
import model.world.County;
import model.world.Map;

public class Print {

	public static void map(Map map){
				
		for (int i=map.xmin; i<=map.xmax; i++){
		
			boolean ieven = i % 2 == 0;
			
			for (int j=map.ymin; j<=map.ymax; j++){
		
				boolean jeven = j % 2 == 0;
				
				if ((ieven && jeven) || (!ieven && !jeven)){
					
					if (map.areas[i][j] != null){
						
						if (map.areas[i][j] instanceof County){
							
							System.out.print("*");
							
						} else {
							
							System.out.print(" ");
							
						}
						
						System.out.print(s(map.areas[i][j].getCongestion(),4));
						
					} else {
						
						System.out.print(r("-",5));
						
					}
					
					
				} else {
					
					System.out.print("     ");
					
				}
				
				System.out.print(r(" ",10));
				
			}
			
			System.out.println("");
			
		}		
		
	}
	
	
	public static void hive(Hive hive){
		
		int rowmin = hive.getMostDistantIndex(Compass.N);
		int colmin = hive.getMostDistantIndex(Compass.W);
		int colmax = hive.getMostDistantIndex(Compass.E);
					
		PositionedEntity[][] array = hive.getAsArray();

		int rowIndex = rowmin;		
		
		System.out.println(r("=",6+(colmax-colmin)*4+1));
		
		for (int row=0; row<array.length; row++){
			
			boolean rowindexEven = rowIndex % 2 ==0;
			
			int colIndex = colmin;
			
			for (int col=0; col<array[row].length; col++){		
				
				boolean colindexEven = colIndex % 2 ==0;
				
				String empty = (rowindexEven && colindexEven || !rowindexEven && !colindexEven) ? "-" : " ";
				
				String p = array[row][col] != null ? "O" : empty;
				
				if (col == 0) System.out.print(s(rowIndex,3) + " ");
				
				System.out.print(p + "   ");
				
				colIndex++;
				
			}
			
			System.out.println("");
			
			rowIndex++;
			
		}
		
		System.out.print(" ");		
		for (int i=colmin; i<=colmax; i++){
			System.out.print(s(i,4));
		}
		System.out.println("");
		System.out.println(r("=",6+(colmax-colmin)*4+1));
		
	}
	
	private static String s(int intstr, int width){
		return s("" + intstr, width);
	}
	
	private static String s(String str, int width){
		StringBuffer buf = new StringBuffer();
		int length = str.length();
		for (int i=width; i>length; i--){
			buf.append(" ");
		}
		if (width > length)
			buf.append(str.substring(0, length));
		else
			buf.append(str);
		return buf.toString();
	}
	
	private static String r(String str, int times){
		StringBuffer buf = new StringBuffer();
		for (int i=0; i<times; i++){
			buf.append(str);
		}
		return buf.toString();
	}
	
}
