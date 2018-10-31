package util;

import model.expandable.Compass;
import model.expandable.ExpandableLine;
import model.expandable.Hex;
import model.expandable.Hive;
import model.expandable.PositionedEntity;
import model.world.County;
import model.world.Map;

public class Print {
	
	public static void hive(Hive hive){
		hive(hive,false,false);
	}
	
	public static void hive(Hive hive, boolean printlines){
		hive(hive,printlines,false);
	}	
	
	public static void hive(Hive hive, boolean printlines, boolean printInactives){
		
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
				if (col == 0) System.out.print(s(rowIndex,3) + " ");
				
				boolean colindexEven = colIndex % 2 ==0;
				String empty = (rowindexEven && colindexEven || !rowindexEven && !colindexEven) ? "-" : " ";
				String p = null;
				if (array[row][col] != null){
					if (array[row][col] instanceof Hex && ((Hex)array[row][col]).isActive()){
						p = "O";						
					} else {
						p = printInactives ? "x" : "-";
					}
				} else {
					p = empty;
				}				
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
		
		if (printlines){
			for (ExpandableLine eline: hive.rowlist){			
				Print.line(eline, printInactives);		
			}
			System.out.println("");
		}
		
	}
	
	public static void line(ExpandableLine eline, boolean printInactives){
		
		System.out.print(s(eline.index,3) + ": ");
		
		for (PositionedEntity pe: eline.entitylist){
			
			if (pe instanceof Hex && (((Hex)pe).isActive() || printInactives))
			
				System.out.print(pe + " ");
			
		}
		System.out.println("");
		
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
