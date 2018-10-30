package model.world;

import java.util.ArrayList;
import java.util.List;

public class Duchy {

	private County capital;
	
	public List<County> counties = new ArrayList<County>();
	
	public Duchy(){		
		
	}

	public County getCapital() {
		
		return capital;
		
	}

	public void setCapital(County capital) {
		
		this.capital = capital;
		
	}

	public List<County> getCounties() {
		
		return counties;
		
	}

	public void setCounties(List<County> counties) {
		
		this.counties = counties;
		
	}	
	
}
