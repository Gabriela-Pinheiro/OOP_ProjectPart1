//Gabriela Pinheiro - R00225375 - Final Project

package model;

import java.util.ArrayList;
import java.util.Objects;

import collections.PatientList;
import collections.VisitList;

public class Consultant extends Person{
	
	public static int ID = 1;
	private PatientList patients;
	private String expertise;
	
	public Consultant(Name name, String phone, String expertise) {
		super(name, phone);
		this.patients = new PatientList();
		this.expertise = expertise;
		super.setId("CO00", ID);
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
		this.patients.addPatients(patient);
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
		return "\n\nId: " + this.getId() + "\nExpertise: " + expertise;
	}
}
