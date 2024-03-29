//Gabriela Pinheiro - R00225375 - Project_Part1

package model;

import java.util.ArrayList;
import java.util.Objects;
import javax.print.attribute.standard.Severity;

import collections.PatientList;
import collections.VisitList;
import javafx.beans.property.StringProperty;

public class Patient extends Person{
	
	public static int ID = 1;
	private VisitList patientVisits;
	private String ilness;
	public Severity severity;
//	public PatientList allPatients= new PatientList();

	public Patient(Name name, String phone) {
		super(name, phone);
		patientVisits = new VisitList();
		super.setId("PA00", ID);
		ID++;
	}

	public enum Severity {
		MILD,
		MILDTOMODERATE,
		MODERATE,
		MODERATETOSEVERE,	
		SEVERE;
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

	public void addVisit(Visit visit) {
		this.patientVisits.addVisits(visit);			
	}
		
	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public String getIlness() {
		return ilness;
	}

	public void setIlness(String ilness) {
		this.ilness = ilness;
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
		return Objects.equals(ilness, other.ilness) && Objects.equals(patientVisits, other.patientVisits)
				&& Objects.equals(severity, other.severity);
	}
}
