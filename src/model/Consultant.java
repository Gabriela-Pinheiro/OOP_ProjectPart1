//Gabriela Pinheiro - R00225375 - Final Project

package model;

import java.util.ArrayList;
import collections.PatientList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Consultant extends Person{
	
	public static int ID = 1;
	private PatientList patients;
	private StringProperty expertise;
	
	public Consultant(Name name, String phone, String expertise) {
		super(name, phone);
		this.patients = new PatientList();
		this.expertise = new SimpleStringProperty(expertise);
		super.setId("CO00", ID);
		ID++;
	}

	public StringProperty getExpertiseProperty() {
		return expertise;
	}
	public String getExpertise() {
		return expertise.get();
	}

	public void setExpertise(String expertise) {
		this.expertise = new SimpleStringProperty(expertise);
	}	

	public ArrayList<Patient> getPatients() {
		return this.patients.getPatients();
	}

	public void addPatient(Patient patient) {
		this.patients.addPatient(patient);
	}
	
	public void addPatientVisit(Patient p, Visit v) {
		p.addVisit(v);
	}
	
	public String showPatients() {
		String toReturn = "";
		for(Patient p: patients.getPatients()) {
			toReturn += "\n" + p.getId()+ ": " + p.getName();
		}
		return toReturn;
	}
	
	public String showPatientsAndVisits() {
		String toReturn = "";
		for(Patient p: patients.getPatients()) {
			toReturn += "\n" + p.getId()+ ": " + p.getName() + "\n" + p.getPatientVisits() + "\n" ;
		}
		return toReturn;	
	}

	@Override
	public String toString() {
		return this.getId()+ " " + this.getName().getFirstName() + " " + this.getName().getLastName() + " " + expertise.get();
	}
}
