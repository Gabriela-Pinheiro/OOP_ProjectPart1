//Gabriela Pinheiro - R00225375 - Final Project

package model;

import java.util.ArrayList;
import collections.PatientList;
import collections.VisitList;

public class Consultant extends Person{

	public static int ID = 1;
	private PatientList patients;
	private String expertise;
	 
	public Consultant(Name name, String phone, String expertise, String ID) {
		super(name, phone);
		super.setId(ID);
		
		this.patients = new PatientList();
		this.expertise = expertise;
	}
	
	public Consultant(Name name, String phone, String expertise) {
		this(name, phone, expertise, ("CO00" + ID));
		ID++;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}	

	public ArrayList<Patient> getPatients() {
		return this.patients.getPatients();
	}

	public void addPatient(Patient patient) {
		this.patients.addPatient(patient);
	}

	public void removePatient(Patient patient) {
		if(this.patients.getPatients().size() > 0) {
			this.patients.removePatient(patient.getName().toString());
		} else {

		}
	}
	
	public void setPatientList(PatientList patientList) {
		this.patients = patientList;
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
		return this.getId()+ " " + this.getName().getFirstName() + " " + this.getName().getLastName() + " " + getExpertise();
	}
}
