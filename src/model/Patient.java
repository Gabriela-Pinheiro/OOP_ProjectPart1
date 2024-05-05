//Gabriela Pinheiro - R00225375 - Project_Part1

package model;

import java.io.Serializable;
import java.util.Objects;
import collections.VisitList;

public class Patient extends Person implements Serializable{

	private VisitList patientVisits;
	private int consultantID;

	int index;

	public Patient(Name name, String phone, int ID, int consultantID) {
		super(name, phone);
		super.setId(ID);

		this.consultantID = consultantID;
		patientVisits = new VisitList();
	}
	
	public Patient(Name name, String phone, int consultantID) {
		this(name, phone, 0, consultantID);
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