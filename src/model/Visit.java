//Gabriela Pinheiro - R00225375 - Final Project

package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Visit implements Serializable{

	private LocalDate dateOfVisit;
	private String notes;
	
	public Visit(LocalDate dateOfVisit, String notes) {
		this.dateOfVisit = dateOfVisit;
		this.notes = notes;
	}

	public LocalDate getDateOfVisit() {
		return dateOfVisit;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes += "\n" + notes;
	}

	@Override
	public String toString() {
		return "\nVisit on " + dateOfVisit + ", notes: " + notes + ".";
	}
}

