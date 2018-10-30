package model.world;

import java.util.ArrayList;
import java.util.List;

public class Kingdom {

	private County capital;
	
	private List<Duchy> duchies = new ArrayList<Duchy>();
	
	private List<County> counties = new ArrayList<County>();
	
	public Kingdom(){
		
		
	}

	public County getCapital() {
		
		return capital;
		
	}

	public void setCapital(County capital) {
		
		this.capital = capital;
		
	}

	public List<Duchy> getDuchies() {
		
		return duchies;
		
	}

	public void setDuchies(List<Duchy> duchies) {
		
		this.duchies = duchies;
		
	}

	public List<County> getCounties() {
		
		return counties;
		
	}

	public void setCounties(List<County> counties) {
		
		this.counties = counties;
		
	}	
	
}
