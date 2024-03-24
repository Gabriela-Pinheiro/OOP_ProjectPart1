//Gabriela Pinheiro - R00225375 - Final Project

package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Practice implements Serializable{
	
	private ArrayList<Consultant> consultants;
	
	public Practice() {
		this.consultants = new ArrayList<Consultant>();
	}

	public void addConsultant(Consultant c) {
		this.consultants.add(c);
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


 
