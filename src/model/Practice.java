//Gabriela Pinheiro - R00225375- Project_Part1

package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Practice implements Serializable{
	
	private ArrayList<Consultant> consultants;
	
	public Practice() {
		this.consultants = new ArrayList<Consultant>();
	}

	public Consultant addConsultant(Consultant c) {
		try {
			this.consultants.add(c);		
		} catch (Exception e) {
			return null;
		} 
		return c;
	}
	
	public boolean removeConsultant(String name) {
		Consultant remove = this.searchConsltant(name);
		
		if(remove != null) {
			this.consultants.remove(remove);
			return true;
		}
		return false;
	}
	
	public Consultant searchConsltant(String name) {
		Consultant obj = null;
		for(Consultant c: this.consultants) {
			if(c.getName().toString().equals(name)) {
				obj = c;
			}
		}
		return obj;
	}
	
	public ArrayList<Consultant> getConsultants() {
		return this.consultants;
	}
	
	public String showConsultants() {
		String toReturn = "Available consultants: ";
		for(Consultant consultant: this.getConsultants()) {
			toReturn += "\n" + consultant.getId() + ": " + consultant.getName() + " - " + consultant.getExpertise();
		}
		return toReturn;
	}
	
	public ArrayList<String> getConsultantData() {
		ArrayList<String> consultantData= new ArrayList<String>();
		for(Consultant c: this.consultants) {
			consultantData.add(c.toString());
		}
		return consultantData;
	}
	
	public void setConsultants(ArrayList<Consultant> consultants) {
		this.consultants = consultants;
	}

	public String showConsultantsDetails() {
		String toReturn = "";
		for(Consultant consultant: this.getConsultants()) {
			toReturn += "\n\n" + consultant.getId() + ": " + consultant.getName() +
					"\n" + consultant.showPatientsAndVisits();
		}
		return toReturn;
	}

	@Override
	public String toString() {
		return "Practice " + consultants;
	}
	
	
}


 
