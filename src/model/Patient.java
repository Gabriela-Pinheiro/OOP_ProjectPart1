//Gabriela Pinheiro - R00225375 - Project_Part1

package model;

import java.io.Serializable;
import java.util.Objects;
import collections.VisitList;

public class Patient extends Person implements Serializable{

	public static int ID = 1;
	private VisitList patientVisits;
	private String consultantID;

	int index;

	public Patient(Name name, String phone, String ID, String consultantID) {
		super(name, phone);
		
		super.setId(ID);

		this.consultantID = consultantID;
		patientVisits = new VisitList();
		//TODO bring the consultant id from the selected consultant to the add patietn.
	}
	
	public Patient(Name name, String phone, String consultantID) {
		this(name, phone, ("PA00" + ID), consultantID);
		ID++;
	}
	
	public VisitList getPatientVisits() {
		return patientVisits;
	}

	public void setPatientVisits(VisitList patientVisits) {
		this.patientVisits = patientVisits;
	}

	public String showVisits() {
		return "Patient: " + this.getId() + ". " + patientVisits;
	}

	public Visit addVisit(Visit visit) {
		return this.patientVisits.addVisits(visit);			
	}
		

	@Override
	public String toString() {
		return this.getId() + " " + this.getName().getFirstName() + " " + this.getName().getLastName();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		return Objects.equals(patientVisits, other.patientVisits);
	}
}